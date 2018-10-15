package ru.vgonikhin.aa.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        buttonPreviewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail("vgonikhin@gmail.com", textViewPreview.getText().toString());
            }
        });
    }

    public void composeEmail(String address, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"No email app!",Toast.LENGTH_LONG).show();
        }
    }
}
