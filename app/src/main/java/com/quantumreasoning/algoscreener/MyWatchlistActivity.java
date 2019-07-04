package com.quantumreasoning.algoscreener;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MyWatchlistActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    private boolean free;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        MobileAds.initialize(this, "ca-app-pub-5164013549454719~8345618526");

        if(Constants.type == Constants.Type.FREE){
            free = true;
            AdView adView = (com.google.android.gms.ads.AdView) findViewById(R.id.myAdView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        FrameLayout menu_home = findViewById(R.id.menu_home);
        FrameLayout menu_screener = findViewById(R.id.menu_screener);
        FrameLayout menu_my_watchlist = findViewById(R.id.menu_my_watchlist);
        FrameLayout menu_premium_watchlist = findViewById(R.id.menu_premium_watchlist);
        final Drawable clicked = new ColorDrawable(Color.parseColor("#22000000"));
        menu_home.setForeground(null);
        menu_screener.setForeground(null);
        menu_my_watchlist.setForeground(clicked);
        menu_premium_watchlist.setForeground(null);

        final Button menu_button_screener = (Button) findViewById(R.id.menu_button_screener);
        menu_button_screener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MyWatchlistActivity.this, ScreenerActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_home = (Button) findViewById(R.id.menu_button_home);
        menu_button_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MyWatchlistActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_premium_watchlist = (Button) findViewById(R.id.menu_button_premium_watchlist);
        menu_button_premium_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (free) {
                    Toast.makeText(MyWatchlistActivity.this, "Buy Premium to access the Premium Watchlist", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MyWatchlistActivity.this, PremiumWatchlistActivity.class);
                    startActivity(intent);
                }
            }
        });

        FirebaseDatabaseHelper fire_db = new FirebaseDatabaseHelper(this);
        LocalDatabaseHelper local_db = new LocalDatabaseHelper(this);
        int[] btn_ids = new int[25];
        btn_ids[0] = R.id.watchlist_ticker1;
        btn_ids[1] = R.id.watchlist_ticker2;
        btn_ids[2] = R.id.watchlist_ticker3;
        btn_ids[3] = R.id.watchlist_ticker4;
        btn_ids[4] = R.id.watchlist_ticker5;
        btn_ids[5] = R.id.watchlist_ticker6;
        btn_ids[6] = R.id.watchlist_ticker7;
        btn_ids[7] = R.id.watchlist_ticker8;
        btn_ids[8] = R.id.watchlist_ticker9;
        btn_ids[9] = R.id.watchlist_ticker10;
        btn_ids[10] = R.id.watchlist_ticker11;
        btn_ids[11] = R.id.watchlist_ticker12;
        btn_ids[12] = R.id.watchlist_ticker13;
        btn_ids[13] = R.id.watchlist_ticker14;
        btn_ids[14] = R.id.watchlist_ticker15;
        btn_ids[15] = R.id.watchlist_ticker16;
        btn_ids[16] = R.id.watchlist_ticker17;
        btn_ids[17] = R.id.watchlist_ticker18;
        btn_ids[18] = R.id.watchlist_ticker19;
        btn_ids[19] = R.id.watchlist_ticker20;
        btn_ids[20] = R.id.watchlist_ticker21;
        btn_ids[21] = R.id.watchlist_ticker22;
        btn_ids[22] = R.id.watchlist_ticker23;
        btn_ids[23] = R.id.watchlist_ticker24;
        btn_ids[24] = R.id.watchlist_ticker25;

        ArrayList<String> watchlist = local_db.getWatchlistList();
        for (int i = 0; (i < watchlist.size()) && (i < 25); i++){
            fire_db.getTickerData(btn_ids[i], watchlist.get(i), "Watchlist");
        }

    }

    public boolean delete_from_watchlist(String ticker){
        LocalDatabaseHelper local_db = new LocalDatabaseHelper(this);
        local_db.deleteWatchlistTicker(ticker);
        return true;
    }


}
