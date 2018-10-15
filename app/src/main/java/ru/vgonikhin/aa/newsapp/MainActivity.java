package ru.vgonikhin.aa.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextMain;
    AppCompatButton buttonMainPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMain = findViewById(R.id.edittext_main);
        buttonMainPreview = findViewById(R.id.button_main_preview);
        buttonMainPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreviewActivity.start(MainActivity.this,editTextMain.getText().toString());
            }
        });
    }
}
