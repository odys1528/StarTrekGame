package com.odys.startrekgame;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    public static int money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final ImageView menu = (ImageView) findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setColorFilter(Color.WHITE);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        ImageView ship1 = (ImageView) findViewById(R.id.ship1);
        ImageView ship2 = (ImageView) findViewById(R.id.ship2);
        ImageView ship3 = (ImageView) findViewById(R.id.ship3);
        ImageView ship4 = (ImageView) findViewById(R.id.ship4);
        ImageView ship5 = (ImageView) findViewById(R.id.ship5);
        ImageView ship6 = (ImageView) findViewById(R.id.ship6);
        ImageView [] ships = {ship1, ship2, ship3, ship4, ship5, ship6};

        TextView title1 = (TextView) findViewById(R.id.title1);
        TextView title2 = (TextView) findViewById(R.id.title2);
        TextView title3 = (TextView) findViewById(R.id.title3);
        TextView title4 = (TextView) findViewById(R.id.title4);
        TextView title5 = (TextView) findViewById(R.id.title5);
        TextView title6 = (TextView) findViewById(R.id.title6);
        TextView [] titles = {title1, title2, title3, title4, title5, title6};

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button [] buttons = {button1, button2, button3, button4, button5, button6};

        final int price1 = 0;
        final int price2 = 5000;
        final int price3 = 10000;
        final int price4 = 50000;
        final int price5 = 100000;
        final int price6 = 1000000;
        final int [] prices = {price1, price2, price3, price4, price5, price6};

        int SHIPS_NUMBER = 6;
        int AFFORDABLE = 1;
        int UNAFFORDABLE = 2;
        int OWNED = 3;
        int OTHER = 0;
        int status1 = OWNED;
        int status2 = OTHER;
        int status3 = OTHER;
        int status4 = OTHER;
        int status5 = OTHER;
        int status6 = OTHER;
        int [] statuses = {status1, status2, status3, status4, status5, status6};

        for(int i = 0; i < SHIPS_NUMBER; i++) {
            if(statuses[i] != OWNED) {
                if(prices[i] <= money) {
                    statuses[i] = AFFORDABLE;
                    buttons[i].setEnabled(true);
                    buttons[i].setText(getString(R.string.affordable));
                    ships[i].clearColorFilter();

                } else {
                    statuses[i] = UNAFFORDABLE;
                    buttons[i].setEnabled(false);
                    buttons[i].setText(getString(R.string.unaffordable));
                    ships[i].setColorFilter(Color.GRAY);

                }

                titles[i].setText(getString(R.string.ship_def_name));

            } else {
                statuses[i] = OWNED;
                buttons[i].setEnabled(false);
                buttons[i].setText(getString(R.string.owned));

                switch (i) {
                    case 0:
                        ships[i].setImageResource(R.drawable.ship);
                        titles[i].setText(getString(R.string.ship1));
                        break;
                    case 1:
                        ships[i].setImageResource(R.drawable.constitution);
                        titles[i].setText(getString(R.string.ship2));
                        break;
                    case 2:
                        ships[i].setImageResource(R.drawable.heavyshuttle);
                        titles[i].setText(getString(R.string.ship3));
                        break;
                    case 3:
                        ships[i].setImageResource(R.drawable.copernicus);
                        titles[i].setText(getString(R.string.ship4));
                        break;
                    case 4:
                        ships[i].setImageResource(R.drawable.dorsalnacelles);
                        titles[i].setText(getString(R.string.ship5));
                        break;
                    case 5:
                        ships[i].setImageResource(R.mipmap.box);
                        titles[i].setText(getString(R.string.ship6));
                        break;
                }

            }
        }

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}
