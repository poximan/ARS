package com.google.firebase.example.fireeats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.example.fireeats.model.Quote;
import com.google.firebase.example.fireeats.util.QuoteUtil;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddQuoteActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFirestore = FirebaseFirestore.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_quote);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
    }

    public void goBack(View view) {
        super.onBackPressed();
    }
    public void saveQuote(View view) {

        Quote new_quote = new Quote();
        CollectionReference quotes = mFirestore.collection("quotes");

        EditText quoteView = findViewById(R.id.editTextQuote);
        EditText authorView = findViewById(R.id.editTextAuthor);

        String quoteText = quoteView.getText().toString();
        String authorText = authorView.getText().toString();

        if(quoteText.isEmpty() || authorText.isEmpty()) { return; }

        new_quote.setName(quoteText);
        new_quote.setCategory(authorText);
        new_quote.setPhoto(QuoteUtil.getRandomImageUrl());

        quotes.add(new_quote);
    }
}
