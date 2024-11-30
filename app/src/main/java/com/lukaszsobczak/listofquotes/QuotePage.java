package com.lukaszsobczak.listofquotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuotePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quote_page);

        TextView quoteTextView = findViewById(R.id.text_view_quote);
        TextView authorTextView = findViewById(R.id.text_view_author);

        Intent intent = getIntent();
        String quote = intent.getStringExtra("quote");
        String author = intent.getStringExtra("author");

        quoteTextView.setText(quote);
        authorTextView.setText(author);
    }
}