package com.odys.startrekgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ProgressBar;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ProgressBar progressBar2;

    public static MediaPlayer backgroundMusic;
    public static MediaPlayer loadingSound;
    public static MediaPlayer hitSound;
    public static MediaPlayer overSound;
    public static MediaPlayer spockSound;
    public static MediaPlayer breaksSound;
    public static MediaPlayer muteSound;
    public static MediaPlayer muteMusic;
    public static MediaPlayer shopSound;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar2 = (ProgressBar) findViewById(R.id.progress_bar2);
        progressBar2.setMax(100);

        backgroundMusic = MediaPlayer.create(this, R.raw.music);
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.3f, 0.3f);
        backgroundMusic.start();

        hitSound = MediaPlayer.create(this, R.raw.hit);
        overSound = MediaPlayer.create(this, R.raw.over);
        spockSound = MediaPlayer.create(this, R.raw.spock);
        breaksSound = MediaPlayer.create(this, R.raw.breaks);
        muteSound = MediaPlayer.create(this, R.raw.silence);
        muteMusic = MediaPlayer.create(this, R.raw.dubstep);
        shopSound = MediaPlayer.create(this, R.raw.shop);

        loadingSound = MediaPlayer.create(this, R.raw.loading);
        loadingSound.start();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 10; i <= 100; i+=10) {
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}