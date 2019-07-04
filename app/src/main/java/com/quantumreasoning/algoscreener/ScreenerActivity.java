package com.quantumreasoning.algoscreener;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class ScreenerActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    private String[][] ticker_info;
    private boolean free;
    private FirebaseDatabaseHelper fire_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screener);


        MobileAds.initialize(this, "ca-app-pub-5164013549454719~8345618526");
        fire_db = new FirebaseDatabaseHelper(this);

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
        menu_screener.setForeground(clicked);
        menu_my_watchlist.setForeground(null);
        menu_premium_watchlist.setForeground(null);

        final Button menu_button_home = (Button) findViewById(R.id.menu_button_home);
        menu_button_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScreenerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_my_watchlist = (Button) findViewById(R.id.menu_button_my_watchlist);
        menu_button_my_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScreenerActivity.this, MyWatchlistActivity.class);
                startActivity(intent);
            }
        });
        final Button menu_button_premium_watchlist = (Button) findViewById(R.id.menu_button_premium_watchlist);
        menu_button_premium_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (free) {
                    Toast.makeText(ScreenerActivity.this, "Buy Premium to access the Premium Watchlist", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(ScreenerActivity.this, PremiumWatchlistActivity.class);
                    startActivity(intent);
                }
            }
        });
        final Button screener_button_settings = (Button) findViewById(R.id.screener_settings);
        screener_button_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScreenerActivity.this, ScreenerSettingsActivity.class);
                startActivity(intent);
            }
        });

        Button ticker1 = (Button)findViewById(R.id.screener_ticker1);
        ticker1.setText("Analyzing data. Please wait...");

        final LocalDatabaseHelper local_db = new LocalDatabaseHelper(this);

        final Button screener_button_load_settings = (Button) findViewById(R.id.screener_load_settings);
        screener_button_load_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View view = LayoutInflater.from(ScreenerActivity.this).inflate(R.layout.load_alert, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ScreenerActivity.this);
                final Spinner load_spin = (Spinner) view.findViewById(R.id.screener_load_settings_spinner);
                final CheckBox default_check_box = (CheckBox) view.findViewById(R.id.default_check_box);

                default_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (free){
                            if (isChecked){
                                // Display buy premium message
                                Toast.makeText(ScreenerActivity.this, "Buy Premium to save default settings", Toast.LENGTH_LONG).show();
                                default_check_box.setChecked(false);
                            }
                        }
                    }
                });
                // ***** Create Name List for Spinner ***** //
                ArrayList<CharSequence> array_list = new ArrayList<>();
                Cursor data = local_db.getTableData("user_screener_settings");
                if (data.getCount() == 0){
                    // Not found
                }
                else {
                    while (data.moveToNext()) {
                        if (!(data.getString(1).equals("Default"))){
                            array_list.add(data.getString(1));
                        }
                    }
                }
                ArrayAdapter<CharSequence> array_adapter = new ArrayAdapter<CharSequence>(ScreenerActivity.this, android.R.layout.simple_list_item_1, array_list);
                load_spin.setAdapter(array_adapter);
                // ***************************************** //
                builder.setMessage("Choose Settings to Load")
                        .setView(view)
                        .setPositiveButton("Load", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (load_spin.getSelectedItem() != null) {
                                    if (((MyApplication) getApplication()).SettingsFromString(local_db.getScreenerSettingsString(load_spin.getSelectedItem().toString()))) {
                                        if (default_check_box.isChecked()) {
                                            ((MyApplication) getApplication()).saveSettings("Default");
                                        }
                                        Toast.makeText(ScreenerActivity.this, "Load Successful", Toast.LENGTH_LONG).show();
                                    }
                                }
                                else{
                                    Toast.makeText(ScreenerActivity.this, "Error Loading, Try Again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (load_spin.getSelectedItem() != null) {
                                    local_db.deleteScreenerSettings(load_spin.getSelectedItem().toString());
                                    Toast.makeText(ScreenerActivity.this, "Successfully Deleted " + load_spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        Button button_save = (Button) findViewById(R.id.screener_save_settings);
        button_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                View view = LayoutInflater.from(ScreenerActivity.this).inflate(R.layout.name_alert, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ScreenerActivity.this);
                final EditText save_name = (EditText) view.findViewById(R.id.screener_save_name_edit_text);
                final CheckBox default_check_box = (CheckBox) view.findViewById(R.id.default_check_box);

                default_check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (free == true){
                            if (isChecked == true){
                                // Display buy premium message
                                Toast.makeText(ScreenerActivity.this, "Buy Premium to save default settings", Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(ScreenerActivity.this, "Save Successful", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(ScreenerActivity.this, "Name Already Exists, Try Again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        setTickerInfoListeners();
        fire_db.extractMatchedCriteriaTickers((MyApplication)getApplication());

        //NOW DONE FROM FBD HELPER  for(int i = 0; i < tickers.size(); i++){
            //
        //}
        
        //new FirebaseDatabaseHelper(this, "AAPL", R.id.screener_ticker1);

        // getScreenerSettings();
        // get Tickers
        // loop through tickers and get info for each

    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }


    public void setTickerInfoListeners(){
        final Button price = (Button) findViewById(R.id.ticker_info_price);
        final Button volume = (Button) findViewById(R.id.ticker_info_volume);
        final Button rel_volume = (Button) findViewById(R.id.ticker_info_rel_volume);
        final Button market_cap = (Button) findViewById(R.id.ticker_info_market_cap);
        final Button volatility = (Button) findViewById(R.id.ticker_info_volatility);
        final Button percent = (Button) findViewById(R.id.ticker_info_percent);

        int sort = ((MyApplication)getApplication()).getSort_by_option();
        if (sort == 0){
            price.setText("\nPrice\n\u25BC");
        }
        else if (sort == 1){
            market_cap.setText("\nMarket\nCap\n\u25BC");
        }
        else if (sort == 2){
            rel_volume.setText("\nRelative\nVolume\n\u25BC");
        }
        else if (sort == 3){
            volume.setText("\nVolume\n\u25BC");
        }
        else if (sort == 4){
            volatility.setText("\nVolatil.\n\u25BC");
        }
        else if (sort == 5){
            percent.setText("\nPercent\nChange\n\u25BC");
        }
        else if (sort == 6){
            price.setText("\nPrice\n\u25B2");
        }
        else if (sort == 7){
            market_cap.setText("\nMarket\nCap\n\u25B2");
        }
        else if (sort == 8){
            rel_volume.setText("\nRelative\nVolume\n\u25B2");
        }
        else if (sort == 9){
            volume.setText("\nVolume\n\u25B2");
        }
        else if (sort == 10){
            volatility.setText("\nVolatil.\n\u25B2");
        }
        else if (sort == 11){
            percent.setText("\nPercent\nChange\n\u25B2");
        }

        price.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (price.getText().toString().contains("\u25B2")){
                    ((MyApplication)getApplication()).setSort_by_option(0);
                    recreate();
                }
                else if (price.getText().toString().contains("\u25BC")){
                    ((MyApplication)getApplication()).setSort_by_option(6);
                    recreate();
                }
                else{
                    ((MyApplication)getApplication()).setSort_by_option(0);
                    recreate();
                }
            }
        });

        volume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (volume.getText().toString().contains("\u25B2")){
                    ((MyApplication)getApplication()).setSort_by_option(3);
                    recreate();
                }
                else if (volume.getText().toString().contains("\u25BC")){
                    ((MyApplication)getApplication()).setSort_by_option(9);
                    recreate();
                }
                else{
                    ((MyApplication)getApplication()).setSort_by_option(3);
                    recreate();
                }
            }
        });

        rel_volume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (rel_volume.getText().toString().contains("\u25B2")){
                    ((MyApplication)getApplication()).setSort_by_option(2);
                    recreate();
                }
                else if (rel_volume.getText().toString().contains("\u25BC")){
                    ((MyApplication)getApplication()).setSort_by_option(8);
                    recreate();
                }
                else{
                    ((MyApplication)getApplication()).setSort_by_option(2);
                    recreate();
                }
            }
        });

        market_cap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (market_cap.getText().toString().contains("\u25B2")){
                    ((MyApplication)getApplication()).setSort_by_option(1);
                    recreate();
                }
                else if (market_cap.getText().toString().contains("\u25BC")){
                    ((MyApplication)getApplication()).setSort_by_option(7);
                    recreate();
                }
                else{
                    ((MyApplication)getApplication()).setSort_by_option(1);
                    recreate();
                }
            }
        });

        volatility.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (volatility.getText().toString().contains("\u25B2")){
                    ((MyApplication)getApplication()).setSort_by_option(4);
                    recreate();
                }
                else if (volatility.getText().toString().contains("\u25BC")){
                    ((MyApplication)getApplication()).setSort_by_option(10);
                    recreate();
                }
                else{
                    ((MyApplication)getApplication()).setSort_by_option(4);
                    recreate();
                }
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (percent.getText().toString().contains("\u25B2")){
                    ((MyApplication)getApplication()).setSort_by_option(5);
                    recreate();
                }
                else if (percent.getText().toString().contains("\u25BC")){
                    ((MyApplication)getApplication()).setSort_by_option(11);
                    recreate();
                }
                else{
                    ((MyApplication)getApplication()).setSort_by_option(5);
                    recreate();
                }
            }
        });
    }

}
