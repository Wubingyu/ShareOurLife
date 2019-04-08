package com.example.shareourlife;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

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