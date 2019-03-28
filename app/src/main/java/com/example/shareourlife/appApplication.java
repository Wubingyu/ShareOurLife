package com.example.shareourlife;

import android.app.Application;
import android.content.Context;

import com.example.shareourlife.MVVM.BindingAdapters;
import com.example.shareourlife.MVVM.utils.BindingUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class appApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        appApplication application = (appApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        BindingUtils.setDefaultBinder(BindingAdapters.defaultBinder);

    }
}