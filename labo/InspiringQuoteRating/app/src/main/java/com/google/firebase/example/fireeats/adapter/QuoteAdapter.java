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
 package com.google.firebase.example.fireeats.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.model.Quote;
import com.google.firebase.example.fireeats.util.QuoteUtil;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * RecyclerView adapter for a list of Quotes.
 */
public class QuoteAdapter extends FirestoreAdapter<QuoteAdapter.ViewHolder> {

    public interface OnQuoteSelectedListener {

        void onQuoteSelected(DocumentSnapshot quote);

    }

    private OnQuoteSelectedListener mListener;

    public QuoteAdapter(Query query, OnQuoteSelectedListener listener) {
        super(query);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_quote, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quote_item_image)
        ImageView imageView;

        @BindView(R.id.quote_item_name)
        TextView nameView;

        @BindView(R.id.quote_item_rating)
        MaterialRatingBar ratingBar;

        @BindView(R.id.quote_item_num_ratings)
        TextView numRatingsView;

        @BindView(R.id.quote_item_category)
        TextView categoryView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnQuoteSelectedListener listener) {

            Quote quote = snapshot.toObject(Quote.class);
            Resources resources = itemView.getResources();

            // Load image
            Glide.with(imageView.getContext())
                    .load(quote.getPhoto())
                    .into(imageView);

            nameView.setText(quote.getName());
            ratingBar.setRating((float) quote.getAvgRating());
            categoryView.setText(quote.getCategory());
            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings,
                    quote.getNumRatings()));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onQuoteSelected(snapshot);
                    }
                }
            });
        }

    }
}
