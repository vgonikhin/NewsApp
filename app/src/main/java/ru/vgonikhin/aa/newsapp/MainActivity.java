package ru.vgonikhin.aa.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Thread threadLeft;
    private Thread threadRight;

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
        threadLeft = new Thread(new LeftLeg());
        threadLeft.start();
        threadRight = new Thread(new RightLeg());
        threadRight.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (threadLeft != null) {
            threadLeft.interrupt();
            threadLeft = null;
        }
        if (threadRight != null) {
            threadRight.interrupt();
            threadRight = null;
        }
    }

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
                if (Thread.interrupted()) return;
            }
        }
    }

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
                if (Thread.interrupted()) return;
            }
        }
    }
}