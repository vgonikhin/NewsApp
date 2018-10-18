package ru.vgonikhin.aa.newsapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsRecyclerVHDecoration decoration;
    Point p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        recyclerView = findViewById(R.id.recycler_news);
        recyclerView.setAdapter(new NewsRecyclerViewAdapter(DataUtils.generateNews(), this));
        setRecyclerLayout();
        decoration = new NewsRecyclerVHDecoration(getResources().getDimensionPixelSize(R.dimen.item_offset));
        recyclerView.addItemDecoration(decoration);
    }

    private void setRecyclerLayout() {
        p = new Point();
        getDisplayParams(p);
        if (p.x >= 1000)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        else if (p.x >= 600)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDisplayParams(Point point) {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        point.y = (int) (outMetrics.heightPixels / density);
        point.x = (int) (outMetrics.widthPixels / density);
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
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
