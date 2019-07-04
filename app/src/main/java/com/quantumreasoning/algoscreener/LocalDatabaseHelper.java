package com.quantumreasoning.algoscreener;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Kendall on 10/2/2017.
 */

public class LocalDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_data.db";
    public static final String TABLE_WATCHLIST = "user_watchlist";
    public static final String TABLE_SCREENER_SETTINGS = "user_screener_settings";
    public static final String TABLE_USER_DEFAULTS = "user_defaults";
    public static final String COLUMN_SETTINGS_NAME = "user_settings_name";
    public static final String COLUMN_SETTINGS_STRING = "user_settings_string";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TICKER = "ticker";
    public static final String COLUMN_DEFAULT_NAME = "default_name";
    public static final String COLUMN_DEFAULT_VAL = "default_val";


    public LocalDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_WATCHLIST + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TICKER + " TEXT );");
        db.execSQL("CREATE TABLE " + TABLE_SCREENER_SETTINGS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SETTINGS_NAME + " TEXT," + COLUMN_SETTINGS_STRING + " TEXT );");
        db.execSQL("CREATE TABLE " + TABLE_USER_DEFAULTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DEFAULT_NAME + " TEXT," + COLUMN_DEFAULT_VAL + " INTEGER );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,  int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_WATCHLIST);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SCREENER_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER_DEFAULTS);
        onCreate(db);
    }

    public boolean insertWatchlistTicker(String ticker){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        if (ticker != null){
            vals.put(COLUMN_TICKER, ticker);
            if (db.insert(TABLE_WATCHLIST, null, vals) == -1){
                db.close();
                return false;
            }
            db.close();
            return true;
        }
        db.close();
        return true;
    }

    public ArrayList<String> getWatchlistList() {
        ArrayList<String> output = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = getTableData(TABLE_WATCHLIST);
        if (data.getCount() == 0) {
            // Not found
        }
        else {
            while (data.moveToNext()) {
                output.add((data.getString(1).toString()));   // 1 is the column index
            }
        }
        //Log.d("watchlist", "list = " + output.toString());
        return output;
    }

    public boolean deleteWatchlistTicker(String ticker){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WATCHLIST + " WHERE " + COLUMN_TICKER + "=\"" + ticker + "\";");
        return true;
    }

    public boolean insertDefault(String name, int val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(COLUMN_DEFAULT_NAME, name);
        vals.put(COLUMN_DEFAULT_VAL, val);
        if (db.insert(TABLE_USER_DEFAULTS, null, vals) == -1) {
            db.close();
            return false;
        }
        db.close();
        return true;
    }

    public boolean insertScreenerSettings(String name, String settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        if (name.equals("Default")){
            deleteScreenerSettings("Default");
        }

        if (settings != null) {
            vals.put(COLUMN_SETTINGS_NAME, name);
            vals.put(COLUMN_SETTINGS_STRING, settings);
            if (db.insert(TABLE_SCREENER_SETTINGS, null, vals) == -1) {
                db.close();
                return false;
            }
            db.close();
            return true;
        }
        db.close();
        return true;
    }

    public boolean deleteScreenerSettings(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SCREENER_SETTINGS + " WHERE " + COLUMN_SETTINGS_NAME + "=\"" + name + "\";");
        return true;
    }

    public Cursor getTableData(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+table_name, null);
        return result;
    }

    public String getScreenerSettingsString(String name){
        SQLiteDatabase local_db = this.getWritableDatabase();
        Cursor data = getTableData("user_screener_settings");
        if (data.getCount() == 0){
            // Not found
        }
        else {
            StringBuffer buffer = new StringBuffer();
            while (data.moveToNext()) {
                if (data.getString(1).toString().equals(name)) {
                    buffer.append(data.getString(2));   // 2 is the column index
                }
            }
            if(buffer.toString().length() != 0){
                return buffer.toString();
            }
        }
        return "-1";
    }

}
