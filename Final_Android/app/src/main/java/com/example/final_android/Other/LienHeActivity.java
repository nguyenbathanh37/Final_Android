package com.example.final_android.Other;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_android.R;

public class LienHeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}