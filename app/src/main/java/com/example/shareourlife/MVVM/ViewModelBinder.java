package com.example.shareourlife.MVVM;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;

public interface ViewModelBinder {
    void bind(ViewDataBinding viewDataBinding, ViewModel viewModel);
}