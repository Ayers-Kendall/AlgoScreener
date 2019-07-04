package com.quantumreasoning.algoscreener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.type.ArrayType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Kendall on 10/9/2017.
 */

public class FirebaseDatabaseHelper {

    private DatabaseReference mDatabase;
    private String data;
    private String[][] output;
    public Context context;
    public int[] button_ids;
    private boolean sortListDone;

    public FirebaseDatabaseHelper(Context context){
        this.context = context;
        button_ids = new int[25];
        button_ids[0] = R.id.screener_ticker1;
        button_ids[1] = R.id.screener_ticker2;
        button_ids[2] = R.id.screener_ticker3;
        button_ids[3] = R.id.screener_ticker4;
        button_ids[4] = R.id.screener_ticker5;
        button_ids[5] = R.id.screener_ticker6;
        button_ids[6] = R.id.screener_ticker7;
        button_ids[7] = R.id.screener_ticker8;
        button_ids[8] = R.id.screener_ticker9;
        button_ids[9] = R.id.screener_ticker10;
        button_ids[10] = R.id.screener_ticker11;
        button_ids[11] = R.id.screener_ticker12;
        button_ids[12] = R.id.screener_ticker13;
        button_ids[13] = R.id.screener_ticker14;
        button_ids[14] = R.id.screener_ticker15;
        button_ids[15] = R.id.screener_ticker16;
        button_ids[16] = R.id.screener_ticker17;
        button_ids[17] = R.id.screener_ticker18;
        button_ids[18] = R.id.screener_ticker19;
        button_ids[19] = R.id.screener_ticker20;
        button_ids[20] = R.id.screener_ticker21;
        button_ids[21] = R.id.screener_ticker22;
        button_ids[22] = R.id.screener_ticker23;
        button_ids[23] = R.id.screener_ticker24;
        button_ids[24] = R.id.screener_ticker25;
    }

    public FirebaseDatabaseHelper(Context context, int[] btn_ids){
        this.context = context;
        button_ids = btn_ids;
    }

    public String[][] getTickerData(final int btn_id, final String ticker, final String type){
        Handler mHandler = new Handler();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("root/historic/" + ticker);
        output = new String[2][7];
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    data = dataSnapshot.getValue().toString();
                    output = extractTickerData(data, ticker);
                }

