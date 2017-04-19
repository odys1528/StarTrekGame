package com.odys.startrekgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShipActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int item = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        categories.add(getString(R.string.ship1));

        if(settings.getInt("SHIP2", 0) == 3) {
            categories.add(getString(R.string.ship2));
        }
        if(settings.getInt("SHIP3", 0) == 3) {
            categories.add(getString(R.string.ship3));
        }
        if(settings.getInt("SHIP4", 0) == 3) {
            categories.add(getString(R.string.ship4));
        }
        if(settings.getInt("SHIP5", 0) == 3) {
            categories.add(getString(R.string.ship5));
        }
        if(settings.getInt("SHIP6", 0) == 3) {
            categories.add(getString(R.string.ship6));
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        spinner.setSelection(0);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                intent.putExtra("SHIP", item);
                spinner.setVisibility(View.GONE);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorAccent));
        ((TextView) parent.getChildAt(0)).setTextSize(20);
        ((TextView) parent.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        item = position;
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}

