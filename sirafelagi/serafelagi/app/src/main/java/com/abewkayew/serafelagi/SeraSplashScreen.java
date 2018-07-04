package com.abewkayew.serafelagi;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SeraSplashScreen extends AppCompatActivity {
    TextView splash_text;
    ProgressBar progressBar;
    int secondsDelayed = 2;
    private static int progress;
    private int progressStatus = 0;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sera_splash_screen);
        splash_text = findViewById(R.id.splash_text);
        progressBar = findViewById(R.id.progressBar);
        progress = 0;
        progressBar.setMax(100);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus = doSomeWork();
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                handler.post(new Runnable() {
                    public void run() {
                        // ---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
                        startActivity(new Intent(SeraSplashScreen.this, MainActivity.class));
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }

            private int doSomeWork() {
                try {
                    // ---simulate doing some work---
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start();

    }
}