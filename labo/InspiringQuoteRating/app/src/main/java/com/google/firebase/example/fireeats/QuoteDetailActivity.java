/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.google.firebase.example.fireeats;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.example.fireeats.adapter.RatingAdapter;
import com.google.firebase.example.fireeats.model.Rating;
import com.google.firebase.example.fireeats.model.Quote;
import com.google.firebase.example.fireeats.util.QuoteUtil;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class QuoteDetailActivity extends AppCompatActivity
        implements EventListener<DocumentSnapshot>, RatingDialogFragment.RatingListener {

    private static final String TAG = "QuoteDetail";

    public static final String KEY_QUOTE_ID = "key_quote_id";

    @BindView(R.id.quote_image)
    ImageView mImageView;

    @BindView(R.id.quote_name)
    TextView mNameView;

    @BindView(R.id.quote_rating)
    MaterialRatingBar mRatingIndicator;

    @BindView(R.id.quote_num_ratings)
    TextView mNumRatingsView;

    @BindView(R.id.quote_category)
    TextView mCategoryView;

    @BindView(R.id.view_empty_ratings)
    ViewGroup mEmptyView;

    @BindView(R.id.recycler_ratings)
    RecyclerView mRatingsRecycler;

    @BindView(R.id.fab_show_rating_dialog)
    FloatingActionButton view_button_plus;

    private RatingDialogFragment mRatingDialog;

    private FirebaseFirestore mFirestore;
    private DocumentReference mQuoteRef;
    private ListenerRegistration mQuoteRegistration;

    private RatingAdapter mRatingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_detail);
        ButterKnife.bind(this);

        // deshabilitar por defecto hasta confirmar que puede habilitarse
        view_button_plus.setVisibility(View.INVISIBLE);

        // Get quote ID from extras
        String quoteId = getIntent().getExtras().getString(KEY_QUOTE_ID);
        if (quoteId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_QUOTE_ID);
        }

        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Get reference to the quote
        mQuoteRef = mFirestore.collection("quotes").document(quoteId);

        // Get ratings
        Query ratingsQuery = mQuoteRef
                .collection("ratings")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(50);

        // RecyclerView
        mRatingAdapter = new RatingAdapter(ratingsQuery) {
            @Override
            protected void onDataChanged() {
                if (getItemCount() == 0) {
                    mRatingsRecycler.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRatingsRecycler.setVisibility(View.VISIBLE);
                    mEmptyView.setVisibility(View.GONE);
                }
            }
        };

        mRatingsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRatingsRecycler.setAdapter(mRatingAdapter);

        mRatingDialog = new RatingDialogFragment();

        refresh();
    }

    @Override
    public void onStart() {
        super.onStart();

        mRatingAdapter.startListening();
        mQuoteRegistration = mQuoteRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        mRatingAdapter.stopListening();

        if (mQuoteRegistration != null) {
            mQuoteRegistration.remove();
            mQuoteRegistration = null;
        }
        refresh();
    }

    private void refresh() {
        super.onRestart();

        mQuoteRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                // el usuario que dio de alta esta cita
                final String quote_user = (String) task.getResult().getData().get("usuario");

                // el usuario logueado en este momento
                final String this_user = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // los usuarios que ya votaron
                final List<String> rating_users = new ArrayList();

                mQuoteRef.collection("ratings")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        rating_users.add((String) document.getData().get("userId"));
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                                Log.d("votos", quote_user + " / " + this_user + " / " + rating_users);
                                if(!quote_user.equals(this_user) && !rating_users.contains(this_user))
                                    view_button_plus.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });
    }

    private Task<Void> addRating(final DocumentReference quoteRef, final Rating rating) {

        // Create reference for new rating, for use inside the transaction
        final DocumentReference ratingRef = quoteRef.collection("ratings")
                .document();

        // In a transaction, add the new rating and update the aggregate totals
        return mFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction)
                    throws FirebaseFirestoreException {

                Quote quote = transaction.get(quoteRef)
                        .toObject(Quote.class);

                // Compute new number of ratings
                int newNumRatings = quote.getNumRatings() + 1;

                // Compute new average rating
                double oldRatingTotal = quote.getAvgRating() *
                        quote.getNumRatings();
                double newAvgRating = (oldRatingTotal + rating.getRating()) /
                        newNumRatings;

                // Set new quote info
                quote.setNumRatings(newNumRatings);
                quote.setAvgRating(newAvgRating);

                // Commit to Firestore
                transaction.set(quoteRef, quote);
                transaction.set(ratingRef, rating);

                return null;
            }
        });
    }

    /**
     * Listener for the Quote document ({@link #mQuoteRef}).
     */
    @Override
    public void onEvent(DocumentSnapshot snapshot, FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "quote:onEvent", e);
            return;
        }

        onQuoteLoaded(snapshot.toObject(Quote.class));
    }

    private void onQuoteLoaded(Quote quote) {
        mNameView.setText(quote.getName());
        mRatingIndicator.setRating((float) quote.getAvgRating());
        mNumRatingsView.setText(getString(R.string.fmt_num_ratings, quote.getNumRatings()));
        mCategoryView.setText(quote.getCategory());

        // Background image
        Glide.with(mImageView.getContext())
                .load(quote.getPhoto())
                .into(mImageView);
    }

    @OnClick(R.id.quote_button_back)
    public void onBackArrowClicked(View view) {
        onBackPressed();
    }

    @OnClick(R.id.fab_show_rating_dialog)
    public void onAddRatingClicked(View view){
        mRatingDialog.show(getSupportFragmentManager(), RatingDialogFragment.TAG);
    }

    @Override
    public void onRating(Rating rating) {
        // In a transaction, add the new rating and update the aggregate totals
        addRating(mQuoteRef, rating)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Rating added");

                        // Hide keyboard and scroll to top
                        hideKeyboard();
                        mRatingsRecycler.smoothScrollToPosition(0);
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Add rating failed", e);

                        // Show failure message and hide keyboard
                        hideKeyboard();
                        Snackbar.make(findViewById(android.R.id.content), "Failed to add rating",
                                Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
