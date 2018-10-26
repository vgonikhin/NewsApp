package ru.vgonikhin.aa.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final Object object = new Object();
    private static boolean threadSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        new Thread(new LeftLeg()).start();
        new Thread(new RightLeg()).start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private class LeftLeg implements Runnable {
        @Override
        public void run() {
            int leftCounter = 1;
            while (true) {
                if (!threadSwitch) {
                    synchronized (object) {
                        System.out.println("Left step" + (leftCounter++));
                        threadSwitch = true;
                    }
                }
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private class RightLeg implements Runnable {
        @Override
        public void run() {
            int rightCounter = 1;
            while (true) {
                if (threadSwitch) {
                    synchronized (object) {
                        System.out.println("Right step" + (rightCounter++));
                        threadSwitch = false;
                    }
                }
            }
        }
    }

}