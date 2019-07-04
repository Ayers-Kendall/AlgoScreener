package com.quantumreasoning.algoscreener;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kendall on 10/20/2017.
 */

public class MyApplication extends Application {
    private int price_above_option = 0;
    private int price_below_option = 0;
    private int market_cap_above_option = 6;
    private int market_cap_below_option = 0;
    private int rel_volume_above_option = 0;
    private int rel_volume_below_option = 0;
    private int percent_above_option = 0;
    private int percent_below_option = 0;
    private int volatility_above_option = 0;
    private int volatility_below_option = 0;
    private int rel_volatility_above_option = 0;
    private int rel_volatility_below_option = 0;
    private int volume_above_option = 0;
    private int volume_below_option = 0;
    private int avg_volume_above_option = 0;
    private int avg_volume_below_option = 0;
    private int vol_4rel_3_above_option = 0;
    private int vol_4rel_3_below_option = 0;
    private int vol_3rel_2_above_option= 0;
    private int vol_3rel_2_below_option= 0;
    private int vol_2rel_1_above_option= 0;
    private int vol_2rel_1_below_option= 0;
    private int percent_4rel_3_above_option = 0;
    private int percent_4rel_3_below_option = 0;
    private int percent_3rel_2_above_option = 0;
    private int percent_3rel_2_below_option = 0;
    private int percent_2rel_1_above_option = 0;
    private int percent_2rel_1_below_option = 0;
    private int near_3month_high_option = 0;
    private int near_resistance_daily_above_option = 0;
    private int near_resistance_daily_below_option = 0;
    private int near_resistance_1M_above_option = 0;
    private int near_resistance_1M_below_option = 0;
    private int near_resistance_3M_above_option = 0;
    private int near_resistance_3M_below_option = 0;
    private int sort_by_option = 5;
    private int near_support_daily_above_option = 0;
    private int near_support_daily_below_option = 0;
    private int near_support_1M_above_option = 0;
    private int near_support_1M_below_option = 0;
    private int near_support_3M_above_option = 0;
    private int near_support_3M_below_option = 0;
    private int parabolic_1h_option = 0;
    private int parabolic_1wk_option = 0;
    private int parabolic_1M_option = 0;
    private int steady_movement_1M_option = 0;
    private int steady_movement_1wk_option = 0;
    private int steady_movement_intraday_option = 0;
    private int volatility_intraday_above_option = 0;
    private int volatility_intraday_below_option = 0;

    public int getSteady_movement_1M_option() {
        return steady_movement_1M_option;
    }

    public void setSteady_movement_1M_option(int steady_movement_1M_option) {
        this.steady_movement_1M_option = steady_movement_1M_option;
    }

    public int getSteady_movement_1wk_option() {
        return steady_movement_1wk_option;
    }

    public void setSteady_movement_1wk_option(int steady_movement_1wk_option) {
        this.steady_movement_1wk_option = steady_movement_1wk_option;
    }

    public int getSteady_movement_intraday_option() {
        return steady_movement_intraday_option;
    }

    public void setSteady_movement_intraday_option(int steady_movement_intraday_option) {
        this.steady_movement_intraday_option = steady_movement_intraday_option;
    }

    public int getPrice_above_option() {
        return price_above_option;
    }

    public void setPrice_above_option(int price_above_option) {
        this.price_above_option = price_above_option;
    }

    public int getPrice_below_option() {
        return price_below_option;
    }

    public void setPrice_below_option(int price_below_option) {
        this.price_below_option = price_below_option;
    }

    public int getMarket_cap_above_option() {
        return market_cap_above_option;
    }

    public void setMarket_cap_above_option(int market_cap_above_option) {
        this.market_cap_above_option = market_cap_above_option;
    }

    public int getMarket_cap_below_option() {
        return market_cap_below_option;
    }

    public void setMarket_cap_below_option(int market_cap_below_option) {
        this.market_cap_below_option = market_cap_below_option;
    }

