package ru.vgonikhin.aa.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewHolder> {

    private final List<Single<NewsItem>> news;
    private final Context context;
    private final LayoutInflater inflater;

    NewsRecyclerViewAdapter(List<Single<NewsItem>> newsList, Context context) {
        this.news = newsList;
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
        final Single<NewsItem> newsItem = news.get(position);
        newsItem.subscribeOn(Schedulers.io()).delay(2000 + (int) (Math.random() * 3000), TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<NewsItem>() {
            @Override
            public void onSubscribe(Disposable d) {
                holder.progressBarCard.setVisibility(View.VISIBLE);
                holder.textViewCardCategory.setVisibility(View.INVISIBLE);
                holder.textViewCardTitle.setVisibility(View.INVISIBLE);
                holder.textViewCardPreviewText.setVisibility(View.INVISIBLE);
                holder.textViewCardDate.setVisibility(View.INVISIBLE);
                holder.imageViewCardNewsPicture.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onSuccess(NewsItem newsItem) {
                holder.progressBarCard.setVisibility(View.GONE);
                holder.textViewCardCategory.setVisibility(View.VISIBLE);
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
                holder.textViewCardTitle.setVisibility(View.VISIBLE);
                holder.textViewCardTitle.setText(newsItem.getTitle());
                holder.textViewCardPreviewText.setVisibility(View.VISIBLE);
                holder.textViewCardPreviewText.setText(newsItem.getPreviewText());
                holder.textViewCardDate.setVisibility(View.VISIBLE);
                holder.textViewCardDate.setText(newsItem.getDateString(context));
                holder.imageViewCardNewsPicture.setVisibility(View.VISIBLE);
                Glide.with(context).load(newsItem.getImageUrl()).into(holder.imageViewCardNewsPicture);
            }

            @Override
            public void onError(Throwable e) {
                holder.progressBarCard.setVisibility(View.GONE);
                Toast.makeText(context, "Couldn't load the news item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
