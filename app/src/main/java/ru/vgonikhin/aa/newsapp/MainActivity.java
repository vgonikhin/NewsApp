package ru.vgonikhin.aa.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private static final Object object = new Object();
    private static boolean threadSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");
                int i = 0;
                while (i < 5) {
                    System.out.println("loop");
                    textView.post(new LeftLeg());
                    textView.post(new RightLeg());
                    i++;
                }
            }
        });
    }

    private class LeftLeg implements Runnable {
        @Override
        public void run() {
            System.out.println("Left");
            if (!threadSwitch) {
                synchronized (object) {
                    textView.setText("Left");
                    System.out.println(Thread.currentThread().getName() + "\nLeft step");
                    threadSwitch = true;
                }
            }
        }
    }

    private class RightLeg implements Runnable {
        @Override
        public void run() {
            System.out.println("Right");
            if (threadSwitch) {
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\nRight step");
                    textView.setText("Right");
                    threadSwitch = false;
                }
            }
        }
    }
}