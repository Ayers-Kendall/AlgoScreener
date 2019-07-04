package com.quantumreasoning.algoscreener;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Kendall on 10/14/2017.
 */

public class SettingsActivity extends AppCompatActivity {
    FirebaseInstanceId instanceID = FirebaseInstanceId.getInstance();
    String token = instanceID.getToken();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FrameLayout menu_home = findViewById(R.id.menu_home);
        FrameLayout menu_screener = findViewById(R.id.menu_screener);
        FrameLayout menu_my_watchlist = findViewById(R.id.menu_my_watchlist);
        FrameLayout menu_premium_watchlist = findViewById(R.id.menu_premium_watchlist);
        final Drawable clicked = new ColorDrawable(Color.parseColor("#22000000"));
        menu_home.setForeground(null);
        menu_screener.setForeground(null);
        menu_my_watchlist.setForeground(null);
        menu_premium_watchlist.setForeground(null);
        final Button menu_button_home = (Button) findViewById(R.id.menu_button_home);
        menu_button_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_my_watchlist = (Button) findViewById(R.id.menu_button_my_watchlist);
        menu_button_my_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MyWatchlistActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_premium_watchlist = (Button) findViewById(R.id.menu_button_premium_watchlist);
        menu_button_premium_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, PremiumWatchlistActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_screener = (Button) findViewById(R.id.menu_button_screener);
        menu_button_screener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ScreenerActivity.class);
                startActivity(intent);
            }
        });

        setListeners();

     }



     public boolean onCreateOptionsMenu(Menu menu){
          getMenuInflater().inflate(R.menu.menu_custom, menu);
          return true;
     }

     public boolean onOptionsItemSelected(MenuItem item){
          return true;
     }


     public void setListeners(){
         /* final Button t = (Button) findViewById(R.id.);
          t.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {

               }
          });*/
     }

}
