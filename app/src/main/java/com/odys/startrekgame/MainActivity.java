package com.odys.startrekgame;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView ship;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;

    //size
    private int frameHeight;
    private int shipSize;

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
        setContentView(R.layout.activity_main);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        ship = (ImageView) findViewById(R.id.ship);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);

        //move out of screen
        orange.setX(10000);
        orange.setY(10000);
        pink.setX(10000);
        pink.setY(10000);
        black.setX(10000);
        black.setY(10000);

    }

    public void changePosition() {//TODO

        //move ship
        if (action_flag) {
            shipY -= 20;
        } else {
            shipY += 20;
        }


        //check position
        if (shipY < 0) shipY = 0;
        if (shipY > frameHeight - shipSize) shipY = frameHeight - shipSize;
        ship.setY(shipY);
    }


    public boolean onTouchEvent(MotionEvent me) {

        if (start_flag == false) {

            start_flag = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            shipY = (int) ship.getY();
            shipSize = ship.getHeight();

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
}
