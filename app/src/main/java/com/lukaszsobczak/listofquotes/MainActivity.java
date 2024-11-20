package com.lukaszsobczak.listofquotes;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView quoteRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        quoteRecyclerView = findViewById(R.id.recycle_view_list);

        QuoteAdapter adapter = new QuoteAdapter(readFile());
        quoteRecyclerView.setAdapter(adapter);


        //Tutaj mamy domyślne ustawienie vertical
//        quoteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        quoteRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Tutaj mamy ustawienia w siatce grid 2 kolumny
        quoteRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        quoteRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        quoteRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

    }

    public List<Quote> readFile() {
        List<Quote> quotes = new ArrayList<>();

        String language = Locale.getDefault().getLanguage();
        String fileName;

        if(language.equals("pl")) {
            fileName = "quotations[pl].txt";
        } else {
            fileName = "quotations[en].txt";
        }

        try (InputStream stream = getAssets().open(fileName)) {

            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader read = new BufferedReader(streamReader);
            String line;

            while ((line = read.readLine()) != null) {
                quotes.add(new Quote(line, read.readLine()));
            }
        } catch (IOException exception) {
            Log.e("file", exception.toString());

        }
        return quotes;
    }
}