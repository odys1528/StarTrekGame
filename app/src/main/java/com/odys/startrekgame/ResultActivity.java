package com.odys.startrekgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);

        final ImageView menu = (ImageView) findViewById(R.id.menu);
        final ImageView shop = (ImageView) findViewById(R.id.shop);

        final int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");
        PocketMoney.addMoney(score);
        editor.putInt("MONEY", PocketMoney.getMoney());
        editor.commit();

        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText(getString(R.string.high_score) + " " + score);

            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        } else {
            highScoreLabel.setText(getString(R.string.high_score) + " " + highScore);
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setColorFilter(Color.WHITE);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
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


    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    public void endGame(View view) {
        finish();
        System.exit(0);

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}
