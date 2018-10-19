package ru.vgonikhin.aa.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewsDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_NEWS_ITEM = "extra_news_item";
    NewsItem newsItem;

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    TextView textViewDetailsFullText;
    ImageView imageViewDetailsPicture;

    public static void start(@NonNull Context context, @NonNull NewsItem newsItem) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(EXTRA_NEWS_ITEM, newsItem);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        bindViews();
        newsItem = (NewsItem) getIntent().getSerializableExtra(EXTRA_NEWS_ITEM);
        setupToolbar();
        Glide.with(this).load(newsItem.getImageUrl()).into(imageViewDetailsPicture);
        textViewDetailsFullText.setText(newsItem.getFullText());
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        textViewDetailsFullText = findViewById(R.id.textview_details_fulltext);
        imageViewDetailsPicture = findViewById(R.id.imageview_details_picture);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(newsItem.getCategory().getName());
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBackground));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorBackground));
    }
}
