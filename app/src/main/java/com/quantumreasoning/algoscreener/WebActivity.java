package com.quantumreasoning.algoscreener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by Kendall on 10/10/2017.
 */

public class WebActivity extends AppCompatActivity {
    private WebView web;
    private String ticker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ticker = "null";
            }
            else{
                ticker = extras.getString("ticker");
            }
        }
        else{
            ticker = (String) savedInstanceState.getSerializable("ticker");
        }

        final LocalDatabaseHelper local_db = new LocalDatabaseHelper(this);
        final Button btn_add_to_watchlist = findViewById(R.id.add_to_watchlist_from_web);
        btn_add_to_watchlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                local_db.deleteWatchlistTicker(ticker);     //If it already exists, just delete and re-add
                local_db.insertWatchlistTicker(ticker);
                btn_add_to_watchlist.setText(ticker + " Added to Watchlist");
            }
        });

        web = findViewById(R.id.webview);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.setInitialScale(1);
        web.setWebViewClient(new myWebClient());
        web.loadUrl("https://finance.google.com/finance?q="+ticker);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()){
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class myWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView web, String url){
            web.loadUrl(url);
            return true;
        }
    }
}