    public int getRel_volume_above_option() {
        return rel_volume_above_option;
    }

    public void setRel_volume_above_option(int rel_volume_above_option) {
        this.rel_volume_above_option = rel_volume_above_option;
    }

    public int getRel_volume_below_option() {
        return rel_volume_below_option;
    }

    public void setRel_volume_below_option(int rel_volume_below_option) {
        this.rel_volume_below_option = rel_volume_below_option;
    }

    public int getPercent_above_option() {
        return percent_above_option;
    }

    public void setPercent_above_option(int percent_above_option) {
        this.percent_above_option = percent_above_option;
    }

    public int getPercent_below_option() {
        return percent_below_option;
    }

    public void setPercent_below_option(int percent_below_option) {
        this.percent_below_option = percent_below_option;
    }

    public int getVolatility_above_option() {
        return volatility_above_option;
    }

    public void setVolatility_above_option(int volatility_above_option) {
        this.volatility_above_option = volatility_above_option;
    }

    public int getVolatility_below_option() {
        return volatility_below_option;
    }

    public void setVolatility_below_option(int volatility_below_option) {
        this.volatility_below_option = volatility_below_option;
    }

    public int getRel_volatility_above_option() {
        return rel_volatility_above_option;
    }

    public void setRel_volatility_above_option(int rel_volatility_above_option) {
        this.rel_volatility_above_option = rel_volatility_above_option;
    }

    public int getRel_volatility_below_option() {
        return rel_volatility_below_option;
    }

    public void setRel_volatility_below_option(int rel_volatility_below_option) {
        this.rel_volatility_below_option = rel_volatility_below_option;
    }

    public int getVolume_above_option() {
        return volume_above_option;
    }

    public void setVolume_above_option(int volume_above_option) {
        this.volume_above_option = volume_above_option;
    }

    public int getVolume_below_option() {
        return volume_below_option;
    }

    public void setVolume_below_option(int volume_below_option) {
        this.volume_below_option = volume_below_option;
    }

    public int getAvg_volume_above_option() {
        return avg_volume_above_option;
    }

    public void setAvg_volume_above_option(int avg_volume_above_option) {
        this.avg_volume_above_option = avg_volume_above_option;
    }

    public int getAvg_volume_below_option() {
        return avg_volume_below_option;
    }

    public void setAvg_volume_below_option(int avg_volume_below_option) {
        this.avg_volume_below_option = avg_volume_below_option;
    }

    public int getVol_4rel_3_above_option() {
        return vol_4rel_3_above_option;
    }

    public void setVol_4rel_3_above_option(int vol_4rel_3_above_option) {
        this.vol_4rel_3_above_option = vol_4rel_3_above_option;
    }

    public int getVol_4rel_3_below_option() {
        return vol_4rel_3_below_option;
    }

    public void setVol_4rel_3_below_option(int vol_4rel_3_below_option) {
        this.vol_4rel_3_below_option = vol_4rel_3_below_option;
    }

    public int getVol_3rel_2_above_option() {
        return vol_3rel_2_above_option;
    }

    public void setVol_3rel_2_above_option(int vol_3rel_2_above_option) {
        this.vol_3rel_2_above_option = vol_3rel_2_above_option;
    }

    public int getVol_3rel_2_below_option() {
        return vol_3rel_2_below_option;
    }

    public void setVol_3rel_2_below_option(int vol_3rel_2_below_option) {
        this.vol_3rel_2_below_option = vol_3rel_2_below_option;
    }

    public int getVol_2rel_1_above_option() {
        return vol_2rel_1_above_option;
    }

    public void setVol_2rel_1_above_option(int vol_2rel_1_above_option) {
        this.vol_2rel_1_above_option = vol_2rel_1_above_option;
    }

    public int getVol_2rel_1_below_option() {
        return vol_2rel_1_below_option;
    }

