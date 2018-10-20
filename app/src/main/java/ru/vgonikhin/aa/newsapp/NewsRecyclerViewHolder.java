package ru.vgonikhin.aa.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {

    final CardView cardViewCardNewsItem;
    final TextView textViewCardCategory;
    final TextView textViewCardTitle;
    final TextView textViewCardPreviewText;
    final TextView textViewCardDate;
    final ImageView imageViewCardNewsPicture;


    NewsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        cardViewCardNewsItem = itemView.findViewById(R.id.cardview_card_news_item);
        textViewCardCategory = itemView.findViewById(R.id.textview_card_category);
        textViewCardTitle = itemView.findViewById(R.id.textview_card_title);
        textViewCardPreviewText = itemView.findViewById(R.id.textview_card_preview_text);
        textViewCardDate = itemView.findViewById(R.id.textview_card_date);
        imageViewCardNewsPicture = itemView.findViewById(R.id.imageview_card_news_picture);
    }
}
