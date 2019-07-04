package com.quantumreasoning.algoscreener;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Kendall on 10/19/2017.
 */


public class ScreenerSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean free = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screener_settings);

        if(Constants.type == Constants.Type.FREE){
            free = true;
        }

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
                Intent intent = new Intent(ScreenerSettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_my_watchlist = (Button) findViewById(R.id.menu_button_my_watchlist);
        menu_button_my_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScreenerSettingsActivity.this, MyWatchlistActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_premium_watchlist = (Button) findViewById(R.id.menu_button_premium_watchlist);
        menu_button_premium_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (free) {
                    Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access the Premium Watchlist", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(ScreenerSettingsActivity.this, PremiumWatchlistActivity.class);
                    startActivity(intent);
                }
            }
        });
        final Button menu_button_screener = (Button) findViewById(R.id.menu_button_screener);
        menu_button_screener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScreenerSettingsActivity.this, ScreenerActivity.class);
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
        Spinner spin01 = (Spinner) findViewById(R.id.spinner_price_above);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.price_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin01.setAdapter(adapter);
        spin01.setSelection(((MyApplication) this.getApplication()).getPrice_above_option());
        spin01.setOnItemSelectedListener(this);

        Spinner spin02 = (Spinner) findViewById(R.id.spinner_price_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.price_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin02.setAdapter(adapter);
        spin02.setSelection(((MyApplication) this.getApplication()).getPrice_below_option());
        spin02.setOnItemSelectedListener(this);
        
        Spinner spin03 = (Spinner) findViewById(R.id.spinner_market_cap_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.market_cap_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin03.setAdapter(adapter);
        spin03.setSelection(((MyApplication) this.getApplication()).getMarket_cap_above_option());
        spin03.setOnItemSelectedListener(this);

        Spinner spin04 = (Spinner) findViewById(R.id.spinner_market_cap_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.market_cap_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin04.setAdapter(adapter);
        spin04.setSelection(((MyApplication) this.getApplication()).getMarket_cap_below_option());
        spin04.setOnItemSelectedListener(this);

        Spinner spin05 = (Spinner) findViewById(R.id.spinner_rel_volume_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.relative_volume_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin05.setAdapter(adapter);
        spin05.setSelection(((MyApplication) this.getApplication()).getRel_volume_above_option());
        spin05.setOnItemSelectedListener(this);

        Spinner spin06 = (Spinner) findViewById(R.id.spinner_rel_volume_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.relative_volume_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin06.setAdapter(adapter);
        spin06.setSelection(((MyApplication) this.getApplication()).getRel_volume_below_option());
        spin06.setOnItemSelectedListener(this);
        
        Spinner spin07 = (Spinner) findViewById(R.id.spinner_percent_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_change_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin07.setAdapter(adapter);
        spin07.setSelection(((MyApplication) this.getApplication()).getPercent_above_option());
        spin07.setOnItemSelectedListener(this);

        Spinner spin08 = (Spinner) findViewById(R.id.spinner_percent_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_change_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin08.setAdapter(adapter);
        spin08.setSelection(((MyApplication) this.getApplication()).getPercent_below_option());
        spin08.setOnItemSelectedListener(this);
        
        Spinner spin09 = (Spinner) findViewById(R.id.spinner_volatility_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volatility_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin09.setAdapter(adapter);
        spin09.setSelection(((MyApplication) this.getApplication()).getVolatility_above_option());
        spin09.setOnItemSelectedListener(this);

        Spinner spin10 = (Spinner) findViewById(R.id.spinner_volatility_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volatility_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin10.setAdapter(adapter);
        spin10.setSelection(((MyApplication) this.getApplication()).getVolatility_below_option());
        spin10.setOnItemSelectedListener(this);
        
        Spinner spin11 = (Spinner) findViewById(R.id.spinner_rel_volatility_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.relative_volatility_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin11.setAdapter(adapter);
        spin11.setSelection(((MyApplication) this.getApplication()).getRel_volatility_above_option());
        spin11.setOnItemSelectedListener(this);

        Spinner spin12 = (Spinner) findViewById(R.id.spinner_rel_volatility_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.relative_volatility_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin12.setAdapter(adapter);
        spin12.setSelection(((MyApplication) this.getApplication()).getRel_volatility_below_option());
        spin12.setOnItemSelectedListener(this);

        Spinner spin13 = (Spinner) findViewById(R.id.spinner_volume_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin13.setAdapter(adapter);
        spin13.setSelection(((MyApplication) this.getApplication()).getVolume_above_option());
        spin13.setOnItemSelectedListener(this);

        Spinner spin14 = (Spinner) findViewById(R.id.spinner_volume_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin14.setAdapter(adapter);
        spin14.setSelection(((MyApplication) this.getApplication()).getVolume_below_option());
        spin14.setOnItemSelectedListener(this);

     /*   Spinner spin15 = (Spinner) findViewById(R.id.spinner_avg_volume_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.average_volume_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin15.setAdapter(adapter);
        spin15.setSelection(((MyApplication) this.getApplication()).getAvg_volume_above_option());
        spin15.setOnItemSelectedListener(this);

        Spinner spin16 = (Spinner) findViewById(R.id.spinner_avg_volume_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.average_volume_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin16.setAdapter(adapter);
        spin16.setSelection(((MyApplication) this.getApplication()).getAvg_volume_below_option());
        spin16.setOnItemSelectedListener(this);*/

        Spinner spin17 = (Spinner) findViewById(R.id.spinner_volume_4rel_3_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_4rel_3_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin17.setAdapter(adapter);
        spin17.setSelection(((MyApplication) this.getApplication()).getVol_4rel_3_above_option());
        spin17.setOnItemSelectedListener(this);

        Spinner spin18 = (Spinner) findViewById(R.id.spinner_volume_4rel_3_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_4rel_3_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin18.setAdapter(adapter);
        spin18.setSelection(((MyApplication) this.getApplication()).getVol_4rel_3_below_option());
        spin18.setOnItemSelectedListener(this);

        Spinner spin19 = (Spinner) findViewById(R.id.spinner_volume_3rel_2_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_3rel_2_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin19.setAdapter(adapter);
        spin19.setSelection(((MyApplication) this.getApplication()).getVol_3rel_2_above_option());
        spin19.setOnItemSelectedListener(this);

        Spinner spin20 = (Spinner) findViewById(R.id.spinner_volume_3rel_2_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_3rel_2_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin20.setAdapter(adapter);
        spin20.setSelection(((MyApplication) this.getApplication()).getVol_3rel_2_below_option());
        spin20.setOnItemSelectedListener(this);
        

        Spinner spin21 = (Spinner) findViewById(R.id.spinner_volume_2rel_1_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_2rel_1_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin21.setAdapter(adapter);
        spin21.setSelection(((MyApplication) this.getApplication()).getVol_2rel_1_above_option());
        spin21.setOnItemSelectedListener(this);

        Spinner spin22 = (Spinner) findViewById(R.id.spinner_volume_2rel_1_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.volume_2rel_1_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin22.setAdapter(adapter);
        spin22.setSelection(((MyApplication) this.getApplication()).getVol_2rel_1_below_option());
        spin22.setOnItemSelectedListener(this);
        
        Spinner spin23 = (Spinner) findViewById(R.id.spinner_percent_4rel_3_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_4rel_3_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin23.setAdapter(adapter);
        spin23.setSelection(((MyApplication) this.getApplication()).getPercent_4rel_3_above_option());
        spin23.setOnItemSelectedListener(this);

        Spinner spin24 = (Spinner) findViewById(R.id.spinner_percent_4rel_3_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_4rel_3_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin24.setAdapter(adapter);
        spin24.setSelection(((MyApplication) this.getApplication()).getPercent_4rel_3_below_option());
        spin24.setOnItemSelectedListener(this);

        Spinner spin25 = (Spinner) findViewById(R.id.spinner_percent_3rel_2_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_3rel_2_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin25.setAdapter(adapter);
        spin25.setSelection(((MyApplication) this.getApplication()).getPercent_3rel_2_above_option());
        spin25.setOnItemSelectedListener(this);

        Spinner spin26 = (Spinner) findViewById(R.id.spinner_percent_3rel_2_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_3rel_2_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin26.setAdapter(adapter);
        spin26.setSelection(((MyApplication) this.getApplication()).getPercent_3rel_2_below_option());
        spin26.setOnItemSelectedListener(this);
        
        Spinner spin27 = (Spinner) findViewById(R.id.spinner_percent_2rel_1_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_2rel_1_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin27.setAdapter(adapter);
        spin27.setSelection(((MyApplication) this.getApplication()).getPercent_2rel_1_above_option());
        spin27.setOnItemSelectedListener(this);

        Spinner spin28 = (Spinner) findViewById(R.id.spinner_percent_2rel_1_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.percent_2rel_1_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin28.setAdapter(adapter);
        spin28.setSelection(((MyApplication) this.getApplication()).getPercent_2rel_1_below_option());
        spin28.setOnItemSelectedListener(this);

        Spinner spin29 = (Spinner) findViewById(R.id.spinner_sort_by);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.sort_by, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin29.setAdapter(adapter);
        spin29.setSelection(((MyApplication) this.getApplication()).getSort_by_option());
        spin29.setOnItemSelectedListener(this);

        Spinner spin30 = (Spinner) findViewById(R.id.spinner_parabolic_month);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.parabolic_1M_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin30.setAdapter(adapter);
        spin30.setSelection(((MyApplication) this.getApplication()).getParabolic_1M_option());
        spin30.setOnItemSelectedListener(this);

        Spinner spin31 = (Spinner) findViewById(R.id.spinner_parabolic_week);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.parabolic_1wk_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin31.setAdapter(adapter);
        spin31.setSelection(((MyApplication) this.getApplication()).getParabolic_1wk_option());
        spin31.setOnItemSelectedListener(this);

        Spinner spin32 = (Spinner) findViewById(R.id.spinner_near_resistance_3month_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_resistance_3M_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin32.setAdapter(adapter);
        spin32.setSelection(((MyApplication) this.getApplication()).getNear_resistance_3M_above_option());
        spin32.setOnItemSelectedListener(this);

        Spinner spin33 = (Spinner) findViewById(R.id.spinner_near_resistance_3month_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_resistance_3M_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin33.setAdapter(adapter);
        spin33.setSelection(((MyApplication) this.getApplication()).getNear_resistance_3M_below_option());
        spin33.setOnItemSelectedListener(this);

        Spinner spin34 = (Spinner) findViewById(R.id.spinner_near_resistance_1month_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_resistance_1M_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin34.setAdapter(adapter);
        spin34.setSelection(((MyApplication) this.getApplication()).getNear_resistance_1M_above_option());
        spin34.setOnItemSelectedListener(this);

        Spinner spin35 = (Spinner) findViewById(R.id.spinner_near_resistance_1month_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_resistance_1M_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin35.setAdapter(adapter);
        spin35.setSelection(((MyApplication) this.getApplication()).getNear_resistance_1M_below_option());
        spin35.setOnItemSelectedListener(this);

        Spinner spin36 = (Spinner) findViewById(R.id.spinner_near_support_3month_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_support_3M_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin36.setAdapter(adapter);
        spin36.setSelection(((MyApplication) this.getApplication()).getNear_support_3M_above_option());
        spin36.setOnItemSelectedListener(this);

        Spinner spin37 = (Spinner) findViewById(R.id.spinner_near_support_3month_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_support_3M_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin37.setAdapter(adapter);
        spin37.setSelection(((MyApplication) this.getApplication()).getNear_support_3M_below_option());
        spin37.setOnItemSelectedListener(this);

        Spinner spin38 = (Spinner) findViewById(R.id.spinner_near_support_1month_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_support_1M_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin38.setAdapter(adapter);
        spin38.setSelection(((MyApplication) this.getApplication()).getNear_support_1M_above_option());
        spin38.setOnItemSelectedListener(this);

        Spinner spin39 = (Spinner) findViewById(R.id.spinner_near_support_1month_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_support_1M_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin39.setAdapter(adapter);
        spin39.setSelection(((MyApplication) this.getApplication()).getNear_support_1M_below_option());
        spin39.setOnItemSelectedListener(this);

        Spinner spin40 = (Spinner) findViewById(R.id.spinner_steady_movement_1M);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.steady_movement_1M_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin40.setAdapter(adapter);
        spin40.setSelection(((MyApplication) this.getApplication()).getSteady_movement_1M_option());
        spin40.setOnItemSelectedListener(this);

        Spinner spin41 = (Spinner) findViewById(R.id.spinner_steady_movement_1wk);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.steady_movement_1wk_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin41.setAdapter(adapter);
        spin41.setSelection(((MyApplication) this.getApplication()).getSteady_movement_1wk_option());
        spin41.setOnItemSelectedListener(this);

        Spinner spin42 = (Spinner) findViewById(R.id.spinner_3_month_high);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_3month_high, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin42.setAdapter(adapter);
        spin42.setSelection(((MyApplication) this.getApplication()).getNear_3month_high_option());
        spin42.setOnItemSelectedListener(this);

        Spinner spin43 = (Spinner) findViewById(R.id.spinner_near_resistance_daily_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_resistance_daily_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin43.setAdapter(adapter);
        spin43.setSelection(((MyApplication) this.getApplication()).getNear_resistance_daily_above_option());
        spin43.setOnItemSelectedListener(this);

        Spinner spin44 = (Spinner) findViewById(R.id.spinner_near_resistance_daily_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_resistance_daily_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin44.setAdapter(adapter);
        spin44.setSelection(((MyApplication) this.getApplication()).getNear_resistance_daily_below_option());
        spin44.setOnItemSelectedListener(this);

        Spinner spin45 = (Spinner) findViewById(R.id.spinner_near_support_daily_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_support_daily_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin45.setAdapter(adapter);
        spin45.setSelection(((MyApplication) this.getApplication()).getNear_support_daily_above_option());
        spin45.setOnItemSelectedListener(this);

        Spinner spin46 = (Spinner) findViewById(R.id.spinner_near_support_daily_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.near_support_daily_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin46.setAdapter(adapter);
        spin46.setSelection(((MyApplication) this.getApplication()).getNear_support_daily_below_option());
        spin46.setOnItemSelectedListener(this);

        Spinner spin47 = (Spinner) findViewById(R.id.spinner_parabolic_hour);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.parabolic_1h_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin47.setAdapter(adapter);
        spin47.setSelection(((MyApplication) this.getApplication()).getParabolic_1h_option());
        spin47.setOnItemSelectedListener(this);

        Spinner spin48 = (Spinner) findViewById(R.id.spinner_steady_movement_intraday);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.steady_movement_intraday_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin48.setAdapter(adapter);
        spin48.setSelection(((MyApplication) this.getApplication()).getSteady_movement_intraday_option());
        spin48.setOnItemSelectedListener(this);

        Spinner spin49 = (Spinner) findViewById(R.id.spinner_volatility_intraday_above);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.intraday_volatility_above_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin49.setAdapter(adapter);
        spin49.setSelection(((MyApplication) this.getApplication()).getVolatility_intraday_above_option());
        spin49.setOnItemSelectedListener(this);

        Spinner spin50 = (Spinner) findViewById(R.id.spinner_volatility_intraday_below);
        adapter = ArrayAdapter.createFromResource(ScreenerSettingsActivity.this, R.array.intraday_volatility_below_options, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin50.setAdapter(adapter);
        spin50.setSelection(((MyApplication) this.getApplication()).getVolatility_below_option());
        spin50.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getId() == R.id.spinner_price_above){
            ((MyApplication) this.getApplication()).setPrice_above_option(pos);
        }
        else if (parent.getId() == R.id.spinner_market_cap_above){
            ((MyApplication) this.getApplication()).setMarket_cap_above_option(pos);
        }
        else if (parent.getId() == R.id.spinner_rel_volume_above){
            ((MyApplication) this.getApplication()).setRel_volume_above_option(pos);
        }
        else if (parent.getId() == R.id.spinner_percent_above){
            ((MyApplication) this.getApplication()).setPercent_above_option(pos);
        }
        else if (parent.getId() == R.id.spinner_volatility_above){
            ((MyApplication) this.getApplication()).setVolatility_above_option(pos);
        }
        else if (parent.getId() == R.id.spinner_rel_volatility_above){
            ((MyApplication) this.getApplication()).setRel_volatility_above_option(pos);
        }
        else if (parent.getId() == R.id.spinner_volume_above){
            ((MyApplication) this.getApplication()).setVolume_above_option(pos);
        }
       /* else if (parent.getId() == R.id.spinner_avg_volume_above){
            ((MyApplication) this.getApplication()).setAvg_volume_above_option(pos);
        }*/
        else if (parent.getId() == R.id.spinner_sort_by){
            ((MyApplication) this.getApplication()).setSort_by_option(pos);
        }
        // BELOW!!
        else if (parent.getId() == R.id.spinner_price_below){
            ((MyApplication) this.getApplication()).setPrice_below_option(pos);
        }
        else if (parent.getId() == R.id.spinner_market_cap_below){
            ((MyApplication) this.getApplication()).setMarket_cap_below_option(pos);
        }
        else if (parent.getId() == R.id.spinner_rel_volume_below){
            ((MyApplication) this.getApplication()).setRel_volume_below_option(pos);
        }
        else if (parent.getId() == R.id.spinner_percent_below){
            ((MyApplication) this.getApplication()).setPercent_below_option(pos);
        }
        else if (parent.getId() == R.id.spinner_volatility_below){
            ((MyApplication) this.getApplication()).setVolatility_below_option(pos);
        }
        else if (parent.getId() == R.id.spinner_rel_volatility_below){
            ((MyApplication) this.getApplication()).setRel_volatility_below_option(pos);
        }
        else if (parent.getId() == R.id.spinner_volume_below){
            ((MyApplication) this.getApplication()).setVolume_below_option(pos);
        }
      /*  else if (parent.getId() == R.id.spinner_avg_volume_below){
            ((MyApplication) this.getApplication()).setAvg_volume_below_option(pos);
        }*/
        else if (parent.getId() == R.id.spinner_sort_by){
            ((MyApplication) this.getApplication()).setSort_by_option(pos);
        }
        // If not premium then don't change these and show message
        if (free == true){
            if (parent.getId() == R.id.spinner_3_month_high){
                Spinner spin = findViewById(R.id.spinner_3_month_high);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volume_4rel_3_below){
                Spinner spin = findViewById(R.id.spinner_volume_4rel_3_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volume_3rel_2_below){
                Spinner spin = findViewById(R.id.spinner_volume_3rel_2_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volume_2rel_1_below){
                Spinner spin = findViewById(R.id.spinner_volume_2rel_1_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_percent_4rel_3_below){
                Spinner spin = findViewById(R.id.spinner_percent_4rel_3_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_percent_3rel_2_below){
                Spinner spin = findViewById(R.id.spinner_percent_3rel_2_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_percent_2rel_1_below) {
                Spinner spin = findViewById(R.id.spinner_percent_2rel_1_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volume_4rel_3_above){
                Spinner spin = findViewById(R.id.spinner_volume_4rel_3_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volume_3rel_2_above){
                Spinner spin = findViewById(R.id.spinner_volume_3rel_2_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volume_2rel_1_above){
                Spinner spin = findViewById(R.id.spinner_volume_2rel_1_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_percent_4rel_3_above){
                Spinner spin = findViewById(R.id.spinner_percent_4rel_3_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_percent_3rel_2_above){
                Spinner spin = findViewById(R.id.spinner_percent_3rel_2_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_percent_2rel_1_above) {
                Spinner spin = findViewById(R.id.spinner_percent_2rel_1_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_3month_below) {
                Spinner spin = findViewById(R.id.spinner_near_resistance_3month_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_3month_above) {
                Spinner spin = findViewById(R.id.spinner_near_resistance_3month_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_1month_below) {
                Spinner spin = findViewById(R.id.spinner_near_resistance_1month_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_1month_above) {
                Spinner spin = findViewById(R.id.spinner_near_resistance_1month_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_daily_below){
                Spinner spin = findViewById(R.id.spinner_near_resistance_daily_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_daily_above){
                Spinner spin = findViewById(R.id.spinner_near_resistance_daily_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_support_3month_below) {
                Spinner spin = findViewById(R.id.spinner_near_support_3month_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_support_3month_above) {
                Spinner spin = findViewById(R.id.spinner_near_support_3month_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_support_1month_below) {
                Spinner spin = findViewById(R.id.spinner_near_support_1month_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_support_1month_above) {
                Spinner spin = findViewById(R.id.spinner_near_support_1month_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_support_daily_below){
                Spinner spin = findViewById(R.id.spinner_near_support_daily_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_near_support_daily_above){
                Spinner spin = findViewById(R.id.spinner_near_support_daily_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_parabolic_month){
                Spinner spin = findViewById(R.id.spinner_parabolic_month);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_parabolic_week){
                Spinner spin = findViewById(R.id.spinner_parabolic_week);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_parabolic_hour){
                Spinner spin = findViewById(R.id.spinner_parabolic_hour);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_steady_movement_1M){
                Spinner spin = findViewById(R.id.spinner_steady_movement_1M);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_steady_movement_1wk){
                Spinner spin = findViewById(R.id.spinner_steady_movement_1wk);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_steady_movement_intraday){
                Spinner spin = findViewById(R.id.spinner_steady_movement_intraday);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volatility_intraday_below){
                Spinner spin = findViewById(R.id.spinner_volatility_intraday_below);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
            else if (parent.getId() == R.id.spinner_volatility_intraday_above){
                Spinner spin = findViewById(R.id.spinner_volatility_intraday_above);
                if (pos != 0){
					Toast.makeText(ScreenerSettingsActivity.this, "Buy Premium to access this feature", Toast.LENGTH_LONG).show();
				}
				spin.setSelection(0);
            }
        }
        // If they have premium let them change the spinners
        else{
            if (parent.getId() == R.id.spinner_3_month_high){
                ((MyApplication) this.getApplication()).setNear_3month_high_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volume_4rel_3_below){
                ((MyApplication) this.getApplication()).setVol_4rel_3_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volume_3rel_2_below){
                ((MyApplication) this.getApplication()).setVol_3rel_2_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volume_2rel_1_below){
                ((MyApplication) this.getApplication()).setVol_2rel_1_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_percent_4rel_3_below){
                ((MyApplication) this.getApplication()).setPercent_4rel_3_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_percent_3rel_2_below){
                ((MyApplication) this.getApplication()).setPercent_3rel_2_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_percent_2rel_1_below) {
                ((MyApplication) this.getApplication()).setPercent_2rel_1_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volume_4rel_3_above){
                ((MyApplication) this.getApplication()).setVol_4rel_3_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volume_3rel_2_above){
                ((MyApplication) this.getApplication()).setVol_3rel_2_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volume_2rel_1_above){
                ((MyApplication) this.getApplication()).setVol_2rel_1_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_percent_4rel_3_above){
                ((MyApplication) this.getApplication()).setPercent_4rel_3_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_percent_3rel_2_above){
                ((MyApplication) this.getApplication()).setPercent_3rel_2_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_percent_2rel_1_above) {
                ((MyApplication) this.getApplication()).setPercent_2rel_1_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_3month_below) {
                ((MyApplication) this.getApplication()).setNear_resistance_3M_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_3month_above) {
                ((MyApplication) this.getApplication()).setNear_resistance_3M_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_1month_below) {
                ((MyApplication) this.getApplication()).setNear_resistance_1M_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_1month_above) {
                ((MyApplication) this.getApplication()).setNear_resistance_1M_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_daily_below){
                ((MyApplication) this.getApplication()).setNear_resistance_daily_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_resistance_daily_above){
                ((MyApplication) this.getApplication()).setNear_resistance_daily_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_support_3month_below) {
                ((MyApplication) this.getApplication()).setNear_support_3M_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_support_3month_above) {
                ((MyApplication) this.getApplication()).setNear_support_3M_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_support_1month_below) {
                ((MyApplication) this.getApplication()).setNear_support_1M_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_support_1month_above) {
                ((MyApplication) this.getApplication()).setNear_support_1M_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_support_daily_below){
                ((MyApplication) this.getApplication()).setNear_support_daily_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_near_support_daily_above){
                ((MyApplication) this.getApplication()).setNear_support_daily_above_option(pos);
            }
            else if (parent.getId() == R.id.spinner_parabolic_month){
                ((MyApplication) this.getApplication()).setParabolic_1M_option(pos);
            }
            else if (parent.getId() == R.id.spinner_parabolic_week){
                ((MyApplication) this.getApplication()).setParabolic_1wk_option(pos);
            }
            else if (parent.getId() == R.id.spinner_parabolic_hour){
                ((MyApplication) this.getApplication()).setParabolic_1h_option(pos);
            }
            else if (parent.getId() == R.id.spinner_steady_movement_1M){
                ((MyApplication) this.getApplication()).setSteady_movement_1M_option(pos);
            }
            else if (parent.getId() == R.id.spinner_steady_movement_1wk){
                ((MyApplication) this.getApplication()).setSteady_movement_1wk_option(pos);
            }
            else if (parent.getId() == R.id.spinner_steady_movement_intraday){
                ((MyApplication) this.getApplication()).setSteady_movement_intraday_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volatility_intraday_below){
                ((MyApplication) this.getApplication()).setVolatility_intraday_below_option(pos);
            }
            else if (parent.getId() == R.id.spinner_volatility_intraday_above){
                ((MyApplication) this.getApplication()).setVolatility_intraday_above_option(pos);
            }
        }
        
        

        Button button_save = (Button) findViewById(R.id.screener_save_settings);
        button_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                View view = LayoutInflater.from(ScreenerSettingsActivity.this).inflate(R.layout.name_alert, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ScreenerSettingsActivity.this);
                final EditText save_name = (EditText) view.findViewById(R.id.screener_save_name_edit_text);
                final CheckBox default_check_box = (CheckBox) view.findViewById(R.id.default_check_box);

                default_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (free == true){
                            if (isChecked == true){
                                Toast.makeText(ScreenerSettingsActivity.this, "Premium Required", Toast.LENGTH_LONG).show();
                                default_check_box.setChecked(false);
                            }
                        }
                    }
                });

                builder.setMessage("Enter Save Name")
                        .setView(view)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (((MyApplication)getApplication()).saveSettings(save_name.getText().toString())){
                                    if (default_check_box.isChecked()){
                                        ((MyApplication)getApplication()).saveSettings("Default");
                                    }
                                    Toast.makeText(ScreenerSettingsActivity.this, "Save Successful", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(ScreenerSettingsActivity.this, "Name Already Exists, Try Again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        Button button_run = (Button) findViewById(R.id.screener_run_screener);
        button_run.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ScreenerSettingsActivity.this, ScreenerActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
