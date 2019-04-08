package com.example.shareourlife.MVVM;

import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.shareourlife.BR;
import com.example.shareourlife.MVVM.utils.BindingUtils;
import com.example.shareourlife.MVVM.utils.Preconditions;

public abstract class MvvmActivity extends AppCompatActivity {

    private ViewDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayoutId());

        //为了在其他地方，也能进行动态绑定，我们new一个Binder类
//        Preconditions.checkNotNull(binding, "bindingClass in " + MvvmActivity.this.getClass().getSimpleName());
//        binding.setVariable(BR.vm, createViewModel());

        getDefaultBinder().bind(binding, createViewModel());
    }

    @NonNull
    private ViewModelBinder getDefaultBinder() {
        ViewModelBinder defaultBinder = BindingUtils.getDefaultBinder();
        Preconditions.checkNotNull(defaultBinder, "Default Binder");
        return defaultBinder;
    }

    @NonNull
    protected abstract ViewModel createViewModel();

    @LayoutRes
    protected abstract int getLayoutId();
}
