package com.example.shareourlife.MVVM.utils;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shareourlife.MVVM.ViewModelBinder;

public class BindingUtils {

    @Nullable
    private static ViewModelBinder defaultBinder = null;


    @Nullable
    public static ViewModelBinder getDefaultBinder() {
        return defaultBinder;
    }

    public static void setDefaultBinder(@NonNull ViewModelBinder viewModelBinder) {
        defaultBinder = viewModelBinder;
    }
}
