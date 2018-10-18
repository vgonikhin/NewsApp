package ru.vgonikhin.aa.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    TextView textViewCategory;
    TextView textViewTitle;
    TextView textViewPreviewText;
    TextView textViewDate;
    ImageView imageViewNewsPicture;


    NewsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardview_news_item);
        textViewCategory = itemView.findViewById(R.id.textview_category);
        textViewTitle = itemView.findViewById(R.id.textview_title);
        textViewPreviewText = itemView.findViewById(R.id.textview_preview_text);
        textViewDate = itemView.findViewById(R.id.textview_date);
        imageViewNewsPicture = itemView.findViewById(R.id.imageview_news_picture);
    }
}
