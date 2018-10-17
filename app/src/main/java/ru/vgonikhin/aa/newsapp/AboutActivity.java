package ru.vgonikhin.aa.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    EditText editText;
    Button buttonSend;
    ImageButton buttonGithub;
    ImageButton buttonTelegram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initGUI();
    }

    public void initGUI() {
        editText = findViewById(R.id.edittext_message);
        buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(view -> composeEmail(editText.getText().toString()));
        buttonGithub = findViewById(R.id.button_github);
        buttonGithub.setOnClickListener(view -> viewLink(getString(R.string.github_link)));
        buttonTelegram = findViewById(R.id.button_telegram);
        buttonTelegram.setOnClickListener(view -> viewLink(getString(R.string.telegram_link)));
    }

    public void viewLink(String link){
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.error_no_app),Toast.LENGTH_LONG).show();
        }
    }

    public void composeEmail(String text) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
