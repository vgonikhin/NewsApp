package ru.vgonikhin.aa.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    public static final String KEY_MSG = "message";
    public static void start(Activity activity, String message){
        activity.startActivity(new Intent(activity,PreviewActivity.class).putExtra(KEY_MSG, message));
    }

    TextView textViewPreview;
    AppCompatButton buttonPreviewSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        textViewPreview = findViewById(R.id.textview_preview);
        buttonPreviewSend = findViewById(R.id.button_preview_send);
        textViewPreview.setText(getIntent().getStringExtra(KEY_MSG));
    }
}
