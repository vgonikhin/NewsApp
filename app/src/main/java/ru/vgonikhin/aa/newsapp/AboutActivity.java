package ru.vgonikhin.aa.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonSend;
    private ImageButton buttonGithub;
    private ImageButton buttonTelegram;

    public static void start(@NonNull Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        bindViews();
        initUIActions();
    }

    private void bindViews() {
        editText = findViewById(R.id.edittext_message);
        buttonSend = findViewById(R.id.button_send);
        buttonGithub = findViewById(R.id.button_github);
        buttonTelegram = findViewById(R.id.button_telegram);
    }

    private void initUIActions() {
        buttonSend.setOnClickListener(view -> composeEmail(editText.getText().toString()));
        buttonGithub.setOnClickListener(view -> viewLink(getString(R.string.github_link)));
        buttonTelegram.setOnClickListener(view -> viewLink(getString(R.string.telegram_link)));
    }

    private void viewLink(String link) {
        final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.error_no_app),Toast.LENGTH_LONG).show();
        }
    }

    private void composeEmail(String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse(getString(R.string.mailto_address)))
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject))
                .putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.error_no_app),Toast.LENGTH_LONG).show();
        }
    }

}
