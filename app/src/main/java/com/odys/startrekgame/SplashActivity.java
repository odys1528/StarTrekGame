package com.odys.startrekgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ProgressBar progressBar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar2 = (ProgressBar) findViewById(R.id.progress_bar2);
        progressBar2.setMax(100);

        MediaPlayer player = MediaPlayer.create(this, R.raw.music);
        player.setLooping(true);
        player.setVolume(0.3f, 0.3f);
        player.start();

        MediaPlayer player2 = MediaPlayer.create(this, R.raw.loading);
        player2.start();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= 100; i+=10) {
                        progressBar.setProgress(0);
                        for (int k = 0; k <= 100; k++) {
                            progressBar.setProgress(k);
                            sleep(10);
                        }
                        progressBar2.setProgress(i);
                        sleep(100);
                    }
                    sleep(100);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();


    }
}