    public void setVol_2rel_1_below_option(int vol_2rel_1_below_option) {
        this.vol_2rel_1_below_option = vol_2rel_1_below_option;
    }

    public int getPercent_4rel_3_above_option() {
        return percent_4rel_3_above_option;
    }

    public void setPercent_4rel_3_above_option(int percent_4rel_3_above_option) {
        this.percent_4rel_3_above_option = percent_4rel_3_above_option;
    }

    public int getPercent_4rel_3_below_option() {
        return percent_4rel_3_below_option;
    }

    public void setPercent_4rel_3_below_option(int percent_4rel_3_below_option) {
        this.percent_4rel_3_below_option = percent_4rel_3_below_option;
    }

    public int getPercent_3rel_2_above_option() {
        return percent_3rel_2_above_option;
    }

    public void setPercent_3rel_2_above_option(int percent_3rel_2_above_option) {
        this.percent_3rel_2_above_option = percent_3rel_2_above_option;
    }

    public int getPercent_3rel_2_below_option() {
        return percent_3rel_2_below_option;
    }

    public void setPercent_3rel_2_below_option(int percent_3rel_2_below_option) {
        this.percent_3rel_2_below_option = percent_3rel_2_below_option;
    }

    public int getPercent_2rel_1_above_option() {
        return percent_2rel_1_above_option;
    }

    public void setPercent_2rel_1_above_option(int percent_2rel_1_above_option) {
        this.percent_2rel_1_above_option = percent_2rel_1_above_option;
    }

    public int getPercent_2rel_1_below_option() {
        return percent_2rel_1_below_option;
    }

    public void setPercent_2rel_1_below_option(int percent_2rel_1_below_option) {
        this.percent_2rel_1_below_option = percent_2rel_1_below_option;
    }

    public int getNear_3month_high_option() {
        return near_3month_high_option;
    }

    public void setNear_3month_high_option(int near_3month_high_option) {
        this.near_3month_high_option = near_3month_high_option;
    }

    public int getNear_resistance_daily_above_option() {
        return near_resistance_daily_above_option;
    }

    public void setNear_resistance_daily_above_option(int near_resistance_daily_above_option) {
        this.near_resistance_daily_above_option = near_resistance_daily_above_option;
    }

    public int getNear_resistance_daily_below_option() {
        return near_resistance_daily_below_option;
    }

    public void setNear_resistance_daily_below_option(int near_resistance_daily_below_option) {
        this.near_resistance_daily_below_option = near_resistance_daily_below_option;
    }

    public int getNear_resistance_1M_above_option() {
        return near_resistance_1M_above_option;
    }

    public void setNear_resistance_1M_above_option(int near_resistance_1M_above_option) {
        this.near_resistance_1M_above_option = near_resistance_1M_above_option;
    }

    public int getNear_resistance_1M_below_option() {
        return near_resistance_1M_below_option;
    }

    public void setNear_resistance_1M_below_option(int near_resistance_1M_below_option) {
        this.near_resistance_1M_below_option = near_resistance_1M_below_option;
    }

    public int getNear_resistance_3M_above_option() {
        return near_resistance_3M_above_option;
    }

    public void setNear_resistance_3M_above_option(int near_resistance_3M_above_option) {
        this.near_resistance_3M_above_option = near_resistance_3M_above_option;
    }

    public int getNear_resistance_3M_below_option() {
        return near_resistance_3M_below_option;
    }

    public void setNear_resistance_3M_below_option(int near_resistance_3M_below_option) {
        this.near_resistance_3M_below_option = near_resistance_3M_below_option;
    }

    public int getSort_by_option() {
        return sort_by_option;
    }

    public void setSort_by_option(int sort_by_option) {
        this.sort_by_option = sort_by_option;
    }

    public int getNear_support_daily_above_option() {
        return near_support_daily_above_option;
    }

