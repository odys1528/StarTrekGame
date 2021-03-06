package com.odys.startrekgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean SOUND_TURNED_ON;
    private boolean MUSIC_TURNED_ON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        PocketMoney.setMoney(settings.getInt("MONEY", 0));

        final ImageView volume = (ImageView) findViewById(R.id.volume);
        final ImageView note = (ImageView) findViewById(R.id.note);
        final ImageView shop = (ImageView) findViewById(R.id.shop);

        final SharedPreferences.Editor editor = settings.edit();
        SOUND_TURNED_ON = settings.getBoolean("SOUND", true);
        MUSIC_TURNED_ON = settings.getBoolean("MUSIC", true);

        if(SOUND_TURNED_ON) {
            volume.clearColorFilter();
            SplashActivity.hitSound1.setVolume(1.0f, 1.0f);
            SplashActivity.hitSound2.setVolume(1.0f, 1.0f);
            SplashActivity.overSound.setVolume(1.0f, 1.0f);
            SplashActivity.spockSound.setVolume(1.0f, 1.0f);
            SplashActivity.breaksSound.setVolume(1.0f, 1.0f);
            SplashActivity.shopSound.setVolume(1.0f, 1.0f);
        } else {
            volume.setColorFilter(Color.WHITE);
            SplashActivity.hitSound1.setVolume(0.0f, 0.0f);
            SplashActivity.hitSound2.setVolume(0.0f, 0.0f);
            SplashActivity.overSound.setVolume(0.0f, 0.0f);
            SplashActivity.spockSound.setVolume(0.0f, 0.0f);
            SplashActivity.breaksSound.setVolume(0.0f, 0.0f);
            SplashActivity.shopSound.setVolume(0.0f, 0.0f);
        }

        if(MUSIC_TURNED_ON) {
            note.clearColorFilter();
            SplashActivity.backgroundMusic.setVolume(0.5f, 0.5f);
        } else {
            note.setColorFilter(Color.WHITE);
            SplashActivity.backgroundMusic.setVolume(0.0f, 0.0f);
        }

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SOUND_TURNED_ON) {
                    SplashActivity.hitSound1.setVolume(0.0f, 0.0f);
                    SplashActivity.hitSound2.setVolume(0.0f, 0.0f);
                    SplashActivity.overSound.setVolume(0.0f, 0.0f);
                    SplashActivity.spockSound.setVolume(0.0f, 0.0f);
                    SplashActivity.breaksSound.setVolume(0.0f, 0.0f);
                    SplashActivity.shopSound.setVolume(0.0f, 0.0f);

                    SplashActivity.muteSound.start();
                    volume.setColorFilter(Color.WHITE);
                    SOUND_TURNED_ON = false;
                } else {
                    SplashActivity.hitSound1.setVolume(1.0f, 1.0f);
                    SplashActivity.hitSound2.setVolume(1.0f, 1.0f);
                    SplashActivity.overSound.setVolume(1.0f, 1.0f);
                    SplashActivity.spockSound.setVolume(1.0f, 1.0f);
                    SplashActivity.breaksSound.setVolume(1.0f, 1.0f);
                    SplashActivity.shopSound.setVolume(1.0f, 1.0f);

                    volume.clearColorFilter();
                    SOUND_TURNED_ON = true;
                }
                editor.putBoolean("SOUND", SOUND_TURNED_ON);
                editor.commit();
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MUSIC_TURNED_ON) {
                    SplashActivity.backgroundMusic.setVolume(0.0f, 0.0f);
                    note.setColorFilter(Color.WHITE);
                    MUSIC_TURNED_ON = false;
                } else {
                    SplashActivity.backgroundMusic.setVolume(1.0f, 1.0f);
                    SplashActivity.muteMusic.start();
                    note.clearColorFilter();
                    MUSIC_TURNED_ON = true;
                }
                editor.putBoolean("MUSIC", MUSIC_TURNED_ON);
                editor.commit();
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shop.setColorFilter(Color.WHITE);

                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                finish();
            }
        });

    }

    public void startGame(View view) {
        startActivity(new Intent(getApplicationContext(), ShipActivity.class));
        finish();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}
