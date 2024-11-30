package com.lukaszsobczak.listofquotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder> {

    private List<Quote> quotes;
    private Context context;

    public QuoteAdapter(List<Quote> quotes, Context context) {
        this.quotes = quotes;
        this.context = context;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.bind(quote);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuotePage.class);
                intent.putExtra("quote", quote.getContent());
                intent.putExtra("author", quote.getAuthor());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }


    static class QuoteViewHolder extends RecyclerView.ViewHolder {

        private TextView content;
        private TextView author;

        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.text_view_quote);
            author = itemView.findViewById(R.id.text_view_author);
        }

        public void bind(Quote quote) {
            content.setText(quote.getContent());
            author.setText(quote.getAuthor());
        }

    }
}
