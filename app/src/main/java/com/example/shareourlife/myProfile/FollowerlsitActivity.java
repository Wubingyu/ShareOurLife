package com.example.shareourlife.myProfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.example.shareourlife.R;

public class FollowerlsitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_Design_NoActionBar);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followerlsit);
    }
}
