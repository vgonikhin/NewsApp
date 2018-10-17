package ru.vgonikhin.aa.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewHolder> {

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
        NewsItem newsItem = news.get(position);
        holder.textViewCategory.setText(newsItem.getCategory().getName());
        holder.textViewTitle.setText(newsItem.getTitle());
        holder.textViewPreviewText.setText(newsItem.getPreviewText());
        holder.textViewDate.setText(newsItem.getPublishDate().toString());
        Glide.with(context).load(newsItem.getImageUrl()).into(holder.imageViewNewsPicture);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
