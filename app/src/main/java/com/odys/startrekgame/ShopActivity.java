package com.odys.startrekgame;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putInt("MONEY", PocketMoney.getMoney());
        editor.commit();

        final ImageView menu = (ImageView) findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.setColorFilter(Color.WHITE);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        final int money = PocketMoney.getMoney();
        final TextView wallet = (TextView) findViewById(R.id.cash);
        wallet.setText(getString(R.string.cash) + " " + money);

        ImageView ship1 = (ImageView) findViewById(R.id.ship1);
        ImageView ship2 = (ImageView) findViewById(R.id.ship2);
        ImageView ship3 = (ImageView) findViewById(R.id.ship3);
        ImageView ship4 = (ImageView) findViewById(R.id.ship4);
        ImageView ship5 = (ImageView) findViewById(R.id.ship5);
        ImageView ship6 = (ImageView) findViewById(R.id.ship6);
        final ImageView [] ships = {ship1, ship2, ship3, ship4, ship5, ship6};

        TextView title1 = (TextView) findViewById(R.id.title1);
        TextView title2 = (TextView) findViewById(R.id.title2);
        TextView title3 = (TextView) findViewById(R.id.title3);
        TextView title4 = (TextView) findViewById(R.id.title4);
        TextView title5 = (TextView) findViewById(R.id.title5);
        TextView title6 = (TextView) findViewById(R.id.title6);
        final TextView [] titles = {title1, title2, title3, title4, title5, title6};

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        final Button [] buttons = {button1, button2, button3, button4, button5, button6};

        final int price1 = 0;
        final int price2 = 0;
        final int price3 = 10000;
        final int price4 = 50000;
        final int price5 = 100000;
        final int price6 = 1000000;
        final int [] prices = {price1, price2, price3, price4, price5, price6};

        final int SHIPS_NUMBER = 6;
        final int AFFORDABLE = 1;
        final int UNAFFORDABLE = 2;
        final int OWNED = 3;
        final int OTHER = 0;
        int status1 = settings.getInt("SHIP1", OWNED);
        int status2 = settings.getInt("SHIP2", OTHER);
        int status3 = settings.getInt("SHIP3", OTHER);
        int status4 = settings.getInt("SHIP4", OTHER);
        int status5 = settings.getInt("SHIP5", OTHER);
        int status6 = settings.getInt("SHIP6", OTHER);
        final int [] statuses = {status1, status2, status3, status4, status5, status6};

        //init loop
        for(int i = 0; i < SHIPS_NUMBER; i++) {
            if(statuses[i] != OWNED) {
                if(prices[i] <= money) {
                    statuses[i] = AFFORDABLE;
                    buttons[i].setEnabled(true);
                    ships[i].clearColorFilter();

                } else {
                    statuses[i] = UNAFFORDABLE;
                    buttons[i].setEnabled(false);
                    ships[i].setColorFilter(Color.GRAY);

                }
                buttons[i].setText(String.valueOf(prices[i]));
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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statuses[0] = OWNED;
                editor.putInt("SHIP1", statuses[0]);
                editor.commit();
                buttons[0].setEnabled(false);
                buttons[0].setText(getString(R.string.owned));
                ships[0].setImageResource(R.drawable.ship);
                titles[0].setText(getString(R.string.ship1));
                PocketMoney.spendMoney(price1);
                wallet.setText(getString(R.string.cash) + " " + money);
                editor.putInt("MONEY", PocketMoney.getMoney());
                editor.commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statuses[1] = OWNED;
                editor.putInt("SHIP2", statuses[1]);
                editor.commit();
                buttons[1].setEnabled(false);
                buttons[1].setText(getString(R.string.owned));
                ships[1].setImageResource(R.drawable.constitution);
                titles[1].setText(getString(R.string.ship2));
                PocketMoney.spendMoney(price2);
                wallet.setText(getString(R.string.cash) + " " + money);
                editor.putInt("MONEY", PocketMoney.getMoney());
                editor.commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statuses[2] = OWNED;
                editor.putInt("SHIP3", statuses[2]);
                editor.commit();
                buttons[2].setEnabled(false);
                buttons[2].setText(getString(R.string.owned));
                ships[2].setImageResource(R.drawable.heavyshuttle);
                titles[2].setText(getString(R.string.ship3));
                PocketMoney.spendMoney(price3);
                wallet.setText(getString(R.string.cash) + " " + money);
                editor.putInt("MONEY", PocketMoney.getMoney());
                editor.commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statuses[3] = OWNED;
                editor.putInt("SHIP4", statuses[3]);
                editor.commit();
                buttons[3].setEnabled(false);
                buttons[3].setText(getString(R.string.owned));
                ships[3].setImageResource(R.drawable.copernicus);
                titles[3].setText(getString(R.string.ship4));
                PocketMoney.spendMoney(price4);
                wallet.setText(getString(R.string.cash) + " " + money);
                editor.putInt("MONEY", PocketMoney.getMoney());
                editor.commit();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statuses[4] = OWNED;
                editor.putInt("SHIP5", statuses[4]);
                editor.commit();
                buttons[4].setEnabled(false);
                buttons[4].setText(getString(R.string.owned));
                ships[4].setImageResource(R.drawable.dorsalnacelles);
                titles[4].setText(getString(R.string.ship5));
                PocketMoney.spendMoney(price5);
                wallet.setText(getString(R.string.cash) + " " + money);
                editor.putInt("MONEY", PocketMoney.getMoney());
                editor.commit();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statuses[5] = OWNED;
                editor.putInt("SHIP6", statuses[5]);
                editor.commit();
                buttons[5].setEnabled(false);
                buttons[5].setText(getString(R.string.owned));
                ships[5].setImageResource(R.mipmap.box);
                titles[5].setText(getString(R.string.ship1));
                PocketMoney.spendMoney(price6);
                wallet.setText(getString(R.string.cash) + " " + money);
                editor.putInt("MONEY", PocketMoney.getMoney());
                editor.commit();
            }
        });

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}
