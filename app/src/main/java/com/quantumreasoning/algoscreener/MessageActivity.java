package com.quantumreasoning.algoscreener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kendall on 11/6/2017.
 */

public class MessageActivity extends AppCompatActivity{
    private String title;
    private String body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        title = getIntent().getStringExtra("title");
        body = getIntent().getStringExtra("body");

        TextView title_view = findViewById(R.id.message_title);
        TextView body_view = findViewById(R.id.message_body);
        title_view.setText(title);
        body_view.setText(body);

        Button got_it_button = findViewById(R.id.got_it_button);
        got_it_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MessageActivity.this.finish();
            }
        });

    }

}
