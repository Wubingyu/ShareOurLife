package com.example.shareourlife;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //没用。
/*        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_Design_NoActionBar);
        }*/
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new MainViewModel(getNavigator());
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}