    public void setNear_support_daily_above_option(int near_support_daily_above_option) {
        this.near_support_daily_above_option = near_support_daily_above_option;
    }

    public int getNear_support_daily_below_option() {
        return near_support_daily_below_option;
    }

    public void setNear_support_daily_below_option(int near_support_daily_below_option) {
        this.near_support_daily_below_option = near_support_daily_below_option;
    }

    public int getNear_support_1M_above_option() {
        return near_support_1M_above_option;
    }

    public void setNear_support_1M_above_option(int near_support_1M_above_option) {
        this.near_support_1M_above_option = near_support_1M_above_option;
    }

    public int getNear_support_1M_below_option() {
        return near_support_1M_below_option;
    }

    public void setNear_support_1M_below_option(int near_support_1M_below_option) {
        this.near_support_1M_below_option = near_support_1M_below_option;
    }

    public int getNear_support_3M_above_option() {
        return near_support_3M_above_option;
    }

    public void setNear_support_3M_above_option(int near_support_3M_above_option) {
        this.near_support_3M_above_option = near_support_3M_above_option;
    }

    public int getNear_support_3M_below_option() {
        return near_support_3M_below_option;
    }

    public void setNear_support_3M_below_option(int near_support_3M_below_option) {
        this.near_support_3M_below_option = near_support_3M_below_option;
    }

    public int getParabolic_1h_option() {
        return parabolic_1h_option;
    }

    public void setParabolic_1h_option(int parabolic_1h_option) {
        this.parabolic_1h_option = parabolic_1h_option;
    }

    public int getParabolic_1wk_option() {
        return parabolic_1wk_option;
    }

    public void setParabolic_1wk_option(int parabolic_1wk_option) {
        this.parabolic_1wk_option = parabolic_1wk_option;
    }

    public int getParabolic_1M_option() {
        return parabolic_1M_option;
    }

    public void setParabolic_1M_option(int parabolic_1M_option) {
        this.parabolic_1M_option = parabolic_1M_option;
    }

    public int getVolatility_intraday_above_option() {
        return volatility_intraday_above_option;
    }

    public void setVolatility_intraday_above_option(int volatility_intraday_above_option) {
        this.volatility_intraday_above_option = volatility_intraday_above_option;
    }

    public int getVolatility_intraday_below_option() {
        return volatility_intraday_below_option;
    }

    public void setVolatility_intraday_below_option(int volatility_intraday_below_option) {
        this.volatility_intraday_below_option = volatility_intraday_below_option;
    }



    public boolean saveSettings(String name){
        //Log.d("SAVE", name);
        LocalDatabaseHelper local_db = new LocalDatabaseHelper(this);
        if (local_db.getScreenerSettingsString(name).length() < 11) {
            local_db.insertScreenerSettings(name, settingsToString());
            return true;
        }
        return false;
    }

    public String settingsToString(){
        String output = "|" + price_above_option + "|" + price_below_option + "|" + market_cap_above_option + "|" + market_cap_below_option +
                "|" + rel_volume_above_option + "|" + rel_volume_below_option + "|" + percent_above_option + "|" + percent_below_option +
                "|" + volatility_above_option + "|" + volatility_below_option + "|" + rel_volatility_above_option + "|" + rel_volatility_below_option +
                "|" + volume_above_option + "|" + volume_below_option + "|" + avg_volume_above_option + "|" + avg_volume_below_option + "|" + sort_by_option +
                "|" + near_3month_high_option + "|" + near_resistance_3M_above_option + "|" + near_resistance_3M_below_option + "|" + near_resistance_1M_above_option +
                "|" + near_resistance_1M_below_option + "|" + near_support_3M_above_option + "|" + near_support_3M_below_option + "|" + near_support_1M_above_option +
                "|" + near_support_1M_below_option + "|" + parabolic_1M_option + "|" + parabolic_1wk_option + "|" + percent_4rel_3_above_option + "|" + percent_4rel_3_below_option +
                "|" + percent_3rel_2_above_option + "|" + percent_3rel_2_below_option + "|" + percent_2rel_1_above_option + "|" + percent_2rel_1_below_option +
                "|" + vol_4rel_3_above_option + "|" + vol_4rel_3_below_option + "|" + vol_3rel_2_above_option + "|" + vol_3rel_2_below_option + "|" + vol_2rel_1_above_option +
                "|" + vol_2rel_1_below_option + "|" + near_resistance_daily_above_option + "|" + near_resistance_daily_below_option +
                "|" + near_support_daily_above_option + "|" + near_support_daily_below_option + "|" + parabolic_1h_option + "|" + steady_movement_intraday_option +
                "|" + volatility_intraday_above_option + "|" + volatility_intraday_below_option + "|" + steady_movement_1M_option + "|" + steady_movement_1wk_option + "|";
        return output;
    }

