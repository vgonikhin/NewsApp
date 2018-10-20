package ru.vgonikhin.aa.newsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        bindViews();
        setupRecycler();
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.recycler_news);
    }

    private void setupRecycler() {
        recyclerView.setAdapter(new NewsRecyclerViewAdapter(DataUtils.generateNews(), this));
        setRecyclerLayout();
        recyclerView.addItemDecoration(new NewsRecyclerVHDecoration(getResources().getDimensionPixelSize(R.dimen.offset_item)));
    }

    private void setRecyclerLayout() {
        int width = DisplayParams.getDisplayWidthInDp(this);
        if (width >= 1000)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        else if (width >= 600)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switch:
                AboutActivity.start(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
