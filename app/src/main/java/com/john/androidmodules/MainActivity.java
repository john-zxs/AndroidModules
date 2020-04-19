package com.john.androidmodules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.john.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected int setLayout(Bundle savedInstanceState) {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }
}
