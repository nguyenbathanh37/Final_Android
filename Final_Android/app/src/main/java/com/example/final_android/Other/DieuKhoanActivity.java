package com.example.final_android.Other;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_android.R;

public class DieuKhoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieu_khoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}