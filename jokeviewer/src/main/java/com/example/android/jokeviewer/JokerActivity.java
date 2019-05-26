package com.example.android.jokeviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokerActivity extends AppCompatActivity {

    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);
        mTextView = findViewById(R.id.tv_joke_holder);
        Intent intent = getIntent();
        String joke = intent.getStringExtra("joke");
        mTextView.setText(joke);
    }


}