    public boolean SettingsFromString(String str){
        if (str.length() < 11){ return false;}
        List<Integer> sub = new ArrayList<>();
        for (int i = 0; i < str.length()-2; i++){
            sub.add(Integer.parseInt(str.substring(str.indexOf("|", i)+1, str.indexOf("|", i+2))));
            i = str.indexOf("|", i+2)-1;
            Log.d("SUBSTRING", sub.toString());
        }
        price_above_option = sub.get(0);
        price_below_option = sub.get(1);
        market_cap_above_option = sub.get(2);
        market_cap_below_option = sub.get(3);
        rel_volume_above_option = sub.get(4);
        rel_volume_below_option = sub.get(5);
        percent_above_option = sub.get(6);
        percent_below_option = sub.get(7);
        volatility_above_option = sub.get(8);
        volatility_below_option = sub.get(9);
        rel_volatility_above_option = sub.get(10);
        rel_volatility_below_option = sub.get(11);
        volume_above_option = sub.get(12);
        volume_below_option = sub.get(13);
        avg_volume_above_option = sub.get(14);
        avg_volume_below_option = sub.get(15);

        sort_by_option = sub.get(16);
        near_3month_high_option = sub.get(17);

        near_resistance_3M_above_option = sub.get(18);
        near_resistance_3M_below_option = sub.get(19);
        near_resistance_1M_above_option = sub.get(20);
        near_resistance_1M_below_option = sub.get(21);
        near_support_3M_above_option = sub.get(22);
        near_support_3M_below_option = sub.get(23);
        near_support_1M_above_option = sub.get(24);
        near_support_1M_below_option = sub.get(25);

        parabolic_1M_option = sub.get(26);
        parabolic_1wk_option = sub.get(27);

        percent_4rel_3_above_option = sub.get(28);
        percent_4rel_3_below_option = sub.get(29);
        percent_3rel_2_above_option = sub.get(30);
        percent_3rel_2_below_option = sub.get(31);
        percent_2rel_1_above_option = sub.get(32);
        percent_2rel_1_below_option = sub.get(33);
        vol_4rel_3_above_option = sub.get(34);
        vol_4rel_3_below_option = sub.get(35);
        vol_3rel_2_above_option= sub.get(36);
        vol_3rel_2_below_option = sub.get(37);
        vol_2rel_1_above_option= sub.get(38);
        vol_2rel_1_below_option = sub.get(39);
        near_resistance_daily_above_option = sub.get(40);
        near_resistance_daily_below_option = sub.get(41);
        near_support_daily_above_option = sub.get(42);
        near_support_daily_below_option = sub.get(43);

        parabolic_1h_option = sub.get(44);
        steady_movement_intraday_option = sub.get(45);
        volatility_intraday_above_option = sub.get(46);
        volatility_intraday_below_option = sub.get(47);

        steady_movement_1M_option = sub.get(48);
        steady_movement_1wk_option = sub.get(49);
        return true;
    }
    
}
