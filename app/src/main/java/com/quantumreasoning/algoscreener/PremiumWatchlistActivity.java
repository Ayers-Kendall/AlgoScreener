package com.quantumreasoning.algoscreener;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class PremiumWatchlistActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        FrameLayout menu_home = findViewById(R.id.menu_home);
        FrameLayout menu_screener = findViewById(R.id.menu_screener);
        FrameLayout menu_my_watchlist = findViewById(R.id.menu_my_watchlist);
        FrameLayout menu_premium_watchlist = findViewById(R.id.menu_premium_watchlist);
        final Drawable clicked = new ColorDrawable(Color.parseColor("#22000000"));
        menu_home.setForeground(null);
        menu_screener.setForeground(null);
        menu_my_watchlist.setForeground(null);
        menu_premium_watchlist.setForeground(clicked);

        final Button menu_button_screener = (Button) findViewById(R.id.menu_button_screener);
        menu_button_screener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PremiumWatchlistActivity.this, ScreenerActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_my_watchlist = (Button) findViewById(R.id.menu_button_my_watchlist);
        menu_button_my_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PremiumWatchlistActivity.this, MyWatchlistActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_home = (Button) findViewById(R.id.menu_button_home);
        menu_button_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PremiumWatchlistActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        getPremiumTickers();

    }

    public void getPremiumTickers(){
        MyApplication app = ((MyApplication) this.getApplication());
        int temp_price_above_option = app.getPrice_above_option();
        int temp_price_below_option = app.getPrice_below_option();
        int temp_market_cap_above_option = app.getMarket_cap_above_option();
        int temp_market_cap_below_option = app.getMarket_cap_below_option();
        int temp_rel_volume_above_option = app.getRel_volume_above_option();
        int temp_rel_volume_below_option = app.getRel_volume_below_option();
        int temp_percent_above_option = app.getPercent_above_option();
        int temp_percent_below_option = app.getPercent_below_option();
        int temp_volatility_above_option = app.getVolatility_above_option();
        int temp_volatility_below_option = app.getVolatility_below_option();
        int temp_rel_volatility_above_option = app.getRel_volatility_above_option();
        int temp_rel_volatility_below_option = app.getRel_volatility_below_option();
        int temp_volume_above_option = app.getVolume_above_option();
        int temp_volume_below_option = app.getVolume_below_option();
        int temp_avg_volume_above_option = app.getAvg_volume_above_option();
        int temp_avg_volume_below_option = app.getAvg_volume_below_option();
        int temp_vol_4rel_3_above_option = app.getVol_4rel_3_above_option();
        int temp_vol_4rel_3_below_option = app.getVol_4rel_3_below_option();
        int temp_vol_3rel_2_above_option= app.getVol_3rel_2_above_option();
        int temp_vol_3rel_2_below_option= app.getVol_3rel_2_below_option();
        int temp_vol_2rel_1_above_option= app.getVol_2rel_1_above_option();
        int temp_vol_2rel_1_below_option= app.getVol_2rel_1_below_option();
        int temp_percent_4rel_3_above_option = app.getPercent_4rel_3_above_option();
        int temp_percent_4rel_3_below_option = app.getPercent_4rel_3_below_option();
        int temp_percent_3rel_2_above_option = app.getPercent_3rel_2_above_option();
        int temp_percent_3rel_2_below_option = app.getPercent_3rel_2_below_option();
        int temp_percent_2rel_1_above_option = app.getPercent_2rel_1_above_option();
        int temp_percent_2rel_1_below_option = app.getPercent_2rel_1_below_option();
        int temp_near_3month_high_option = app.getNear_3month_high_option();
        int temp_near_resistance_daily_above_option = app.getNear_resistance_daily_above_option();
        int temp_near_resistance_daily_below_option = app.getNear_resistance_daily_below_option();
        int temp_near_resistance_1M_above_option = app.getNear_resistance_1M_above_option();
        int temp_near_resistance_1M_below_option = app.getNear_resistance_1M_below_option();
        int temp_near_resistance_3M_above_option = app.getNear_resistance_3M_above_option();
        int temp_near_resistance_3M_below_option = app.getNear_resistance_3M_below_option();
        int temp_sort_by_option = app.getSort_by_option();
        int temp_near_support_daily_above_option = app.getNear_support_daily_above_option();
        int temp_near_support_daily_below_option = app.getNear_support_daily_below_option();
        int temp_near_support_1M_above_option = app.getNear_support_1M_above_option();
        int temp_near_support_1M_below_option = app.getNear_support_1M_below_option();
        int temp_near_support_3M_above_option = app.getNear_support_3M_above_option();
        int temp_near_support_3M_below_option = app.getNear_support_3M_below_option();
        int temp_parabolic_1h_option = app.getParabolic_1h_option();
        int temp_parabolic_1wk_option = app.getParabolic_1wk_option();
        int temp_parabolic_1M_option = app.getParabolic_1M_option();
        int temp_steady_movement_1M_option = app.getSteady_movement_1M_option();
        int temp_steady_movement_1wk_option = app.getSteady_movement_1wk_option();
        int temp_steady_movement_intraday_option = app.getSteady_movement_intraday_option();
        int temp_volatility_intraday_above_option = app.getVolatility_intraday_above_option();
        int temp_volatility_intraday_below_option = app.getVolatility_intraday_below_option();


        // Set premium screener settings
        app.setPrice_above_option(0);
        app.setPrice_below_option(0);
        app.setMarket_cap_above_option(2);
        app.setMarket_cap_below_option(0);
        app.setRel_volume_above_option(7);
        app.setRel_volume_below_option(4);
        app.setPercent_above_option(0);
        app.setPercent_below_option(0);
        app.setVolatility_above_option(0);
        app.setVolatility_below_option(0);
        app.setRel_volatility_above_option(0);
        app.setRel_volatility_below_option(0);
        app.setVolume_above_option(0);
        app.setVolume_below_option(0);
        app.setAvg_volume_above_option(0);
        app.setAvg_volume_below_option(0);
        app.setVol_4rel_3_above_option(6);
        app.setVol_4rel_3_below_option(0);
        app.setVol_3rel_2_above_option(8);
        app.setVol_3rel_2_below_option(4);
        app.setVol_2rel_1_above_option(8);
        app.setVol_2rel_1_below_option(6);
        app.setPercent_4rel_3_above_option(7);
        app.setPercent_4rel_3_below_option(3);
        app.setPercent_3rel_2_above_option(8);
        app.setPercent_3rel_2_below_option(6);
        app.setPercent_2rel_1_above_option(10);
        app.setPercent_2rel_1_below_option(7);
        app.setNear_3month_high_option(0);
        app.setNear_resistance_daily_above_option(0);
        app.setNear_resistance_daily_below_option(0);
        app.setNear_resistance_1M_above_option(0);
        app.setNear_resistance_1M_below_option(0);
        app.setNear_resistance_3M_above_option(0);
        app.setNear_resistance_3M_below_option(0);
        app.setSort_by_option(2);
        app.setNear_support_daily_above_option(0);
        app.setNear_support_daily_below_option(0);
        app.setNear_support_1M_above_option(7);
        app.setNear_support_1M_below_option(5);
        app.setNear_support_3M_above_option(0);
        app.setNear_support_3M_below_option(0);
        app.setParabolic_1h_option(0);
        app.setParabolic_1wk_option(0);
        app.setParabolic_1M_option(0);
        app.setSteady_movement_1M_option(0);
        app.setSteady_movement_1wk_option(0);
        app.setSteady_movement_intraday_option(0);
        app.setVolatility_intraday_above_option(0);
        app.setVolatility_intraday_below_option(0);


        // Fetch data and add to list
        int[] btn_ids1 = new int[5];
        Button label_btn = findViewById(R.id.watchlist_ticker1);
        label_btn.setText("Large Market Cap Stocks (Low risk)");
        label_btn.setGravity(Gravity.CENTER);
        label_btn = findViewById(R.id.watchlist_ticker7);
        label_btn.setText("Mid Market Cap Stocks (Medium risk)");
        label_btn.setGravity(Gravity.CENTER);
        label_btn = findViewById(R.id.watchlist_ticker13);
        label_btn.setText("Small Market Cap Stocks (High risk)");
        label_btn.setGravity(Gravity.CENTER);
        btn_ids1[0] = R.id.watchlist_ticker2;
        btn_ids1[1] = R.id.watchlist_ticker3;
        btn_ids1[2] = R.id.watchlist_ticker4;
        btn_ids1[3] = R.id.watchlist_ticker5;
        btn_ids1[4] = R.id.watchlist_ticker6;
        FirebaseDatabaseHelper fire_db1 = new FirebaseDatabaseHelper(this, btn_ids1);
        fire_db1.extractMatchedCriteriaTickers(app);

        int[] btn_ids2 = new int[5];
        app.setMarket_cap_above_option(4);
        app.setMarket_cap_below_option(2);
        btn_ids2[0] = R.id.watchlist_ticker8;
        btn_ids2[1] = R.id.watchlist_ticker9;
        btn_ids2[2] = R.id.watchlist_ticker10;
        btn_ids2[3] = R.id.watchlist_ticker11;
        btn_ids2[4] = R.id.watchlist_ticker12;
        FirebaseDatabaseHelper fire_db2 = new FirebaseDatabaseHelper(this, btn_ids2);
        fire_db2.extractMatchedCriteriaTickers(app);

        int[] btn_ids3 = new int[5];
        app.setMarket_cap_above_option(6);
        app.setMarket_cap_below_option(4);
        btn_ids3[0] = R.id.watchlist_ticker14;
        btn_ids3[1] = R.id.watchlist_ticker15;
        btn_ids3[2] = R.id.watchlist_ticker16;
        btn_ids3[3] = R.id.watchlist_ticker17;
        btn_ids3[4] = R.id.watchlist_ticker18;
        FirebaseDatabaseHelper fire_db3 = new FirebaseDatabaseHelper(this, btn_ids3);
        fire_db3.extractMatchedCriteriaTickers(app);



        // Set back the original values
        app.setPrice_above_option(temp_price_above_option);
        app.setPrice_below_option(temp_price_below_option);
        app.setMarket_cap_above_option(temp_market_cap_above_option);
        app.setMarket_cap_below_option(temp_market_cap_below_option);
        app.setRel_volume_above_option(temp_rel_volume_above_option);
        app.setRel_volume_above_option(temp_rel_volume_below_option);
        app.setPercent_above_option(temp_percent_above_option);
        app.setPercent_below_option(temp_percent_below_option);
        app.setVolatility_above_option(temp_volatility_above_option);
        app.setVolatility_below_option(temp_volatility_below_option);
        app.setRel_volatility_above_option(temp_rel_volatility_above_option);
        app.setRel_volatility_below_option(temp_rel_volatility_below_option);
        app.setVolume_above_option(temp_volume_above_option);
        app.setVolume_below_option(temp_volume_below_option);
        app.setAvg_volume_above_option(temp_avg_volume_above_option);
        app.setAvg_volume_below_option(temp_avg_volume_below_option);
        app.setVol_4rel_3_above_option(temp_vol_4rel_3_above_option);
        app.setVol_4rel_3_below_option(temp_vol_4rel_3_below_option);
        app.setVol_3rel_2_above_option(temp_vol_3rel_2_above_option);
        app.setVol_3rel_2_below_option(temp_vol_3rel_2_below_option);
        app.setVol_2rel_1_above_option(temp_vol_2rel_1_above_option);
        app.setVol_2rel_1_below_option(temp_vol_2rel_1_below_option);
        app.setPercent_4rel_3_above_option(temp_percent_4rel_3_above_option);
        app.setPercent_4rel_3_below_option(temp_percent_4rel_3_below_option);
        app.setPercent_3rel_2_above_option(temp_percent_3rel_2_above_option);
        app.setPercent_3rel_2_below_option(temp_percent_3rel_2_below_option);
        app.setPercent_2rel_1_above_option(temp_percent_2rel_1_above_option);
        app.setPercent_2rel_1_below_option(temp_percent_2rel_1_below_option);
        app.setNear_3month_high_option(temp_near_3month_high_option);
        app.setNear_resistance_daily_above_option(temp_near_resistance_daily_above_option);
        app.setNear_resistance_daily_below_option(temp_near_resistance_daily_below_option);
        app.setNear_resistance_1M_above_option(temp_near_resistance_1M_above_option);
        app.setNear_resistance_1M_below_option(temp_near_resistance_1M_below_option);
        app.setNear_resistance_3M_above_option(temp_near_resistance_3M_above_option);
        app.setNear_resistance_3M_below_option(temp_near_resistance_3M_below_option);
        app.setSort_by_option(temp_sort_by_option);
        app.setNear_support_daily_above_option(temp_near_support_daily_above_option);
        app.setNear_support_daily_below_option(temp_near_support_daily_below_option);
        app.setNear_support_1M_above_option(temp_near_support_1M_above_option);
        app.setNear_support_1M_below_option(temp_near_support_1M_below_option);
        app.setNear_support_3M_above_option(temp_near_support_3M_above_option);
        app.setNear_support_3M_below_option(temp_near_support_3M_below_option);
        app.setParabolic_1h_option(temp_parabolic_1h_option);
        app.setParabolic_1wk_option(temp_parabolic_1wk_option);
        app.setParabolic_1M_option(temp_parabolic_1M_option);
        app.setSteady_movement_1M_option(temp_steady_movement_1M_option);
        app.setSteady_movement_1wk_option(temp_steady_movement_1wk_option);
        app.setSteady_movement_intraday_option(temp_steady_movement_intraday_option);
        app.setVolatility_intraday_above_option(temp_volatility_intraday_above_option);
        app.setVolatility_intraday_below_option(temp_volatility_intraday_below_option);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }


}
