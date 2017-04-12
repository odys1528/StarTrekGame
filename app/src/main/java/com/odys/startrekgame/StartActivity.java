package com.odys.startrekgame;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView ship;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;

    //size
    private int frameHeight;
    private int shipHeight;
    private int shipWidth;
    private int screenWidth;
    private int screenHeight;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;

    //score
    private int score = 0;

    //position
    private int shipY;

    //init class
    private Handler handler = new Handler();
    private  Timer timer = new Timer();

    //status check
    private boolean action_flag = false;
    private boolean start_flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        ship = (ImageView) findViewById(R.id.ship);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);

        //get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;


        //move out of screen
        orange.setX(10000);
        orange.setY(10000);
        pink.setX(10000);
        pink.setY(10000);
        black.setX(10000);
        black.setY(10000);

        scoreLabel.setText(getString(R.string.score) + " " + score);
    }

    public void changePosition() {//TODO

        hitCheck();

        orangeX -= 25;
        if (orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        blackX -= 35;
        if (blackX < 0) {
            blackX = screenWidth + 10;
            blackY = (int) Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);

        pinkX -= 30;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        //move ship
        if (action_flag) {
            shipY -= 20;
        } else {
            shipY += 20;
        }


        //check position
        if (shipY < 0) shipY = 0;
        if (shipY > frameHeight - shipHeight) shipY = frameHeight - shipHeight;
        ship.setY(shipY);

        scoreLabel.setText(getString(R.string.score) + " " + score);
    }


    public void hitCheck() {

        int orangeCenterX = orangeX + orange.getWidth() / 2;
        int orangeCenterY = orangeY + orange.getHeight() / 2;

        if (0 <= orangeCenterX && orangeCenterX <= shipWidth && shipY <= orangeCenterY && orangeCenterY <= shipY + shipHeight) {
            orangeX = -10;
            score += 10;
        }

        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;

        if (0 <= pinkCenterX && pinkCenterX <= shipWidth && shipY <= pinkCenterY && pinkCenterY <= shipY + shipHeight) {
            pinkX = -10;
            score += 30;
        }

        int blackCenterX = blackX + black.getWidth() / 2;
        int blackCenterY = blackY + black.getHeight() / 2;

        if (0 <= blackCenterX && blackCenterX <= shipWidth && shipY <= blackCenterY && blackCenterY <= shipY + shipHeight) {

            timer.cancel();
            timer = null;

            finish();
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);

        }

    }

    public boolean onTouchEvent(MotionEvent me) {

        if (start_flag == false) {

            start_flag = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            shipY = (int) ship.getY();
            shipHeight = ship.getHeight();
            shipWidth = ship.getWidth();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePosition();
                        }
                    });
                }
            }, 0, 20);

        } else {

            if(me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flag = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flag = false;
            }

        }

        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}
