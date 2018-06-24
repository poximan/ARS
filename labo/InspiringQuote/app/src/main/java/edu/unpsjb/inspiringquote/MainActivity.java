package edu.unpsjb.inspiringquote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentListenOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String QUOTE_KEY = "quote";
    private static final String AUTHOR_KEY = "author";
    private static final String TAG = "InspiringQuote";

    private TextView mQuoteTextView;

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().collection("sampleData").document("inspiration");

    @Override
    protected void onStart() {
        super.onStart();

        DocumentListenOptions verboseOptions = new DocumentListenOptions();
        verboseOptions.includeMetadataChanges();

        mDocRef.addSnapshotListener(this, verboseOptions, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if(documentSnapshot.exists()){
                    Log.d(TAG, "Got a " + (documentSnapshot.getMetadata().hasPendingWrites() ? "Local" : "Server") + " update");
                    update(documentSnapshot);
                } else {
                    Log.d(TAG, "Got an exception!", e);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuoteTextView = (TextView) findViewById(R.id.textViewInspiring);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void fetchQuote(View view) {
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists()){
                    update(documentSnapshot);
                }
            }
        });
    }

    public void saveQuote(View view) {

        EditText quoteView = (EditText) findViewById(R.id.editTextQuote);
        EditText authorView = (EditText) findViewById(R.id.editTextAuthor);

        String quoteText = quoteView.getText().toString();
        String authorText = authorView.getText().toString();

        if(quoteText.isEmpty() || authorText.isEmpty()) { return; }

        Map<String, Object> dataSave = new HashMap<String, Object>();
        dataSave.put(QUOTE_KEY, quoteText);
        dataSave.put(AUTHOR_KEY, authorText);

        mDocRef.set(dataSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document has been saved!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Document was not saved!", e);
            }
        });
    }

    public void update(DocumentSnapshot documentSnapshot) {
        /*
        alternativa 1
        Map<String, Object> myData = documentSnapshot.getData();

        alternativa 2
        InspiringQuote myQuote = documentSnapshot.toObject(InspiringQuote.class);
        */

        String quoteText = documentSnapshot.getString(QUOTE_KEY);
        String authorText = documentSnapshot.getString(AUTHOR_KEY);
        mQuoteTextView.setText("\"" + quoteText + "\" -- " + authorText);
    }
}