                ((Button) ((Activity)context).findViewById(btn_id)).setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                ((Button) ((Activity)context).findViewById(btn_id)).setText(createSpannableString(output));
                set_listener(context, btn_id, ticker, type);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("CANCELLED");System.out.println("CANCELLED");
            }
        });
        return output;
    }

    private String[] title_body;

    public void getOpenMessage(){
        Handler mHandler = new Handler();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("root/information/open_message");
        title_body = new String[2];
        title_body[0] = "none";
        title_body[1] = "none";
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    data = dataSnapshot.getValue().toString();
                    if ((!(data.contains("none"))) && data.indexOf(']') != -1){
                        title_body[0] = data.substring(data.indexOf("'")+1, data.indexOf(']')-1).replace("\\n", "\n");
                        title_body[1] = data.substring(data.indexOf("'", data.indexOf(']'))+1, data.indexOf(']', data.indexOf(']')+1)-1).replace("\\n", "\n");
                        Intent intent = new Intent(context, MessageActivity.class);
                        intent.putExtra("title", title_body[0]);
                        intent.putExtra("body", title_body[1]);
                        context.startActivity(intent);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("CANCELLED");System.out.println("CANCELLED");
            }
        });
    }

    private String msg_of_day = "";
    public void getMessageOfDay(){
        Handler mHandler = new Handler();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("root/information/message_of_the_day");

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    data = dataSnapshot.getValue().toString();
                    if (!(data.contains("none"))) {
                        msg_of_day = data;
                        View v = ((View) ((Activity) context).findViewById(R.id.top_banner_divider));
                        v.setBackgroundColor(Color.parseColor("#2aa83d"));
                        v = ((View) ((Activity) context).findViewById(R.id.bottom_banner_divider));
                        v.setBackgroundColor(Color.parseColor("#2aa83d"));
                    }
                    else {
                        msg_of_day = "";
                    }
                    TextView text = ((TextView) ((Activity) context).findViewById(R.id.message_of_the_day));
                    text.setText(msg_of_day);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("CANCELLED");System.out.println("CANCELLED");
            }
        });
    }
    
    private String data_string;
    public ArrayList<String> searchList(final String list_string, final ArrayList<String> inputs, final double upperBound, final double lowerBound, final boolean highToLow){
        ArrayList<String> out = new ArrayList<String>();
        ArrayList<String> t = new ArrayList<String>();
        // List String Retrieved. Now Parse
        Log.d("Testing", " -- " + inputs.toString());
        if (list_string == null){
            //Log.d("LIST", " null list");}
        }
        else{
            for (int i = 1; i < list_string.length(); i++) {
                i = list_string.indexOf("'", i);
                if (i == -1) break;
                String ticker = list_string.substring(i + 1, list_string.indexOf("'", i + 1));
                i = list_string.indexOf(",", i);
                if (i == -1) break;
                double num;
                try {
                    num = Double.valueOf(list_string.substring(i + 1, list_string.indexOf("]", i + 1)).trim());
                }
                catch(Exception e){
                    num = -99999999999999999999d;
                }
                i = list_string.indexOf("]", i + 1);
                if (i == -1) break;
                //Log.d("LIST", "lower = " + lowerBound + " upper = " + upperBound);
                if (num <= upperBound && num >= lowerBound){
                    t.add(ticker);
                }
                if (highToLow == false){
                    Collections.reverse(t);
                }
                //Log.d("LIST", ticker + " " + num + "   i = " + i);
            }
        }
        for (int i = 0; i < inputs.size(); i++){
            if(t.contains(inputs.get(i))){
                out.add(inputs.get(i));
            }
        }
        if (inputs.size() == 0 && sortListDone == false){
            return t;
        }
        //Log.d("CrossedList", out.toString());
        return out;
    }

    private String listToExclude = null;
    private double price_above_num = 0;
    private double price_below_num = 0;
    private double market_cap_above_num = 0;
    private double market_cap_below_num = 0;
    private double rel_volume_above_num = 0;
    private double rel_volume_below_num = 0;
    private double percent_above_num = 0;
    private double percent_below_num = 0;
    private double volatility_above_num = 0;
    private double volatility_below_num = 0;
    private double rel_volatility_above_num = 0;
    private double rel_volatility_below_num = 0;
    private double volume_above_num = 0;
    private double volume_below_num = 0;
    private double avg_volume_above_num = 0;
    private double avg_volume_below_num = 0;
    private double vol_4rel_3_above_num = 0;
    private double vol_4rel_3_below_num = 0;
    private double vol_3rel_2_above_num= 0;
    private double vol_3rel_2_below_num= 0;
    private double vol_2rel_1_above_num= 0;
    private double vol_2rel_1_below_num= 0;
    private double percent_4rel_3_above_num = 0;
    private double percent_4rel_3_below_num = 0;
    private double percent_3rel_2_above_num = 0;
    private double percent_3rel_2_below_num = 0;
    private double percent_2rel_1_above_num = 0;
    private double percent_2rel_1_below_num = 0;
    private double near_3month_high_num = 0;
    private double near_resistance_daily_above_num = 0;
    private double near_resistance_daily_below_num = 0;
    private double near_resistance_1M_above_num = 0;
    private double near_resistance_1M_below_num = 0;
    private double near_resistance_3M_above_num = 0;
    private double near_resistance_3M_below_num = 0;
    private double sort_by_num = 0;
    private double near_support_daily_above_num = 0;
    private double near_support_daily_below_num = 0;
    private double near_support_1M_above_num = 0;
    private double near_support_1M_below_num = 0;
    private double near_support_3M_above_num = 0;
    private double near_support_3M_below_num = 0;
    private double parabolic_1h_num = 0;
    private double parabolic_1wk_num = 0;
    private double parabolic_1M_num = 0;
    private double steady_movement_1M_num = 0;
    private double steady_movement_1wk_num = 0;
    private double steady_movement_intraday_num = 0;
    private double volatility_intraday_above_num = 0;
    private double volatility_intraday_below_num = 0;
    private ArrayList<String> temp = new ArrayList<>();
    
    public void extractMatchedCriteriaTickers(final MyApplication app){

        if (app.getPrice_above_option() == 0){price_above_num = -9999999999999999d;}
        else if (app.getPrice_above_option() == 1){price_above_num = 75.0;}
        else if (app.getPrice_above_option() == 2){price_above_num = 50.0;}
        else if (app.getPrice_above_option() == 3){price_above_num = 30.0;}
        else if (app.getPrice_above_option() == 4){price_above_num = 15.0;}
        else if (app.getPrice_above_option() == 5){price_above_num = 10.0;}
        else if (app.getPrice_above_option() == 6){price_above_num = 5.0;}
        else if (app.getPrice_above_option() == 7){price_above_num = 3.0;}
        else if (app.getPrice_above_option() == 8){price_above_num = 1.0;}
        if (app.getPrice_below_option() == 0){price_below_num = 9999999999999999d;}
        else if (app.getPrice_below_option() == 1){price_below_num = 75.0;}
        else if (app.getPrice_below_option() == 2){price_below_num = 50.0;}
        else if (app.getPrice_below_option() == 3){price_below_num = 30.0;}
        else if (app.getPrice_below_option() == 4){price_below_num = 15.0;}
        else if (app.getPrice_below_option() == 5){price_below_num = 10.0;}
        else if (app.getPrice_below_option() == 6){price_below_num = 5.0;}
        else if (app.getPrice_below_option() == 7){price_below_num = 3.0;}
        else if (app.getPrice_below_option() == 8){price_below_num = 1.0;}

        if (app.getMarket_cap_above_option() == 0){market_cap_above_num = -9999999999999999d;}
        else if (app.getMarket_cap_above_option() == 1){market_cap_above_num = 100000000000.0;}
        else if (app.getMarket_cap_above_option() == 2){market_cap_above_num = 10000000000.0;}
        else if (app.getMarket_cap_above_option() == 3){market_cap_above_num = 5000000000.0;}
        else if (app.getMarket_cap_above_option() == 4){market_cap_above_num = 1000000000.0;}
        else if (app.getMarket_cap_above_option() == 5){market_cap_above_num = 500000000.0;}
        else if (app.getMarket_cap_above_option() == 6){market_cap_above_num = 300000000.0;}
        else if (app.getMarket_cap_above_option() == 7){market_cap_above_num = 100000000.0;}
        if (app.getMarket_cap_below_option() == 0){market_cap_below_num = 9999999999999999d;}
        else if (app.getMarket_cap_below_option() == 1){market_cap_below_num = 100000000000.0;}
        else if (app.getMarket_cap_below_option() == 2){market_cap_below_num = 10000000000.0;}
        else if (app.getMarket_cap_below_option() == 3){market_cap_below_num = 5000000000.0;}
        else if (app.getMarket_cap_below_option() == 4){market_cap_below_num = 1000000000.0;}
        else if (app.getMarket_cap_below_option() == 5){market_cap_below_num = 500000000.0;}
        else if (app.getMarket_cap_below_option() == 6){market_cap_below_num = 300000000.0;}
        else if (app.getMarket_cap_below_option() == 7){market_cap_below_num = 100000000.0;}

        if (app.getRel_volume_above_option() == 0){rel_volume_above_num = -9999999999999999d;}
        else if (app.getRel_volume_above_option() == 1){rel_volume_above_num = 10.0;}
        else if (app.getRel_volume_above_option() == 2){rel_volume_above_num = 5.0;}
        else if (app.getRel_volume_above_option() == 3){rel_volume_above_num = 3.0;}
        else if (app.getRel_volume_above_option() == 4){rel_volume_above_num = 2.0;}
        else if (app.getRel_volume_above_option() == 5){rel_volume_above_num = 1.75;}
        else if (app.getRel_volume_above_option() == 6){rel_volume_above_num = 1.5;}
        else if (app.getRel_volume_above_option() == 7){rel_volume_above_num = 1.25;}
        else if (app.getRel_volume_above_option() == 8){rel_volume_above_num = 1.0;}
        if (app.getRel_volume_below_option() == 0){rel_volume_below_num = 9999999999999999d;}
        else if (app.getRel_volume_below_option() == 1){rel_volume_below_num = 2.0;}
        else if (app.getRel_volume_below_option() == 1){rel_volume_below_num = 1.75;}
        else if (app.getRel_volume_below_option() == 1){rel_volume_below_num = 1.5;}
        else if (app.getRel_volume_below_option() == 2){rel_volume_below_num = 1.25;}
        else if (app.getRel_volume_below_option() == 3){rel_volume_below_num = 1.0;}
        else if (app.getRel_volume_below_option() == 4){rel_volume_below_num = .75;}
        else if (app.getRel_volume_below_option() == 1){rel_volume_below_num = .5;}

        if (app.getPercent_above_option() == 0){percent_above_num = -9999999999999999d;}
        else if (app.getPercent_above_option() == 1){percent_above_num = 20.0;}
        else if (app.getPercent_above_option() == 2){percent_above_num = 10.0;}
        else if (app.getPercent_above_option() == 3){percent_above_num = 7.5;}
        else if (app.getPercent_above_option() == 4){percent_above_num = 5.0;}
        else if (app.getPercent_above_option() == 5){percent_above_num = 4.0;}
        else if (app.getPercent_above_option() == 6){percent_above_num = 3.0;}
        else if (app.getPercent_above_option() == 7){percent_above_num = 2.0;}
        else if (app.getPercent_above_option() == 8){percent_above_num = 1.5;}
        else if (app.getPercent_above_option() == 9){percent_above_num = 1.0;}
        else if (app.getPercent_above_option() == 10){percent_above_num = 0.5;}
        else if (app.getPercent_above_option() == 11){percent_above_num = 0.0;}
        else if (app.getPercent_above_option() == 12){percent_above_num = -1.0;}
        else if (app.getPercent_above_option() == 13){percent_above_num = -2.0;}
        else if (app.getPercent_above_option() == 14){percent_above_num = -5.0;}
        if (app.getPercent_below_option() == 0){percent_below_num = 9999999999999999d;}
        else if (app.getPercent_below_option() == 1){percent_below_num = 10.0;}
        else if (app.getPercent_below_option() == 2){percent_below_num = 7.5;}
        else if (app.getPercent_below_option() == 3){percent_below_num = 5.0;}
        else if (app.getPercent_below_option() == 4){percent_below_num = 2.0;}
        else if (app.getPercent_below_option() == 5){percent_below_num = 1.0;}
        else if (app.getPercent_below_option() == 6){percent_below_num = 0.0;}
        else if (app.getPercent_below_option() == 7){percent_below_num = -1.0;}
        else if (app.getPercent_below_option() == 8){percent_below_num = -1.5;}
        else if (app.getPercent_below_option() == 9){percent_below_num = -2.0;}
        else if (app.getPercent_below_option() == 10){percent_below_num = -3.0;}
        else if (app.getPercent_below_option() == 11){percent_below_num = -4.0;}
        else if (app.getPercent_below_option() == 12){percent_below_num = -5.0;}
        else if (app.getPercent_below_option() == 13){percent_below_num = -10.0;}
        else if (app.getPercent_below_option() == 14){percent_below_num = -20.0;}

        if (app.getVolatility_above_option() == 0){volatility_above_num = -9999999999999999d;}
        else if (app.getVolatility_above_option() == 1){volatility_above_num = 20.0;}
        else if (app.getVolatility_above_option() == 2){volatility_above_num = 10.0;}
        else if (app.getVolatility_above_option() == 3){volatility_above_num = 7.5;}
        else if (app.getVolatility_above_option() == 4){volatility_above_num = 5.0;}
        else if (app.getVolatility_above_option() == 5){volatility_above_num = 4.0;}
        else if (app.getVolatility_above_option() == 6){volatility_above_num = 3.0;}
        else if (app.getVolatility_above_option() == 7){volatility_above_num = 2.0;}
        else if (app.getVolatility_above_option() == 8){volatility_above_num = 1.5;}
        else if (app.getVolatility_above_option() == 9){volatility_above_num = 1.0;}
        else if (app.getVolatility_above_option() == 10){volatility_above_num = 0.5;}

        if (app.getVolatility_below_option() == 0){volatility_below_num = 9999999999999999d;}
        else if (app.getVolatility_below_option() == 1){volatility_below_num = 20.0;}
        else if (app.getVolatility_below_option() == 2){volatility_below_num = 10.0;}
        else if (app.getVolatility_below_option() == 3){volatility_below_num = 7.5;}
        else if (app.getVolatility_below_option() == 4){volatility_below_num = 5.0;}
        else if (app.getVolatility_below_option() == 5){volatility_below_num = 4.0;}
        else if (app.getVolatility_below_option() == 6){volatility_below_num = 3.0;}
        else if (app.getVolatility_below_option() == 7){volatility_below_num = 2.0;}
        else if (app.getVolatility_below_option() == 8){volatility_below_num = 1.5;}
        else if (app.getVolatility_below_option() == 8){volatility_below_num = 1.0;}
        else if (app.getVolatility_below_option() == 8){volatility_below_num = 0.5;}


        if (app.getRel_volatility_above_option() == 0){rel_volatility_above_num = -9999999999999999d;}
        else if (app.getRel_volatility_above_option() == 1){rel_volatility_above_num = 10.0;}
        else if (app.getRel_volatility_above_option() == 2){rel_volatility_above_num = 5.0;}
        else if (app.getRel_volatility_above_option() == 3){rel_volatility_above_num = 3.0;}
        else if (app.getRel_volatility_above_option() == 4){rel_volatility_above_num = 2.0;}
        else if (app.getRel_volatility_above_option() == 5){rel_volatility_above_num = 1.5;}
        else if (app.getRel_volatility_above_option() == 6){rel_volatility_above_num = 1.0;}
        else if (app.getRel_volatility_above_option() == 7){rel_volatility_above_num = 0.5;}
        if (app.getRel_volatility_below_option() == 0){rel_volatility_below_num = 9999999999999999d;}
        else if (app.getRel_volatility_below_option() == 1){rel_volatility_below_num = 10.0;}
        else if (app.getRel_volatility_below_option() == 2){rel_volatility_below_num = 5.0;}
        else if (app.getRel_volatility_below_option() == 3){rel_volatility_below_num = 3.0;}
        else if (app.getRel_volatility_below_option() == 4){rel_volatility_below_num = 2.0;}
        else if (app.getRel_volatility_below_option() == 5){rel_volatility_below_num = 1.5;}
        else if (app.getRel_volatility_below_option() == 6){rel_volatility_below_num = 1.0;}
        else if (app.getRel_volatility_below_option() == 7){rel_volatility_below_num = 0.5;}

        if (app.getVolume_above_option() == 0){volume_above_num = -9999999999999999d;}
        else if (app.getVolume_above_option() == 1){volume_above_num = 100000000;}
        else if (app.getVolume_above_option() == 2){volume_above_num = 10000000;}
        else if (app.getVolume_above_option() == 3){volume_above_num = 5000000;}
        else if (app.getVolume_above_option() == 4){volume_above_num = 2000000;}
        else if (app.getVolume_above_option() == 5){volume_above_num = 1000000;}
        else if (app.getVolume_above_option() == 6){volume_above_num = 500000;}
        if (app.getVolume_below_option() == 0){volume_below_num = 9999999999999999d;}
        else if (app.getVolume_below_option() == 1){volume_below_num = 100000000;}
        else if (app.getVolume_below_option() == 2){volume_below_num = 10000000;}
        else if (app.getVolume_below_option() == 3){volume_below_num = 5000000;}
        else if (app.getVolume_below_option() == 4){volume_below_num = 2000000;}
        else if (app.getVolume_below_option() == 5){volume_below_num = 1000000;}
        else if (app.getVolume_below_option() == 6){volume_below_num = 500000;}

        if (app.getRel_volume_above_option() == 0){rel_volume_above_num = -9999999999999999d;}
        else if (app.getRel_volume_above_option() == 1){rel_volume_above_num = 10.0;}
        else if (app.getRel_volume_above_option() == 2){rel_volume_above_num = 5.0;}
        else if (app.getRel_volume_above_option() == 3){rel_volume_above_num = 3.0;}
        else if (app.getRel_volume_above_option() == 4){rel_volume_above_num = 2.0;}
        else if (app.getRel_volume_above_option() == 5){rel_volume_above_num = 1.75;}
        else if (app.getRel_volume_above_option() == 6){rel_volume_above_num = 1.5;}
        else if (app.getRel_volume_above_option() == 7){rel_volume_above_num = 1.25;}
        else if (app.getRel_volume_above_option() == 8){rel_volume_above_num = 1.0;}
        else if (app.getRel_volume_above_option() == 9){rel_volume_above_num = 0.75;}
        else if (app.getRel_volume_above_option() == 10){rel_volume_above_num = 0.5;}
        if (app.getRel_volume_below_option() == 0){rel_volume_below_num = 9999999999999999d;}
        else if (app.getRel_volume_below_option() == 1){rel_volume_below_num = 10.0;}
        else if (app.getRel_volume_below_option() == 2){rel_volume_below_num = 5.0;}
        else if (app.getRel_volume_below_option() == 3){rel_volume_below_num = 3.0;}
        else if (app.getRel_volume_below_option() == 4){rel_volume_below_num = 2.0;}
        else if (app.getRel_volume_below_option() == 5){rel_volume_below_num = 1.75;}
        else if (app.getRel_volume_below_option() == 6){rel_volume_below_num = 1.5;}
        else if (app.getRel_volume_below_option() == 7){rel_volume_below_num = 1.25;}
        else if (app.getRel_volume_below_option() == 8){rel_volume_below_num = 1.0;}
        else if (app.getRel_volume_below_option() == 9){rel_volume_below_num = 0.75;}
        else if (app.getRel_volume_below_option() == 10){rel_volume_below_num = 0.5;}

        if (app.getNear_3month_high_option() == 0){near_3month_high_num = 9999999999999999d;}
        else if (app.getNear_3month_high_option() == 1){near_3month_high_num = 0.25;}
        else if (app.getNear_3month_high_option() == 2){near_3month_high_num = 0.5;}
        else if (app.getNear_3month_high_option() == 3){near_3month_high_num = 1.0;}
        else if (app.getNear_3month_high_option() == 4){near_3month_high_num = 2.0;}
        else if (app.getNear_3month_high_option() == 5){near_3month_high_num = 3.0;}
        else if (app.getNear_3month_high_option() == 6){near_3month_high_num = 5.0;}
        else if (app.getNear_3month_high_option() == 7){near_3month_high_num = 10.0;}


        if (app.getNear_resistance_3M_above_option() == 0){near_resistance_3M_above_num = -9999999999999999d;}
        else if (app.getNear_resistance_3M_above_option() == 1){near_resistance_3M_above_num = 10.0;}
        else if (app.getNear_resistance_3M_above_option() == 2){near_resistance_3M_above_num = 5.0;}
        else if (app.getNear_resistance_3M_above_option() == 3){near_resistance_3M_above_num = 3.0;}
        else if (app.getNear_resistance_3M_above_option() == 4){near_resistance_3M_above_num = 2.0;}
        else if (app.getNear_resistance_3M_above_option() == 5){near_resistance_3M_above_num = 1.0;}
        else if (app.getNear_resistance_3M_above_option() == 6){near_resistance_3M_above_num = 0.5;}
        else if (app.getNear_resistance_3M_above_option() == 7){near_resistance_3M_above_num = 0.25;}
        else if (app.getNear_resistance_3M_above_option() == 8){near_resistance_3M_above_num = -0.25;}
        else if (app.getNear_resistance_3M_above_option() == 9){near_resistance_3M_above_num = -0.5;}
        else if (app.getNear_resistance_3M_above_option() == 10){near_resistance_3M_above_num = -1.0;}
        else if (app.getNear_resistance_3M_above_option() == 11){near_resistance_3M_above_num = -2.0;}
        else if (app.getNear_resistance_3M_above_option() == 12){near_resistance_3M_above_num = -3.0;}
        else if (app.getNear_resistance_3M_above_option() == 13){near_resistance_3M_above_num = -5.0;}
        else if (app.getNear_resistance_3M_above_option() == 14){near_resistance_3M_above_num = -10.0;}
        if (app.getNear_resistance_3M_below_option() == 0){near_resistance_3M_below_num = 9999999999999999d;}
        else if (app.getNear_resistance_3M_below_option() == 1){near_resistance_3M_below_num = 10.0;}
        else if (app.getNear_resistance_3M_below_option() == 2){near_resistance_3M_below_num = 5.0;}
        else if (app.getNear_resistance_3M_below_option() == 3){near_resistance_3M_below_num = 3.0;}
        else if (app.getNear_resistance_3M_below_option() == 4){near_resistance_3M_below_num = 2.0;}
        else if (app.getNear_resistance_3M_below_option() == 5){near_resistance_3M_below_num = 1.0;}
        else if (app.getNear_resistance_3M_below_option() == 6){near_resistance_3M_below_num = 0.5;}
        else if (app.getNear_resistance_3M_below_option() == 7){near_resistance_3M_below_num = 0.25;}
        else if (app.getNear_resistance_3M_below_option() == 8){near_resistance_3M_below_num = -0.25;}
        else if (app.getNear_resistance_3M_below_option() == 9){near_resistance_3M_below_num = -0.5;}
        else if (app.getNear_resistance_3M_below_option() == 10){near_resistance_3M_below_num = -1.0;}
        else if (app.getNear_resistance_3M_below_option() == 11){near_resistance_3M_below_num = -2.0;}
        else if (app.getNear_resistance_3M_below_option() == 12){near_resistance_3M_below_num = -3.0;}
        else if (app.getNear_resistance_3M_below_option() == 13){near_resistance_3M_below_num = -5.0;}
        else if (app.getNear_resistance_3M_below_option() == 14){near_resistance_3M_below_num = -10.0;}

        if (app.getNear_resistance_1M_above_option() == 0){near_resistance_1M_above_num = -9999999999999999d;}
        else if (app.getNear_resistance_1M_above_option() == 1){near_resistance_1M_above_num = 10.0;}
        else if (app.getNear_resistance_1M_above_option() == 2){near_resistance_1M_above_num = 5.0;}
        else if (app.getNear_resistance_1M_above_option() == 3){near_resistance_1M_above_num = 3.0;}
        else if (app.getNear_resistance_1M_above_option() == 4){near_resistance_1M_above_num = 2.0;}
        else if (app.getNear_resistance_1M_above_option() == 5){near_resistance_1M_above_num = 1.0;}
        else if (app.getNear_resistance_1M_above_option() == 6){near_resistance_1M_above_num = 0.5;}
        else if (app.getNear_resistance_1M_above_option() == 7){near_resistance_1M_above_num = 0.25;}
        else if (app.getNear_resistance_1M_above_option() == 8){near_resistance_1M_above_num = -0.25;}
        else if (app.getNear_resistance_1M_above_option() == 9){near_resistance_1M_above_num = -0.5;}
        else if (app.getNear_resistance_1M_above_option() == 10){near_resistance_1M_above_num = -1.0;}
        else if (app.getNear_resistance_1M_above_option() == 11){near_resistance_1M_above_num = -2.0;}
        else if (app.getNear_resistance_1M_above_option() == 12){near_resistance_1M_above_num = -3.0;}
        else if (app.getNear_resistance_1M_above_option() == 13){near_resistance_1M_above_num = -5.0;}
        else if (app.getNear_resistance_1M_above_option() == 14){near_resistance_1M_above_num = -10.0;}
        if (app.getNear_resistance_1M_below_option() == 0){near_resistance_1M_below_num = 9999999999999999d;}
        else if (app.getNear_resistance_1M_below_option() == 1){near_resistance_1M_below_num = 10.0;}
        else if (app.getNear_resistance_1M_below_option() == 2){near_resistance_1M_below_num = 5.0;}
        else if (app.getNear_resistance_1M_below_option() == 3){near_resistance_1M_below_num = 3.0;}
        else if (app.getNear_resistance_1M_below_option() == 4){near_resistance_1M_below_num = 2.0;}
        else if (app.getNear_resistance_1M_below_option() == 5){near_resistance_1M_below_num = 1.0;}
        else if (app.getNear_resistance_1M_below_option() == 6){near_resistance_1M_below_num = 0.5;}
        else if (app.getNear_resistance_1M_below_option() == 7){near_resistance_1M_below_num = 0.25;}
        else if (app.getNear_resistance_1M_below_option() == 8){near_resistance_1M_below_num = -0.25;}
        else if (app.getNear_resistance_1M_below_option() == 9){near_resistance_1M_below_num = -0.5;}
        else if (app.getNear_resistance_1M_below_option() == 10){near_resistance_1M_below_num = -1.0;}
        else if (app.getNear_resistance_1M_below_option() == 11){near_resistance_1M_below_num = -2.0;}
        else if (app.getNear_resistance_1M_below_option() == 12){near_resistance_1M_below_num = -3.0;}
        else if (app.getNear_resistance_1M_below_option() == 13){near_resistance_1M_below_num = -5.0;}
        else if (app.getNear_resistance_1M_below_option() == 14){near_resistance_1M_below_num = -10.0;}

        if (app.getNear_support_3M_above_option() == 0){near_support_3M_above_num = -9999999999999999d;}
        else if (app.getNear_support_3M_above_option() == 1){near_support_3M_above_num = 10.0;}
        else if (app.getNear_support_3M_above_option() == 2){near_support_3M_above_num = 5.0;}
        else if (app.getNear_support_3M_above_option() == 3){near_support_3M_above_num = 3.0;}
        else if (app.getNear_support_3M_above_option() == 4){near_support_3M_above_num = 2.0;}
        else if (app.getNear_support_3M_above_option() == 5){near_support_3M_above_num = 1.0;}
        else if (app.getNear_support_3M_above_option() == 6){near_support_3M_above_num = 0.5;}
        else if (app.getNear_support_3M_above_option() == 7){near_support_3M_above_num = 0.25;}
        else if (app.getNear_support_3M_above_option() == 8){near_support_3M_above_num = -0.25;}
        else if (app.getNear_support_3M_above_option() == 9){near_support_3M_above_num = -0.5;}
        else if (app.getNear_support_3M_above_option() == 10){near_support_3M_above_num = -1.0;}
        else if (app.getNear_support_3M_above_option() == 11){near_support_3M_above_num = -2.0;}
        else if (app.getNear_support_3M_above_option() == 12){near_support_3M_above_num = -3.0;}
        else if (app.getNear_support_3M_above_option() == 13){near_support_3M_above_num = -5.0;}
        else if (app.getNear_support_3M_above_option() == 14){near_support_3M_above_num = -10.0;}
        if (app.getNear_support_3M_below_option() == 0){near_support_3M_below_num = 9999999999999999d;}
        else if (app.getNear_support_3M_below_option() == 1){near_support_3M_below_num = 10.0;}
        else if (app.getNear_support_3M_below_option() == 2){near_support_3M_below_num = 5.0;}
        else if (app.getNear_support_3M_below_option() == 3){near_support_3M_below_num = 3.0;}
        else if (app.getNear_support_3M_below_option() == 4){near_support_3M_below_num = 2.0;}
        else if (app.getNear_support_3M_below_option() == 5){near_support_3M_below_num = 1.0;}
        else if (app.getNear_support_3M_below_option() == 6){near_support_3M_below_num = 0.5;}
        else if (app.getNear_support_3M_below_option() == 7){near_support_3M_below_num = 0.25;}
        else if (app.getNear_support_3M_below_option() == 8){near_support_3M_below_num = -0.25;}
        else if (app.getNear_support_3M_below_option() == 9){near_support_3M_below_num = -0.5;}
        else if (app.getNear_support_3M_below_option() == 10){near_support_3M_below_num = -1.0;}
        else if (app.getNear_support_3M_below_option() == 11){near_support_3M_below_num = -2.0;}
        else if (app.getNear_support_3M_below_option() == 12){near_support_3M_below_num = -3.0;}
        else if (app.getNear_support_3M_below_option() == 13){near_support_3M_below_num = -5.0;}
        else if (app.getNear_support_3M_below_option() == 14){near_support_3M_below_num = -10.0;}

        if (app.getNear_support_1M_above_option() == 0){near_support_1M_above_num = -9999999999999999d;}
        else if (app.getNear_support_1M_above_option() == 1){near_support_1M_above_num = 10.0;}
        else if (app.getNear_support_1M_above_option() == 2){near_support_1M_above_num = 5.0;}
        else if (app.getNear_support_1M_above_option() == 3){near_support_1M_above_num = 3.0;}
        else if (app.getNear_support_1M_above_option() == 4){near_support_1M_above_num = 2.0;}
        else if (app.getNear_support_1M_above_option() == 5){near_support_1M_above_num = 1.0;}
        else if (app.getNear_support_1M_above_option() == 6){near_support_1M_above_num = 0.5;}
        else if (app.getNear_support_1M_above_option() == 7){near_support_1M_above_num = 0.25;}
        else if (app.getNear_support_1M_above_option() == 8){near_support_1M_above_num = -0.25;}
        else if (app.getNear_support_1M_above_option() == 9){near_support_1M_above_num = -0.5;}
        else if (app.getNear_support_1M_above_option() == 10){near_support_1M_above_num = -1.0;}
        else if (app.getNear_support_1M_above_option() == 11){near_support_1M_above_num = -2.0;}
        else if (app.getNear_support_1M_above_option() == 12){near_support_1M_above_num = -3.0;}
        else if (app.getNear_support_1M_above_option() == 13){near_support_1M_above_num = -5.0;}
        else if (app.getNear_support_1M_above_option() == 14){near_support_1M_above_num = -10.0;}
        if (app.getNear_support_1M_below_option() == 0){near_support_1M_below_num = 9999999999999999d;}
        else if (app.getNear_support_1M_below_option() == 1){near_support_1M_below_num = 10.0;}
        else if (app.getNear_support_1M_below_option() == 2){near_support_1M_below_num = 5.0;}
        else if (app.getNear_support_1M_below_option() == 3){near_support_1M_below_num = 3.0;}
        else if (app.getNear_support_1M_below_option() == 4){near_support_1M_below_num = 2.0;}
        else if (app.getNear_support_1M_below_option() == 5){near_support_1M_below_num = 1.0;}
        else if (app.getNear_support_1M_below_option() == 6){near_support_1M_below_num = 0.5;}
        else if (app.getNear_support_1M_below_option() == 7){near_support_1M_below_num = 0.25;}
        else if (app.getNear_support_1M_below_option() == 8){near_support_1M_below_num = -0.25;}
        else if (app.getNear_support_1M_below_option() == 9){near_support_1M_below_num = -0.5;}
        else if (app.getNear_support_1M_below_option() == 10){near_support_1M_below_num = -1.0;}
        else if (app.getNear_support_1M_below_option() == 11){near_support_1M_below_num = -2.0;}
        else if (app.getNear_support_1M_below_option() == 12){near_support_1M_below_num = -3.0;}
        else if (app.getNear_support_1M_below_option() == 13){near_support_1M_below_num = -5.0;}
        else if (app.getNear_support_1M_below_option() == 14){near_support_1M_below_num = -10.0;}

        if (app.getParabolic_1M_option() == 0){parabolic_1M_num = -9999999999999999d;}
        else if (app.getParabolic_1M_option() == 1){parabolic_1M_num = 3;}
        else if (app.getParabolic_1M_option() == 2){parabolic_1M_num = 2;}
        else if (app.getParabolic_1M_option() == 3){parabolic_1M_num = 1;}

        if (app.getParabolic_1wk_option() == 0){parabolic_1wk_num = -9999999999999999d;}
        else if (app.getParabolic_1wk_option() == 1){parabolic_1wk_num = 3;}
        else if (app.getParabolic_1wk_option() == 2){parabolic_1wk_num = 2;}
        else if (app.getParabolic_1wk_option() == 3){parabolic_1wk_num = 1;}

        if (app.getPercent_4rel_3_above_option() == 0){percent_4rel_3_above_num = -9999999999999999d;}
        else if (app.getPercent_4rel_3_above_option() == 1){percent_4rel_3_above_num = 20.0;}
        else if (app.getPercent_4rel_3_above_option() == 2){percent_4rel_3_above_num = 10.0;}
        else if (app.getPercent_4rel_3_above_option() == 3){percent_4rel_3_above_num = 5.0;}
        else if (app.getPercent_4rel_3_above_option() == 4){percent_4rel_3_above_num = 4.0;}
        else if (app.getPercent_4rel_3_above_option() == 5){percent_4rel_3_above_num = 3.0;}
        else if (app.getPercent_4rel_3_above_option() == 6){percent_4rel_3_above_num = 2.0;}
        else if (app.getPercent_4rel_3_above_option() == 7){percent_4rel_3_above_num = 1.5;}
        else if (app.getPercent_4rel_3_above_option() == 8){percent_4rel_3_above_num = 1.0;}
        else if (app.getPercent_4rel_3_above_option() == 9){percent_4rel_3_above_num = 0.5;}
        else if (app.getPercent_4rel_3_above_option() == 10){percent_4rel_3_above_num = 0.0;}
        else if (app.getPercent_4rel_3_above_option() == 11){percent_4rel_3_above_num = -0.5;}
        else if (app.getPercent_4rel_3_above_option() == 12){percent_4rel_3_above_num = -1.0;}
        else if (app.getPercent_4rel_3_above_option() == 13){percent_4rel_3_above_num = -1.5;}
        else if (app.getPercent_4rel_3_above_option() == 14){percent_4rel_3_above_num = -2.0;}
        else if (app.getPercent_4rel_3_above_option() == 15){percent_4rel_3_above_num = -3.0;}
        else if (app.getPercent_4rel_3_above_option() == 16){percent_4rel_3_above_num = -4.0;}
        else if (app.getPercent_4rel_3_above_option() == 17){percent_4rel_3_above_num = -5.0;}
        else if (app.getPercent_4rel_3_above_option() == 18){percent_4rel_3_above_num = -10.0;}
        if (app.getPercent_4rel_3_below_option() == 0){percent_4rel_3_below_num = 9999999999999999d;}
        else if (app.getPercent_4rel_3_below_option() == 1){percent_4rel_3_below_num = 20.0;}
        else if (app.getPercent_4rel_3_below_option() == 2){percent_4rel_3_below_num = 10.0;}
        else if (app.getPercent_4rel_3_below_option() == 3){percent_4rel_3_below_num = 5.0;}
        else if (app.getPercent_4rel_3_below_option() == 4){percent_4rel_3_below_num = 4.0;}
        else if (app.getPercent_4rel_3_below_option() == 5){percent_4rel_3_below_num = 3.0;}
        else if (app.getPercent_4rel_3_below_option() == 6){percent_4rel_3_below_num = 2.0;}
        else if (app.getPercent_4rel_3_below_option() == 7){percent_4rel_3_below_num = 1.5;}
        else if (app.getPercent_4rel_3_below_option() == 8){percent_4rel_3_below_num = 1.0;}
        else if (app.getPercent_4rel_3_below_option() == 9){percent_4rel_3_below_num = 0.5;}
        else if (app.getPercent_4rel_3_below_option() == 10){percent_4rel_3_below_num = 0.0;}
        else if (app.getPercent_4rel_3_below_option() == 11){percent_4rel_3_below_num = -0.5;}
        else if (app.getPercent_4rel_3_below_option() == 12){percent_4rel_3_below_num = -1.0;}
        else if (app.getPercent_4rel_3_below_option() == 13){percent_4rel_3_below_num = -1.5;}
        else if (app.getPercent_4rel_3_below_option() == 14){percent_4rel_3_below_num = -2.0;}
        else if (app.getPercent_4rel_3_below_option() == 15){percent_4rel_3_below_num = -3.0;}
        else if (app.getPercent_4rel_3_below_option() == 16){percent_4rel_3_below_num = -4.0;}
        else if (app.getPercent_4rel_3_below_option() == 17){percent_4rel_3_below_num = -5.0;}
        else if (app.getPercent_4rel_3_below_option() == 18){percent_4rel_3_below_num = -10.0;}

        if (app.getPercent_3rel_2_above_option() == 0){percent_3rel_2_above_num = -9999999999999999d;}
        else if (app.getPercent_3rel_2_above_option() == 1){percent_3rel_2_above_num = 20.0;}
        else if (app.getPercent_3rel_2_above_option() == 2){percent_3rel_2_above_num = 10.0;}
        else if (app.getPercent_3rel_2_above_option() == 3){percent_3rel_2_above_num = 5.0;}
        else if (app.getPercent_3rel_2_above_option() == 4){percent_3rel_2_above_num = 4.0;}
        else if (app.getPercent_3rel_2_above_option() == 5){percent_3rel_2_above_num = 3.0;}
        else if (app.getPercent_3rel_2_above_option() == 6){percent_3rel_2_above_num = 2.0;}
        else if (app.getPercent_3rel_2_above_option() == 7){percent_3rel_2_above_num = 1.5;}
        else if (app.getPercent_3rel_2_above_option() == 8){percent_3rel_2_above_num = 1.0;}
        else if (app.getPercent_3rel_2_above_option() == 9){percent_3rel_2_above_num = 0.5;}
        else if (app.getPercent_3rel_2_above_option() == 10){percent_3rel_2_above_num = 0.0;}
        else if (app.getPercent_3rel_2_above_option() == 11){percent_3rel_2_above_num = -0.5;}
        else if (app.getPercent_3rel_2_above_option() == 12){percent_3rel_2_above_num = -1.0;}
        else if (app.getPercent_3rel_2_above_option() == 13){percent_3rel_2_above_num = -1.5;}
        else if (app.getPercent_3rel_2_above_option() == 14){percent_3rel_2_above_num = -2.0;}
        else if (app.getPercent_3rel_2_above_option() == 15){percent_3rel_2_above_num = -3.0;}
        else if (app.getPercent_3rel_2_above_option() == 16){percent_3rel_2_above_num = -4.0;}
        else if (app.getPercent_3rel_2_above_option() == 17){percent_3rel_2_above_num = -5.0;}
        else if (app.getPercent_3rel_2_above_option() == 18){percent_3rel_2_above_num = -10.0;}
        if (app.getPercent_3rel_2_below_option() == 0){percent_3rel_2_below_num = 9999999999999999d;}
        else if (app.getPercent_3rel_2_below_option() == 1){percent_3rel_2_below_num = 20.0;}
        else if (app.getPercent_3rel_2_below_option() == 2){percent_3rel_2_below_num = 10.0;}
        else if (app.getPercent_3rel_2_below_option() == 3){percent_3rel_2_below_num = 5.0;}
        else if (app.getPercent_3rel_2_below_option() == 4){percent_3rel_2_below_num = 4.0;}
        else if (app.getPercent_3rel_2_below_option() == 5){percent_3rel_2_below_num = 3.0;}
        else if (app.getPercent_3rel_2_below_option() == 6){percent_3rel_2_below_num = 2.0;}
        else if (app.getPercent_3rel_2_below_option() == 7){percent_3rel_2_below_num = 1.5;}
        else if (app.getPercent_3rel_2_below_option() == 8){percent_3rel_2_below_num = 1.0;}
        else if (app.getPercent_3rel_2_below_option() == 9){percent_3rel_2_below_num = 0.5;}
        else if (app.getPercent_3rel_2_below_option() == 10){percent_3rel_2_below_num = 0.0;}
        else if (app.getPercent_3rel_2_below_option() == 11){percent_3rel_2_below_num = -0.5;}
        else if (app.getPercent_3rel_2_below_option() == 12){percent_3rel_2_below_num = -1.0;}
        else if (app.getPercent_3rel_2_below_option() == 13){percent_3rel_2_below_num = -1.5;}
        else if (app.getPercent_3rel_2_below_option() == 14){percent_3rel_2_below_num = -2.0;}
        else if (app.getPercent_3rel_2_below_option() == 15){percent_3rel_2_below_num = -3.0;}
        else if (app.getPercent_3rel_2_below_option() == 16){percent_3rel_2_below_num = -4.0;}
        else if (app.getPercent_3rel_2_below_option() == 17){percent_3rel_2_below_num = -5.0;}
        else if (app.getPercent_3rel_2_below_option() == 18){percent_3rel_2_below_num = -10.0;}

        if (app.getPercent_2rel_1_above_option() == 0){percent_2rel_1_above_num = -9999999999999999d;}
        else if (app.getPercent_2rel_1_above_option() == 1){percent_2rel_1_above_num = 20.0;}
        else if (app.getPercent_2rel_1_above_option() == 2){percent_2rel_1_above_num = 10.0;}
        else if (app.getPercent_2rel_1_above_option() == 3){percent_2rel_1_above_num = 5.0;}
        else if (app.getPercent_2rel_1_above_option() == 4){percent_2rel_1_above_num = 4.0;}
        else if (app.getPercent_2rel_1_above_option() == 5){percent_2rel_1_above_num = 3.0;}
        else if (app.getPercent_2rel_1_above_option() == 6){percent_2rel_1_above_num = 2.0;}
        else if (app.getPercent_2rel_1_above_option() == 7){percent_2rel_1_above_num = 1.5;}
        else if (app.getPercent_2rel_1_above_option() == 8){percent_2rel_1_above_num = 1.0;}
        else if (app.getPercent_2rel_1_above_option() == 9){percent_2rel_1_above_num = 0.5;}
        else if (app.getPercent_2rel_1_above_option() == 10){percent_2rel_1_above_num = 0.0;}
        else if (app.getPercent_2rel_1_above_option() == 11){percent_2rel_1_above_num = -0.5;}
        else if (app.getPercent_2rel_1_above_option() == 12){percent_2rel_1_above_num = -1.0;}
        else if (app.getPercent_2rel_1_above_option() == 13){percent_2rel_1_above_num = -1.5;}
        else if (app.getPercent_2rel_1_above_option() == 14){percent_2rel_1_above_num = -2.0;}
        else if (app.getPercent_2rel_1_above_option() == 15){percent_2rel_1_above_num = -3.0;}
        else if (app.getPercent_2rel_1_above_option() == 16){percent_2rel_1_above_num = -4.0;}
        else if (app.getPercent_2rel_1_above_option() == 17){percent_2rel_1_above_num = -5.0;}
        else if (app.getPercent_2rel_1_above_option() == 18){percent_2rel_1_above_num = -10.0;}
        if (app.getPercent_2rel_1_below_option() == 0){percent_2rel_1_below_num = 9999999999999999d;}
        else if (app.getPercent_2rel_1_below_option() == 1){percent_2rel_1_below_num = 20.0;}
        else if (app.getPercent_2rel_1_below_option() == 2){percent_2rel_1_below_num = 10.0;}
        else if (app.getPercent_2rel_1_below_option() == 3){percent_2rel_1_below_num = 5.0;}
        else if (app.getPercent_2rel_1_below_option() == 4){percent_2rel_1_below_num = 4.0;}
        else if (app.getPercent_2rel_1_below_option() == 5){percent_2rel_1_below_num = 3.0;}
        else if (app.getPercent_2rel_1_below_option() == 6){percent_2rel_1_below_num = 2.0;}
        else if (app.getPercent_2rel_1_below_option() == 7){percent_2rel_1_below_num = 1.5;}
        else if (app.getPercent_2rel_1_below_option() == 8){percent_2rel_1_below_num = 1.0;}
        else if (app.getPercent_2rel_1_below_option() == 9){percent_2rel_1_below_num = 0.5;}
        else if (app.getPercent_2rel_1_below_option() == 10){percent_2rel_1_below_num = 0.0;}
        else if (app.getPercent_2rel_1_below_option() == 11){percent_2rel_1_below_num = -0.5;}
        else if (app.getPercent_2rel_1_below_option() == 12){percent_2rel_1_below_num = -1.0;}
        else if (app.getPercent_2rel_1_below_option() == 13){percent_2rel_1_below_num = -1.5;}
        else if (app.getPercent_2rel_1_below_option() == 14){percent_2rel_1_below_num = -2.0;}
        else if (app.getPercent_2rel_1_below_option() == 15){percent_2rel_1_below_num = -3.0;}
        else if (app.getPercent_2rel_1_below_option() == 16){percent_2rel_1_below_num = -4.0;}
        else if (app.getPercent_2rel_1_below_option() == 17){percent_2rel_1_below_num = -5.0;}
        else if (app.getPercent_2rel_1_below_option() == 18){percent_2rel_1_below_num = -10.0;}

        if (app.getVol_4rel_3_above_option() == 0){vol_4rel_3_above_num = -9999999999999999d;}
        else if (app.getVol_4rel_3_above_option() == 1){vol_4rel_3_above_num = 10.0;}
        else if (app.getVol_4rel_3_above_option() == 2){vol_4rel_3_above_num = 5.0;}
        else if (app.getVol_4rel_3_above_option() == 3){vol_4rel_3_above_num = 3.0;}
        else if (app.getVol_4rel_3_above_option() == 4){vol_4rel_3_above_num = 2.0;}
        else if (app.getVol_4rel_3_above_option() == 5){vol_4rel_3_above_num = 1.75;}
        else if (app.getVol_4rel_3_above_option() == 6){vol_4rel_3_above_num = 1.5;}
        else if (app.getVol_4rel_3_above_option() == 7){vol_4rel_3_above_num = 1.25;}
        else if (app.getVol_4rel_3_above_option() == 8){vol_4rel_3_above_num = 1.0;}
        else if (app.getVol_4rel_3_above_option() == 9){vol_4rel_3_above_num = 0.75;}
        else if (app.getVol_4rel_3_above_option() == 10){vol_4rel_3_above_num = 0.5;}

        if (app.getVol_4rel_3_below_option() == 0){vol_4rel_3_below_num = 9999999999999999d;}
        else if (app.getVol_4rel_3_below_option() == 1){vol_4rel_3_below_num = 10.0;}
        else if (app.getVol_4rel_3_below_option() == 2){vol_4rel_3_below_num = 5.0;}
        else if (app.getVol_4rel_3_below_option() == 3){vol_4rel_3_below_num = 3.0;}
        else if (app.getVol_4rel_3_below_option() == 4){vol_4rel_3_below_num = 2.0;}
        else if (app.getVol_4rel_3_below_option() == 5){vol_4rel_3_below_num = 1.75;}
        else if (app.getVol_4rel_3_below_option() == 6){vol_4rel_3_below_num = 1.5;}
        else if (app.getVol_4rel_3_below_option() == 7){vol_4rel_3_below_num = 1.25;}
        else if (app.getVol_4rel_3_below_option() == 8){vol_4rel_3_below_num = 1.0;}
        else if (app.getVol_4rel_3_below_option() == 9){vol_4rel_3_below_num = 0.75;}
        else if (app.getVol_4rel_3_below_option() == 10){vol_4rel_3_below_num = 0.5;}

        if (app.getVol_3rel_2_above_option() == 0){vol_3rel_2_above_num = -9999999999999999d;}
        else if (app.getVol_3rel_2_above_option() == 1){vol_3rel_2_above_num = 10.0;}
        else if (app.getVol_3rel_2_above_option() == 2){vol_3rel_2_above_num = 5.0;}
        else if (app.getVol_3rel_2_above_option() == 3){vol_3rel_2_above_num = 3.0;}
        else if (app.getVol_3rel_2_above_option() == 4){vol_3rel_2_above_num = 2.0;}
        else if (app.getVol_3rel_2_above_option() == 5){vol_3rel_2_above_num = 1.75;}
        else if (app.getVol_3rel_2_above_option() == 6){vol_3rel_2_above_num = 1.5;}
        else if (app.getVol_3rel_2_above_option() == 7){vol_3rel_2_above_num = 1.25;}
        else if (app.getVol_3rel_2_above_option() == 8){vol_3rel_2_above_num = 1.0;}
        else if (app.getVol_3rel_2_above_option() == 9){vol_3rel_2_above_num = 0.75;}
        else if (app.getVol_3rel_2_above_option() == 10){vol_3rel_2_above_num = 0.5;}
        if (app.getVol_3rel_2_below_option() == 0){vol_3rel_2_below_num = 9999999999999999d;}
        else if (app.getVol_3rel_2_below_option() == 1){vol_3rel_2_below_num = 10.0;}
        else if (app.getVol_3rel_2_below_option() == 2){vol_3rel_2_below_num = 5.0;}
        else if (app.getVol_3rel_2_below_option() == 3){vol_3rel_2_below_num = 3.0;}
        else if (app.getVol_3rel_2_below_option() == 4){vol_3rel_2_below_num = 2.0;}
        else if (app.getVol_3rel_2_below_option() == 5){vol_3rel_2_below_num = 1.75;}
        else if (app.getVol_3rel_2_below_option() == 6){vol_3rel_2_below_num = 1.5;}
        else if (app.getVol_3rel_2_below_option() == 7){vol_3rel_2_below_num = 1.25;}
        else if (app.getVol_3rel_2_below_option() == 8){vol_3rel_2_below_num = 1.0;}
        else if (app.getVol_3rel_2_below_option() == 9){vol_3rel_2_below_num = 0.75;}
        else if (app.getVol_3rel_2_below_option() == 10){vol_3rel_2_below_num = 0.5;}

        if (app.getVol_2rel_1_above_option() == 0){vol_2rel_1_above_num = -9999999999999999d;}
        else if (app.getVol_2rel_1_above_option() == 1){vol_2rel_1_above_num = 10.0;}
        else if (app.getVol_2rel_1_above_option() == 2){vol_2rel_1_above_num = 5.0;}
        else if (app.getVol_2rel_1_above_option() == 3){vol_2rel_1_above_num = 3.0;}
        else if (app.getVol_2rel_1_above_option() == 4){vol_2rel_1_above_num = 2.0;}
        else if (app.getVol_2rel_1_above_option() == 5){vol_2rel_1_above_num = 1.75;}
        else if (app.getVol_2rel_1_above_option() == 6){vol_2rel_1_above_num = 1.5;}
        else if (app.getVol_2rel_1_above_option() == 7){vol_2rel_1_above_num = 1.25;}
        else if (app.getVol_2rel_1_above_option() == 8){vol_2rel_1_above_num = 1.0;}
        else if (app.getVol_2rel_1_above_option() == 9){vol_2rel_1_above_num = 0.75;}
        else if (app.getVol_2rel_1_above_option() == 10){vol_2rel_1_above_num = 0.5;}
        if (app.getVol_2rel_1_below_option() == 0){vol_2rel_1_below_num = 9999999999999999d;}
        else if (app.getVol_2rel_1_below_option() == 1){vol_2rel_1_below_num = 10.0;}
        else if (app.getVol_2rel_1_below_option() == 2){vol_2rel_1_below_num = 5.0;}
        else if (app.getVol_2rel_1_below_option() == 3){vol_2rel_1_below_num = 3.0;}
        else if (app.getVol_2rel_1_below_option() == 4){vol_2rel_1_below_num = 2.0;}
        else if (app.getVol_2rel_1_below_option() == 5){vol_2rel_1_below_num = 1.75;}
        else if (app.getVol_2rel_1_below_option() == 6){vol_2rel_1_below_num = 1.5;}
        else if (app.getVol_2rel_1_below_option() == 7){vol_2rel_1_below_num = 1.25;}
        else if (app.getVol_2rel_1_below_option() == 8){vol_2rel_1_below_num = 1.0;}
        else if (app.getVol_2rel_1_below_option() == 9){vol_2rel_1_below_num = 0.75;}
        else if (app.getVol_2rel_1_below_option() == 10){vol_2rel_1_below_num = 0.5;}

        if (app.getNear_resistance_daily_above_option() == 0){near_resistance_daily_above_num = -9999999999999999d;}
        else if (app.getNear_resistance_daily_above_option() == 1){near_resistance_daily_above_num = 5.0;}
        else if (app.getNear_resistance_daily_above_option() == 2){near_resistance_daily_above_num = 3.0;}
        else if (app.getNear_resistance_daily_above_option() == 3){near_resistance_daily_above_num = 2.0;}
        else if (app.getNear_resistance_daily_above_option() == 4){near_resistance_daily_above_num = 1.0;}
        else if (app.getNear_resistance_daily_above_option() == 5){near_resistance_daily_above_num = 0.5;}
        else if (app.getNear_resistance_daily_above_option() == 6){near_resistance_daily_above_num = 0.25;}
        else if (app.getNear_resistance_daily_above_option() == 7){near_resistance_daily_above_num = -0.25;}
        else if (app.getNear_resistance_daily_above_option() == 8){near_resistance_daily_above_num = -0.5;}
        else if (app.getNear_resistance_daily_above_option() == 9){near_resistance_daily_above_num = -1.0;}
        else if (app.getNear_resistance_daily_above_option() == 10){near_resistance_daily_above_num = -2.0;}
        else if (app.getNear_resistance_daily_above_option() == 11){near_resistance_daily_above_num = -3.0;}
        else if (app.getNear_resistance_daily_above_option() == 12){near_resistance_daily_above_num = -5.0;}
        if (app.getNear_resistance_daily_below_option() == 0){near_resistance_daily_below_num = 9999999999999999d;}
        else if (app.getNear_resistance_daily_below_option() == 1){near_resistance_daily_below_num = 5.0;}
        else if (app.getNear_resistance_daily_below_option() == 2){near_resistance_daily_below_num = 3.0;}
        else if (app.getNear_resistance_daily_below_option() == 3){near_resistance_daily_below_num = 2.0;}
        else if (app.getNear_resistance_daily_below_option() == 4){near_resistance_daily_below_num = 1.0;}
        else if (app.getNear_resistance_daily_below_option() == 5){near_resistance_daily_below_num = 0.5;}
        else if (app.getNear_resistance_daily_below_option() == 6){near_resistance_daily_below_num = 0.25;}
        else if (app.getNear_resistance_daily_below_option() == 7){near_resistance_daily_below_num = -0.25;}
        else if (app.getNear_resistance_daily_below_option() == 8){near_resistance_daily_below_num = -0.5;}
        else if (app.getNear_resistance_daily_below_option() == 9){near_resistance_daily_below_num = -1.0;}
        else if (app.getNear_resistance_daily_below_option() == 10){near_resistance_daily_below_num = -2.0;}
        else if (app.getNear_resistance_daily_below_option() == 11){near_resistance_daily_below_num = -3.0;}
        else if (app.getNear_resistance_daily_below_option() == 12){near_resistance_daily_below_num = -5.0;}

        if (app.getNear_support_daily_above_option() == 0){near_support_daily_above_num = -9999999999999999d;}
        else if (app.getNear_support_daily_above_option() == 1){near_support_daily_above_num = 5.0;}
        else if (app.getNear_support_daily_above_option() == 2){near_support_daily_above_num = 3.0;}
        else if (app.getNear_support_daily_above_option() == 3){near_support_daily_above_num = 2.0;}
        else if (app.getNear_support_daily_above_option() == 4){near_support_daily_above_num = 1.0;}
        else if (app.getNear_support_daily_above_option() == 5){near_support_daily_above_num = 0.5;}
        else if (app.getNear_support_daily_above_option() == 6){near_support_daily_above_num = 0.25;}
        else if (app.getNear_support_daily_above_option() == 7){near_support_daily_above_num = -0.25;}
        else if (app.getNear_support_daily_above_option() == 8){near_support_daily_above_num = -0.5;}
        else if (app.getNear_support_daily_above_option() == 9){near_support_daily_above_num = -1.0;}
        else if (app.getNear_support_daily_above_option() == 10){near_support_daily_above_num = -2.0;}
        else if (app.getNear_support_daily_above_option() == 11){near_support_daily_above_num = -3.0;}
        else if (app.getNear_support_daily_above_option() == 12){near_support_daily_above_num = -5.0;}
        if (app.getNear_support_daily_below_option() == 0){near_support_daily_below_num = 9999999999999999d;}
        else if (app.getNear_support_daily_below_option() == 1){near_support_daily_below_num = 5.0;}
        else if (app.getNear_support_daily_below_option() == 2){near_support_daily_below_num = 3.0;}
        else if (app.getNear_support_daily_below_option() == 3){near_support_daily_below_num = 2.0;}
        else if (app.getNear_support_daily_below_option() == 4){near_support_daily_below_num = 1.0;}
        else if (app.getNear_support_daily_below_option() == 5){near_support_daily_below_num = 0.5;}
        else if (app.getNear_support_daily_below_option() == 6){near_support_daily_below_num = 0.25;}
        else if (app.getNear_support_daily_below_option() == 7){near_support_daily_below_num = -0.25;}
        else if (app.getNear_support_daily_below_option() == 8){near_support_daily_below_num = -0.5;}
        else if (app.getNear_support_daily_below_option() == 9){near_support_daily_below_num = -1.0;}
        else if (app.getNear_support_daily_below_option() == 10){near_support_daily_below_num = -2.0;}
        else if (app.getNear_support_daily_below_option() == 11){near_support_daily_below_num = -3.0;}
        else if (app.getNear_support_daily_below_option() == 12){near_support_daily_below_num = -5.0;}

        if (app.getParabolic_1h_option() == 0){parabolic_1h_num = -9999999999999999d;}
        else if (app.getParabolic_1h_option() == 1){parabolic_1h_num = 2;}
        else if (app.getParabolic_1h_option() == 2){parabolic_1h_num = 1;}

        if (app.getSteady_movement_1M_option() == 0){steady_movement_1M_num = -9999999999999999d;}
        else if (app.getSteady_movement_1M_option() == 1){steady_movement_1M_num = 1;}
        else if (app.getSteady_movement_1M_option() == 2){steady_movement_1M_num = 2;}

        if (app.getSteady_movement_1wk_option() == 0){steady_movement_1wk_num = -9999999999999999d;}
        else if (app.getSteady_movement_1wk_option() == 1){steady_movement_1wk_num = 1;}
        else if (app.getSteady_movement_1wk_option() == 2){steady_movement_1wk_num = 2;}

        if (app.getSteady_movement_intraday_option() == 0){steady_movement_intraday_num = -9999999999999999d;}
        else if (app.getSteady_movement_intraday_option() == 1){steady_movement_intraday_num = 2;}
        else if (app.getSteady_movement_intraday_option() == 2){steady_movement_intraday_num = 1;}
        else if (app.getSteady_movement_intraday_option() == 3){steady_movement_intraday_num = 4;}
        else if (app.getSteady_movement_intraday_option() == 4){steady_movement_intraday_num = 3;}

        if (app.getSort_by_option() == 0) {sort_by_num = 0;}
        else if (app.getSort_by_option() == 1) {sort_by_num = 1;}
        else if (app.getSort_by_option() == 2) {sort_by_num = 2;}
        else if (app.getSort_by_option() == 3) {sort_by_num = 3;}
        else if (app.getSort_by_option() == 4) {sort_by_num = 4;}
        else if (app.getSort_by_option() == 5) {sort_by_num = 5;}
        else if (app.getSort_by_option() == 6) {sort_by_num = 6;}
        else if (app.getSort_by_option() == 7) {sort_by_num = 7;}
        else if (app.getSort_by_option() == 8) {sort_by_num = 8;}
        else if (app.getSort_by_option() == 9) {sort_by_num = 9;}
        else if (app.getSort_by_option() == 10) {sort_by_num = 10;}
        else if (app.getSort_by_option() == 11) {sort_by_num = 11;}

        if (app.getVolatility_intraday_above_option() == 0){volatility_intraday_above_num = -9999999999999999d;}
        else if (app.getVolatility_intraday_above_option() == 1){volatility_intraday_above_num = 10;}
        else if (app.getVolatility_intraday_above_option() == 2){volatility_intraday_above_num = 7.5;}
        else if (app.getVolatility_intraday_above_option() == 3){volatility_intraday_above_num = 5;}
        else if (app.getVolatility_intraday_above_option() == 4){volatility_intraday_above_num = 4;}
        else if (app.getVolatility_intraday_above_option() == 5){volatility_intraday_above_num = 3;}
        else if (app.getVolatility_intraday_above_option() == 6){volatility_intraday_above_num = 2;}
        else if (app.getVolatility_intraday_above_option() == 7){volatility_intraday_above_num = 1.5;}
        else if (app.getVolatility_intraday_above_option() == 8){volatility_intraday_above_num = 1.0;}
        else if (app.getVolatility_intraday_above_option() == 9){volatility_intraday_above_num = 0.5;}
        if (app.getVolatility_intraday_below_option() == 0){volatility_intraday_below_num = 9999999999999999d;}
        else if (app.getVolatility_intraday_below_option() == 1){volatility_intraday_below_num = 10;}
        else if (app.getVolatility_intraday_below_option() == 2){volatility_intraday_below_num = 7.5;}
        else if (app.getVolatility_intraday_below_option() == 3){volatility_intraday_below_num = 5;}
        else if (app.getVolatility_intraday_below_option() == 4){volatility_intraday_below_num = 4;}
        else if (app.getVolatility_intraday_below_option() == 5){volatility_intraday_below_num = 3;}
        else if (app.getVolatility_intraday_below_option() == 6){volatility_intraday_below_num = 2;}
        else if (app.getVolatility_intraday_below_option() == 7){volatility_intraday_below_num = 1.5;}
        else if (app.getVolatility_intraday_below_option() == 8){volatility_intraday_below_num = 1.0;}
        else if (app.getVolatility_intraday_below_option() == 8){volatility_intraday_below_num = 0.5;}


        mDatabase = FirebaseDatabase.getInstance().getReference().child("root/sorted");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    data_string = dataSnapshot.getValue().toString();
                    //Log.d("LIST", " -- " + dataSnapshot.getValue().toString());
                }
                // List String Retrieved. Now Parse
                String volume_change_1to2 = data_string.substring(data_string.indexOf("09id") + 5, data_string.indexOf("]]", data_string.indexOf("09id")) + 1);
                String volume_change_2to3 = data_string.substring(data_string.indexOf("10id") + 5, data_string.indexOf("]]", data_string.indexOf("10id")) + 1);
                String volume_change_3to4 = data_string.substring(data_string.indexOf("11id") + 5, data_string.indexOf("]]", data_string.indexOf("11id")) + 1);
                String percent_change_1to2 = data_string.substring(data_string.indexOf("12id") + 5, data_string.indexOf("]]", data_string.indexOf("12id")) + 1);
                String percent_change_2to3 = data_string.substring(data_string.indexOf("13id") + 5, data_string.indexOf("]]", data_string.indexOf("13id")) + 1);
                String percent_change_3to4 = data_string.substring(data_string.indexOf("14id") + 5, data_string.indexOf("]]", data_string.indexOf("14id")) + 1);
                String avg_volume = data_string.substring(data_string.indexOf("02id") + 5, data_string.indexOf("]]", data_string.indexOf("02id")) + 1);
                String market_cap = data_string.substring(data_string.indexOf("05id") + 5, data_string.indexOf("]]", data_string.indexOf("05id")) + 1);
                String percent = data_string.substring(data_string.indexOf("07id") + 5, data_string.indexOf("]]", data_string.indexOf("07id")) + 1);
                String price = data_string.substring(data_string.indexOf("04id") + 5, data_string.indexOf("]]", data_string.indexOf("04id")) + 1);
                String rel_volatility = data_string.substring(data_string.indexOf("08id") + 5, data_string.indexOf("]]", data_string.indexOf("08id")) + 1);
                String rel_volume = data_string.substring(data_string.indexOf("03id") + 5, data_string.indexOf("]]", data_string.indexOf("03id")) + 1);
                String three_month_high_percent = data_string.substring(data_string.indexOf("15id") + 5, data_string.indexOf("]]", data_string.indexOf("15id")) + 1);
                String volume = data_string.substring(data_string.indexOf("01id") + 5, data_string.indexOf("]]", data_string.indexOf("01id")) + 1);
                String volatility = data_string.substring(data_string.indexOf("06id") + 5, data_string.indexOf("]]", data_string.indexOf("06id")) + 1);
                String parabolic_formation_1M = data_string.substring(data_string.indexOf("20id") + 5, data_string.indexOf("]]", data_string.indexOf("20id")) + 1);
                String parabolic_formation_1wk = data_string.substring(data_string.indexOf("21id") + 5, data_string.indexOf("]]", data_string.indexOf("21id")) + 1);
                String steady_movement_1M = data_string.substring(data_string.indexOf("22id") + 5, data_string.indexOf("]]", data_string.indexOf("22id")) + 1);
                String steady_movement_1wk = data_string.substring(data_string.indexOf("23id") + 5, data_string.indexOf("]]", data_string.indexOf("23id")) + 1);
                String near_resistance_3M = data_string.substring(data_string.indexOf("16id") + 5, data_string.indexOf("]]", data_string.indexOf("16id")) + 1);
                String near_resistance_1M = data_string.substring(data_string.indexOf("17id") + 5, data_string.indexOf("]]", data_string.indexOf("17id")) + 1);
                String near_support_3M = data_string.substring(data_string.indexOf("18id") + 5, data_string.indexOf("]]", data_string.indexOf("18id")) + 1);
                String near_support_1M = data_string.substring(data_string.indexOf("19id") + 5, data_string.indexOf("]]", data_string.indexOf("19id")) + 1);
                String near_resistance_daily = data_string.substring(data_string.indexOf("24id") + 5, data_string.indexOf("]]", data_string.indexOf("24id")) + 1);
                String near_support_daily = data_string.substring(data_string.indexOf("25id") + 5, data_string.indexOf("]]", data_string.indexOf("25id")) + 1);
                String parabolic_formation_1h = data_string.substring(data_string.indexOf("26id") + 5, data_string.indexOf("]]", data_string.indexOf("26id")) + 1);
                String steady_movement_daily = data_string.substring(data_string.indexOf("27id") + 5, data_string.indexOf("]]", data_string.indexOf("27id")) + 1);
                String volatility_daily = data_string.substring(data_string.indexOf("28id") + 5, data_string.indexOf("]]", data_string.indexOf("28id")) + 1);


                //Once parsed run searchList on them all
                temp = new ArrayList<String>();
                sortListDone = false;
                if (sort_by_num == 0){
                    temp = searchList(price, temp, price_below_num, price_above_num, true);
                    listToExclude = "price";
                }
               else if (sort_by_num == 1){
                    temp = searchList(market_cap, temp, market_cap_below_num, market_cap_above_num, true);
                    listToExclude = "market_cap";
               }
               else if (sort_by_num == 2){
                    temp = searchList(rel_volume, temp, rel_volume_below_num, rel_volume_above_num, true);
                    listToExclude = "rel_volume";
               }
               else if (sort_by_num == 3){
                    temp = searchList(volume, temp, volume_below_num, volume_above_num, true);
                    listToExclude = "volume";
               }
               else if (sort_by_num == 4){
                    temp = searchList(volatility, temp, volatility_below_num, volatility_above_num, true);
                    listToExclude = "volatility";
               }
               else if (sort_by_num == 5){
                    temp = searchList(percent, temp, percent_below_num, percent_above_num, true);
                    listToExclude = "percent";
               }
               else if (sort_by_num == 6){
                    temp = searchList(price, temp, price_below_num, price_above_num, false);
                    listToExclude = "price";
               }
               else if (sort_by_num == 7){
                    temp = searchList(market_cap, temp, market_cap_below_num, market_cap_above_num, false);
                    listToExclude = "market_cap";
               }
               else if (sort_by_num == 8){
                    temp = searchList(rel_volume, temp, rel_volume_below_num, rel_volume_above_num, false);
                    listToExclude = "rel_volume";
               }
               else if (sort_by_num == 9){
                    temp = searchList(volume, temp, volume_below_num, volume_above_num, false);
                    listToExclude = "volume";
               }
               else if (sort_by_num == 10){
                    temp = searchList(volatility, temp, volatility_below_num, volatility_above_num, false);
                    listToExclude = "volatility";
               }
               else if (sort_by_num == 11){
                    temp = searchList(percent, temp, percent_below_num, percent_above_num, false);
                    listToExclude = "percent";
               }
               sortListDone = true;
               if (!(((app.getPrice_above_option() == 0) && (app.getPrice_below_option() == 0)) || (listToExclude.equals("price")))){
                   temp = searchList(price, temp, price_below_num, price_above_num, true);
               }
               if (!(((app.getMarket_cap_above_option() == 0) && (app.getMarket_cap_below_option() == 0)) || (listToExclude.equals("market_cap")))){
                   temp = searchList(market_cap, temp, market_cap_below_num, market_cap_above_num, true);
               }
               if (!(((app.getRel_volume_above_option() == 0) && (app.getRel_volume_below_option() == 0)) || (listToExclude.equals("rel_volume")))){
                   temp = searchList(rel_volume, temp, rel_volume_below_num, rel_volume_above_num, true);
               }
               if (!(((app.getVolume_above_option() == 0) && (app.getVolume_below_option() == 0)) || (listToExclude.equals("volume")))){
                    temp = searchList(volume, temp, volume_below_num, volume_above_num, true);
                }
                if (!(((app.getVolatility_above_option() == 0) && (app.getVolatility_below_option() == 0)) || (listToExclude.equals("volatility")))){
                    temp = searchList(volatility, temp, volatility_below_num, volatility_above_num, true);
                }
                if (!(((app.getPercent_above_option() == 0) && (app.getPercent_below_option() == 0)) || (listToExclude.equals("percent")))){
                    temp = searchList(percent, temp, percent_below_num, percent_above_num, true);
                }
                if (!(((app.getAvg_volume_above_option() == 0) && (app.getAvg_volume_below_option() == 0)) || (listToExclude.equals("avg_volume")))){
                    temp = searchList(avg_volume, temp, avg_volume_below_num, avg_volume_above_num, true);
                }
                if (!(((app.getRel_volatility_above_option() == 0) && (app.getRel_volatility_below_option() == 0)) || (listToExclude.equals("rel_volatility")))){
                    temp = searchList(rel_volatility, temp, rel_volatility_below_num, rel_volatility_above_num, true);
                }
                if (!(((app.getNear_3month_high_option() == 0)) || (listToExclude.equals("three_month_high_percent")))){
                    temp = searchList(three_month_high_percent, temp, near_3month_high_num, -1, true);
                }
                if (!(((app.getPercent_4rel_3_above_option() == 0) && (app.getPercent_4rel_3_below_option() == 0)) || (listToExclude.equals("percent_change3to4")))){
                    temp = searchList(percent_change_3to4, temp, percent_4rel_3_below_num, percent_4rel_3_above_num, true);
                }
                if (!(((app.getPercent_3rel_2_above_option() == 0) && (app.getPercent_3rel_2_below_option() == 0)) || (listToExclude.equals("percent_change2to3")))){
                    temp = searchList(percent_change_2to3, temp, percent_3rel_2_below_num, percent_3rel_2_above_num, true);
                }
                if (!(((app.getPercent_2rel_1_above_option() == 0) && (app.getPercent_2rel_1_below_option() == 0)) || (listToExclude.equals("percent_change1to2")))){
                    temp = searchList(percent_change_1to2, temp, percent_2rel_1_below_num, percent_2rel_1_above_num, true);
                }
                if (!(((app.getVol_4rel_3_above_option() == 0) && (app.getVol_4rel_3_below_option() == 0)) || (listToExclude.equals("volume_change3to4")))){
                    temp = searchList(volume_change_3to4, temp, vol_4rel_3_below_num, vol_4rel_3_above_num, true);
                }
                if (!(((app.getVol_3rel_2_above_option() == 0) && (app.getVol_3rel_2_below_option() == 0)) || (listToExclude.equals("volume_change2to3")))){
                    temp = searchList(volume_change_2to3, temp, vol_3rel_2_below_num, vol_3rel_2_above_num, true);
                }
                if (!(((app.getVol_2rel_1_above_option() == 0) && (app.getVol_2rel_1_below_option() == 0)) || (listToExclude.equals("volume_change1to2")))){
                    temp = searchList(volume_change_1to2, temp, vol_2rel_1_below_num, vol_2rel_1_above_num, true);
                }
                if (!(app.getParabolic_1M_option() == 0)){
                    temp = searchList(parabolic_formation_1M, temp, parabolic_1M_num+.1, parabolic_1M_num-.1, true);
                }
                if (!(app.getParabolic_1wk_option() == 0)){
                    temp = searchList(parabolic_formation_1wk, temp, parabolic_1wk_num+.1, parabolic_1wk_num-.1, true);
                }
                if (!(app.getSteady_movement_1M_option() == 0)){
                    temp = searchList(steady_movement_1M, temp, steady_movement_1M_num+.1, steady_movement_1M_num-.1, true);
                }
                if (!(app.getSteady_movement_1wk_option() == 0)){
                    temp = searchList(steady_movement_1wk, temp, steady_movement_1wk_num+.1, steady_movement_1wk_num-.1 , true);
                }
                if (!(((app.getNear_resistance_3M_above_option() == 0) && (app.getNear_resistance_1M_below_option() == 0)))){
                    temp = searchList(near_resistance_3M, temp, near_resistance_3M_below_num, near_resistance_3M_above_num, true);
                }
                if (!(((app.getNear_resistance_1M_above_option() == 0) && (app.getNear_resistance_1M_below_option() == 0)))){
                    temp = searchList(near_resistance_1M, temp, near_resistance_1M_below_num, near_resistance_1M_above_num, true);
                }
                if (!(((app.getNear_support_3M_above_option() == 0) && (app.getNear_support_3M_below_option() == 0)))){
                    temp = searchList(near_support_3M, temp, near_support_3M_below_num, near_support_3M_above_num, true);
                }
                if (!(((app.getNear_support_1M_above_option() == 0) && (app.getNear_support_1M_below_option() == 0)))){
                    temp = searchList(near_support_1M, temp, near_support_1M_below_num, near_support_1M_above_num, true);
                }
                if (!(((app.getNear_resistance_daily_above_option() == 0) && (app.getNear_resistance_daily_below_option() == 0)))){
                    temp = searchList(near_resistance_daily, temp, near_resistance_daily_below_num, near_resistance_daily_above_num, true);
                }
                if (!(((app.getNear_support_daily_above_option() == 0) && (app.getNear_support_daily_below_option() == 0)))){
                    temp = searchList(near_support_daily, temp, near_support_daily_below_num, near_support_daily_above_num, true);
                }
                if (!(((app.getParabolic_1h_option() == 0)))){
                    temp = searchList(parabolic_formation_1h, temp, parabolic_1h_num + 0.1, parabolic_1h_num - 0.1, true);
                }
                if (!(((app.getSteady_movement_intraday_option() == 0)))){
                    temp = searchList(steady_movement_daily, temp, steady_movement_intraday_num + 0.1, steady_movement_intraday_num - 0.1, true);
                }
                if (!(((app.getVolatility_intraday_above_option() == 0) && (app.getVolatility_intraday_below_option() == 0)))){
                    temp = searchList(volatility_daily, temp, volatility_intraday_below_num, volatility_intraday_above_num, true);
                }
                if (temp.size() == 0){
                    ((Button) ((Activity)context).findViewById(button_ids[0])).setText("No tickers found matching this criteria. Try different settings");
                }
                String ticker;
                for(int i = 0; i < temp.size() && i < button_ids.length; i++){
                    ticker = temp.get(i);
                    if(button_ids.length < 11){
                        getTickerData(button_ids[i], ticker, "Premium_Watchlist");
                    }
                    else {
                        getTickerData(button_ids[i], ticker, "Screener");
                    }
                    //set_listener(context, button_ids[i], ticker, "Screener");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String[][] extractTickerData(String str, String t){
        String tick = t;
        String market_cap = getCategoryString(str, "03id");
        String percent = getCategoryString(str, "05id");
        String price = getCategoryString(str, "00id");
        String rel_volume = getCategoryString(str, "02id");
        String volatility = getCategoryString(str, "04id");
        String volume = getCategoryString(str, "01id");
        double num_market_cap = -1, num_percent = -1, num_price = -1, num_rel_volume = -1, num_volatility = -1, num_volume = -1;
        if (!(market_cap.equals("null"))) {
            //Log.d("MARKET_CAP", market_cap);
            num_market_cap = Double.parseDouble(shortNumberExpander(market_cap.trim()));
        }
        if (!(percent.equals("null"))) {
            num_percent = Double.parseDouble(percent.trim());
        }
        if (!(price.equals("null"))) {
            num_price = Double.parseDouble(price.trim());
            price = String.format("%.2f", num_price);
        }
        if (!(rel_volume.equals("null") || rel_volume.equals("nan"))) {
            num_rel_volume = Double.parseDouble(rel_volume.trim());
        }
        if (!(volatility.equals("null"))) {
            num_volatility = Double.parseDouble(volatility.trim());
        }
        if (!(volume.equals("null"))) {
            num_volume = Double.parseDouble(volume.trim());
            volume = largeNumberShortener(volume);
        }
        String[][] output = new String[2][7];
        output[0][0] = tick;
        output[0][1] = price;
        output[0][2] = volume;
        output[0][3] = rel_volume;
        output[0][4] = market_cap;
        output[0][5] = volatility;
        output[0][6] = percent;
        output[1][0] = tick;
        output[1][1] = Double.toString(num_price);
        output[1][2] = Double.toString(num_volume);
        output[1][3] = Double.toString(num_rel_volume);
        output[1][4] = Double.toString(num_market_cap);
        output[1][5] = Double.toString(num_volatility);
        output[1][6] = Double.toString(num_percent);
        return output;
    }

    private String largeNumberShortener(String num){
        String output = null;
        if (num.length() > 9 ){
            output = num.substring(0, num.length() - 7) + "B";
        }
        else if (num.length() > 6 ){
            output = num.substring(0, num.length() - 4) + "M";
        }
        else if (num.length() > 3 ){
            output = num.substring(0, num.length() - 1) + "K";
        }
        else{
            return num;
        }
        output = output.substring(0, output.length() - 3) + "." + output.substring(output.length() - 3);
        return output;
    }

    private String shortNumberExpander(String num){
        String output = num;
        int numAfterDecimal = 0;
        int numZerosToAdd = 0;
        if (output.indexOf(".") != -1){
            numAfterDecimal = output.substring(output.indexOf(".")).length();
        }
        if (output.contains("B")){
            numZerosToAdd = 9 - numAfterDecimal;
            output = output.replace(".", "");
            output = output.replace("B", "");
        }
        if (output.contains("M")){
            numZerosToAdd = 6 - numAfterDecimal;
            output = output.replace(".", "");
            output = output.replace("M", "");
        }
        if (output.contains("K")){
            numZerosToAdd = 3 - numAfterDecimal;
            output = output.replace(".", "");
            output = output.replace("K", "");
        }
        for (int i = 0; i < numZerosToAdd; i++){
            output = output + "0";
        }
        return output;
    }

    private String getCategoryString(String str, String ID){    // Pass in a 3 character ID for the category
        if (str.indexOf(',', str.indexOf(ID)) == -1){
            //Log.d("CAT_STR", "}}}ID = " + ID + "   String = " + str.substring(str.indexOf(ID) + 5, str.indexOf('}', str.indexOf(ID))).replace("\"", "").replace("\'", ""));
            return str.substring(str.indexOf(ID) + 5, str.indexOf('}', str.indexOf(ID))).replace("\"", "").replace("\'", "");
        }
        else {
            //Log.d("CAT_STR", ",,,ID = " + ID + "   String = " + str.substring(str.indexOf(ID) + 5, str.indexOf(',', str.indexOf(ID))).replace("\"", "").replace("\'", ""));
            return str.substring(str.indexOf(ID) + 5, str.indexOf(',', str.indexOf(ID))).replace("\"", "").replace("\'", "");
        }
    }

    private ArrayList<String> getTickerArray(String criteria){
        ArrayList<String> data_array = new ArrayList<String>();

        return data_array;
    }

    protected SpannableString createSpannableString(String[][] info){
        String str0 = info[0][0], str1 = info[0][1], str2 = info[0][2], str3 = info[0][3],
                str4 = info[0][4], str5 = info[0][5], str6 = info[0][6];
        if (str0 == null) str0 = "null";
        if (str1 == null) str1 = "null";
        if (str2 == null) str2 = "null";
        if (str3 == null) str3 = "null";
        if (str4 == null) str4 = "null";
        if (str5 == null) str5 = "null";
        if (str6 == null) str6 = "null";
        int temp_int = str0.length();
        for (int i = 0; i < (5 - temp_int); i++){
            str0 = str0 + " ";
        }
        str0 = str0 + "|";
        temp_int = str1.length();
        for (int i = 0; i < (8 - temp_int); i++){
            str1 = " " + str1 + " ";
        }
        str1 = str1 + "|";
        temp_int = str2.length();
        for (int i = 0; i < (8 - temp_int); i++){
            str2 = " " + str2 + " ";
        }
        str2 = str2 + "|";
        str3 = str3 + "x";
        temp_int = str3.length();
        for (int i = 0; i < (5 - temp_int); i++){
            str3 = " " + str3 + " ";
        }
        str3 = str3 + "|";
        temp_int = str4.length();
        for (int i = 0; i < (8 - temp_int); i++){
            str4 = " " + str4 + " ";
        }
        str4 = str4 + "|";
        str5 = str5 + "%";
        temp_int = str5.length();
        for (int i = 0; i < (6 - temp_int); i++) {
            str5 = " " + str5 + " ";
        }
        str5 = str5 + "|";
        if (str6.contains("-")){
            str6 = str6 + "%";
        }
        else{
            str6 = "+" + str6 + "%";
        }
        temp_int = str6.length();
        for (int i = 0; i < (8 - temp_int); i++) {
            str6 = " " + str6;
        }
        String str_all = str0 + str1 + str2 + str3 + str4 + str5 + str6;
        SpannableString span = new SpannableString(str_all);
        if (str6.contains("-")){
            if (str_all.indexOf(str6, str_all.length() - 10) != -1){
            span.setSpan(new ForegroundColorSpan(Color.parseColor("#e81414")),
                        str_all.indexOf(str6, str_all.length() - 10),
                        str_all.indexOf(str6, str_all.length() - 10) + str6.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        else {
            if (str_all.indexOf(str6, str_all.length() - 10) != -1) {
                span.setSpan(new ForegroundColorSpan(Color.parseColor("#2ce851")),
                        str_all.indexOf(str6, str_all.length() - 10),
                        str_all.indexOf(str6, str_all.length() - 10) + str6.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return span;
    }

    public void set_listener(final Context context, int button_id, final String ticker, final String type) {

        Button btn = ((Activity) context).findViewById(button_id);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Uri uri = Uri.parse("https://www.google.com");
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //context.startActivity(intent);
                //WebView webview = new WebView(context);
                //((Activity) context).setContentView(webview);
                //webview.loadUrl("https://www.google.com/finance?q="+ticker);
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("ticker", ticker);
                context.startActivity(intent);
            }
        });

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // SHOW ADD TO WATCHLIST ITEM MENU
                if (type.equals("Screener") || type.equals("Premium_Watchlist")) {
                    if (add_to_watchlist(ticker)) {
                        Toast.makeText(context, ticker + " added to watchlist", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, ticker + " Error adding ticker", Toast.LENGTH_LONG).show();
                    }
                    return true;
                } else if (type.equals("Watchlist")) {
                    if (remove_from_watchlist(ticker)) {
                        Toast.makeText(context, ticker + " deleted from watchlist", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, MyWatchlistActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    } else {
                        Toast.makeText(context, ticker + " Error removing ticker", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                return true;
            }
        });
    }

    public void setButton_ids(int[] ids){
        this.button_ids = ids;
    }

    public boolean add_to_watchlist(String ticker){
        LocalDatabaseHelper local_db = new LocalDatabaseHelper(context);
        local_db.deleteWatchlistTicker(ticker);
        if (local_db.insertWatchlistTicker(ticker)) {return true;}
        return false;
    }

    public boolean remove_from_watchlist(String ticker){
        LocalDatabaseHelper local_db = new LocalDatabaseHelper(context);
        if (local_db.deleteWatchlistTicker(ticker)) {return true;}
        return false;
    }
}
