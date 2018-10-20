package ru.vgonikhin.aa.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NEWS_ITEM = "extra_news_item";

    private Toolbar toolbar;
    private TextView textViewDetailsTitle;
    private TextView textViewDetailsDate;
    private TextView textViewDetailsFullText;
    private ImageView imageViewDetailsNewsPicture;

    public static void start(@NonNull Context context, @NonNull NewsItem newsItem) {
        context.startActivity(new Intent(context, NewsDetailsActivity.class).putExtra(EXTRA_NEWS_ITEM, newsItem));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        bindViews();
        final NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra(EXTRA_NEWS_ITEM);
        setupToolbar(newsItem.getCategory());
        populateViews(newsItem);
    }

    private void populateViews(@NonNull NewsItem newsItem) {
        Glide.with(this).load(newsItem.getImageUrl()).into(imageViewDetailsNewsPicture);
        textViewDetailsTitle.setText(newsItem.getTitle());
        textViewDetailsDate.setText(newsItem.getDateString(this));
        textViewDetailsFullText.setText(newsItem.getFullText());
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        textViewDetailsTitle = findViewById(R.id.textview_details_title);
        textViewDetailsDate = findViewById(R.id.textview_details_date);
        textViewDetailsFullText = findViewById(R.id.textview_details_fulltext);
        imageViewDetailsNewsPicture = findViewById(R.id.imageview_details_news_picture);
    }

    private void setupToolbar(String title) {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
