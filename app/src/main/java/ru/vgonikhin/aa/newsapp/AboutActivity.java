package ru.vgonikhin.aa.newsapp;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.edittext_message) EditText editText;
    @BindView(R.id.button_send) Button buttonSend;
    @BindView(R.id.button_github) ImageButton buttonGithub;
    @BindView(R.id.button_telegram) ImageButton buttonTelegram;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        unbinder = ButterKnife.bind(this);
        initButtons();
    }

    public void initButtons(){
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail(editText.getText().toString());
            }
        });
        buttonGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLink(getString(R.string.github_link));
            }
        });
        buttonTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLink(getString(R.string.telegram_link));
            }
        });
    }

    //ButterKnife OnClick with androidX crashes app - disabled until solution
    //@OnClick(R.id.button_send)
    //public void sendFeedback(){
    //    composeEmail(editText.getText().toString());
    //}

//    @OnClick(R.id.button_github)
//    public void contactGithub(){
//        viewLink(getString(R.string.github_link));
//    }
//
//    @OnClick(R.id.button_telegram)
//    public void contactTelegram(){
//        viewLink(getString(R.string.telegram_link));
//    }

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
        unbinder.unbind();
        super.onDestroy();
    }
}
