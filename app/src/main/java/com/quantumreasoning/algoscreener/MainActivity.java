package com.quantumreasoning.algoscreener;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

public class MainActivity extends AppCompatActivity {

    private boolean free = false;
    private GestureDetectorCompat gestureObject;
    public LocalDatabaseHelper local_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-5164013549454719~8345618526");
        if(Constants.type == Constants.Type.FREE){
            free = true;
            AdView adView = (com.google.android.gms.ads.AdView) findViewById(R.id.myAdView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        local_db = new LocalDatabaseHelper(this);
        checkForFirstOpen();
        checkForOpenMessage();
        checkForMessageOfDay();

        FrameLayout menu_home = findViewById(R.id.menu_home);
        FrameLayout menu_screener = findViewById(R.id.menu_screener);
        FrameLayout menu_my_watchlist = findViewById(R.id.menu_my_watchlist);
        FrameLayout menu_premium_watchlist = findViewById(R.id.menu_premium_watchlist);
        final Drawable clicked = new ColorDrawable(Color.parseColor("#22000000"));
        menu_home.setForeground(clicked);
        menu_screener.setForeground(null);
        menu_my_watchlist.setForeground(null);
        menu_premium_watchlist.setForeground(null);

        // ******* FOR TESTING ONLY ********
        // this.deleteDatabase("user_data.db");
        // *********************************
        getDefaultSettings();

        final Button screener = (Button) findViewById(R.id.button_screener);
        screener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScreenerActivity.class);
                startActivity(intent);
            }
        });
        final Button my_watchlist = (Button) findViewById(R.id.button_my_watchlist);
        my_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyWatchlistActivity.class);
                startActivity(intent);
            }
        });
        final Button premium_watchlist = (Button) findViewById(R.id.button_premium_watchlist);
        premium_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (free) {
                    Toast.makeText(MainActivity.this, "Buy Premium to access the Premium Watchlist", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, PremiumWatchlistActivity.class);
                    startActivity(intent);
                }
            }
        });
        /*final Button settings = (Button) findViewById(R.id.button_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
             DELETED FOR NOW */
        final Button about_app = (Button) findViewById(R.id.button_about_app);
        about_app.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                intent.putExtra("title", "About App");
                intent.putExtra("body", getResources().getString(R.string.about_app_body));
                startActivity(intent);
            }
        });

        final Button contact_us = (Button) findViewById(R.id.button_contact_us);
        contact_us.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                intent.putExtra("title", "Contact Us");
                intent.putExtra("body", getResources().getString(R.string.contact_us_body));
                startActivity(intent);
            }
        });

        final Button menu_button_buy_premium = (Button) findViewById(R.id.button_buy_premium);
        if (free) {
            menu_button_buy_premium.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.quantumreasoning.algoscreenerpremium"));
                    //intent.setData(Uri.parse("market://search?q=stock"));
                    startActivity(intent);
                }
            });
        }
        if (!free){
            ViewGroup layout = (ViewGroup) menu_button_buy_premium.getParent();
            if (layout!=null){
                layout.removeView(menu_button_buy_premium);
            }
        }

        final Button menu_button_screener = (Button) findViewById(R.id.menu_button_screener);
        menu_button_screener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScreenerActivity.class);
                startActivity(intent);
            }
        });

        final Button menu_button_my_watchlist = (Button) findViewById(R.id.menu_button_my_watchlist);
        menu_button_my_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyWatchlistActivity.class);
                startActivity(intent);
            }
        });

        final Button menu_button_premium_watchlist = (Button) findViewById(R.id.menu_button_premium_watchlist);
        menu_button_premium_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (free) {
                    Toast.makeText(MainActivity.this, "Buy Premium to access the Premium Watchlist", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, PremiumWatchlistActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }



    public void getWatchlist(){
        //testing
        Cursor data = local_db.getTableData("user_watchlist");
        if (data.getCount() == 0){
            Toast.makeText(this, "None", Toast.LENGTH_LONG).show();
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (data.moveToNext()) {
                buffer.append(data.getString(1));
            }
            Toast.makeText(this, buffer.toString() + " test", Toast.LENGTH_LONG).show();
        }
    }

    public void checkForFirstOpen(){
        Cursor data = local_db.getTableData("user_defaults");
        boolean found = false;
        if (!(data.getCount() == 0)){
            StringBuffer buffer = new StringBuffer();
            while (data.moveToNext()) {
                buffer.append(data.getString(1));
                if (data.getString(1).equals("first_open")){
                    found = true;
                }
            }
        }
        if (found == false){
            Intent intent = new Intent(MainActivity.this, MessageActivity.class);
            intent.putExtra("title", "Thank you for downloading!!");
            intent.putExtra("body", getResources().getString(R.string.first_open_body));
            startActivity(intent);
            local_db.insertDefault("first_open", -1);
        }
    }

    public void checkForOpenMessage(){
        FirebaseDatabaseHelper fire_db = new FirebaseDatabaseHelper(this);
        fire_db.getOpenMessage();
    }

    public void checkForMessageOfDay(){
        FirebaseDatabaseHelper fire_db = new FirebaseDatabaseHelper(this);
        fire_db.getMessageOfDay();
    }

    public void getDefaultSettings(){
        String settings_str = local_db.getScreenerSettingsString("Default");
        ((MyApplication)getApplication()).SettingsFromString(settings_str);

        // Get other defaults
    }

}
