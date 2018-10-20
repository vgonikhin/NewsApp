package ru.vgonikhin.aa.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewHolder> {

    private final List<NewsItem> news;
    private final Context context;
    private final LayoutInflater inflater;

    NewsRecyclerViewAdapter(List<NewsItem> news, Context context) {
        this.news = news;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsRecyclerViewHolder(inflater.inflate(R.layout.recycler_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewHolder holder, int position) {
        final NewsItem newsItem = news.get(position);
        holder.textViewCardCategory.setText(newsItem.getCategory());
        switch (newsItem.getCategory()) {
            case DataUtils.DARWIN_AWARDS:
                holder.cardViewCardNewsItem.setCardBackgroundColor(context.getResources().getColor(R.color.darwinAwards));
                break;
            case DataUtils.CRIMINAL:
                holder.cardViewCardNewsItem.setCardBackgroundColor(context.getResources().getColor(R.color.criminal));
                break;
            case DataUtils.ANIMALS:
                holder.cardViewCardNewsItem.setCardBackgroundColor(context.getResources().getColor(R.color.animals));
                break;
            case DataUtils.MUSIC:
                holder.cardViewCardNewsItem.setCardBackgroundColor(context.getResources().getColor(R.color.music));
                break;
        }
        holder.cardViewCardNewsItem.setOnClickListener(view -> NewsDetailsActivity.start(context, newsItem));
        holder.textViewCardTitle.setText(newsItem.getTitle());
        holder.textViewCardPreviewText.setText(newsItem.getPreviewText());
        holder.textViewCardDate.setText(newsItem.getDateString(context));
        Glide.with(context).load(newsItem.getImageUrl()).into(holder.imageViewCardNewsPicture);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
