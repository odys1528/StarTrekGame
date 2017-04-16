package com.odys.startrekgame;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import tyrantgit.explosionfield.ExplosionField;

public class StartActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView ship;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;
    private ImageView blue;

    //speed
    private int shipSpeed;
    private int pinkSpeed;
    private int orangeSpeed;
    private int blackSpeed;
    private int blueSpeed;
    private final int parameter = 1;
    private final int DEFAULT_SHIP_SPEED = 20 / parameter;
    private final int DEFAULT_PINK_SPEED = 30 / parameter;
    private final int DEFAULT_ORANGE_SPEED = 20 / parameter;
    private final int DEFAULT_BLACK_SPEED = 40 / parameter;
    private final int DEFAULT_BLUE_SPEED = 50 / parameter;



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
    private int blueX;
    private int blueY;

    //score
    private int score = 0;

    //position
    private int shipY;

    //init class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //status check
    private boolean action_flag = false;
    private boolean start_flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //moving background
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, -1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX + width);
            }
        });
        animator.start();

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        ship = (ImageView) findViewById(R.id.ship);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);
        blue = (ImageView) findViewById(R.id.blue);


        //get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        shipSpeed = DEFAULT_SHIP_SPEED;
        orangeSpeed = DEFAULT_ORANGE_SPEED;
        pinkSpeed = DEFAULT_PINK_SPEED;
        blackSpeed = DEFAULT_BLACK_SPEED;
        blueSpeed = DEFAULT_BLUE_SPEED;

        /*
        Log.v("SPEED_SHIP", shipSpeed+"");
        Log.v("SPEED_ORANGE", orangeSpeed+"");
        Log.v("SPEED_PINK", pinkSpeed+"");
        Log.v("SPEED_BLACK", blackSpeed+"");
        */

        //move out of screen
        orange.setX(10000);
        orange.setY(10000);
        pink.setX(10000);
        pink.setY(10000);
        black.setX(10000);
        black.setY(10000);
        blue.setX(100000);
        blue.setY(100000);

        scoreLabel.setText(getString(R.string.score) + " " + score);
    }

    public void changePosition() {

        hitCheck();

        orangeX -= orangeSpeed;
        if (orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        blackX -= blackSpeed;
        if (blackX < 0) {
            blackX = screenWidth + 10;
            blackY = (int) Math.floor(Math.random() * (frameHeight - black.getHeight()));
        }
        black.setX(blackX);
        black.setY(blackY);

        pinkX -= pinkSpeed;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        blueX -= blueSpeed;
        if (blueX < 0) {
            blueX = screenWidth + 100000;
            blueY = (int) Math.floor(Math.random() * (frameHeight - blue.getHeight()));
        }
        blue.setX(blueX);
        blue.setY(blueY);

        //move ship
        if (action_flag) {
            shipY -= shipSpeed;
        } else {
            shipY += shipSpeed;
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
            increaseSpeed();
            SplashActivity.hitSound.start();
        }

        int pinkCenterX = pinkX + pink.getWidth() / 2;
        int pinkCenterY = pinkY + pink.getHeight() / 2;

        if (0 <= pinkCenterX && pinkCenterX <= shipWidth && shipY <= pinkCenterY && pinkCenterY <= shipY + shipHeight) {
            pinkX = -10;
            score += 30;
            increaseSpeed();
            SplashActivity.hitSound.start();
        }

        int blueCenterX = blueX + blue.getWidth() / 2;
        int blueCenterY = blueY + blue.getHeight() / 2;

        if (0 <= blueCenterX && blueCenterX <= shipWidth && shipY <= blueCenterY && blueCenterY <= shipY + shipHeight) {
            blueX = -10;
            decreaseSpeed();
            SplashActivity.breaksSound.start();
        }

        int blackCenterX = blackX + black.getWidth() / 2;
        int blackCenterY = blackY + black.getHeight() / 2;

        if (0 <= blackCenterX && blackCenterX <= shipWidth && shipY <= blackCenterY && blackCenterY <= shipY + shipHeight) {

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("SCORE", score);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            timer.cancel();
            timer = null;

            SplashActivity.overSound.start();
            SplashActivity.spockSound.start();

            final ExplosionField explosionField = ExplosionField.attach2Window(this);
            black.setVisibility(View.GONE);
            ship.setVisibility(View.GONE);
            explosionField.explode(ship);thread.start();

        }

    }

    public void increaseSpeed() {
        orangeSpeed++;
        pinkSpeed++;
        blackSpeed++;
        blueSpeed++;
    }

    public void decreaseSpeed() {
        orangeSpeed = Math.max(orangeSpeed / 2, DEFAULT_ORANGE_SPEED);
        pinkSpeed = Math.max(pinkSpeed / 2, DEFAULT_PINK_SPEED);
        blackSpeed = Math.max(blackSpeed / 2, DEFAULT_BLACK_SPEED);
        blueSpeed = Math.max(blueSpeed / 2, DEFAULT_BLUE_SPEED);
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
