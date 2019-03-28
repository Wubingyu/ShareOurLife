package com.example.shareourlife.MVVM;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingConversion;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.shareourlife.BR;

import io.reactivex.functions.Action;

public class BindingAdapters {

    @NonNull
    public static final ViewModelBinder defaultBinder = new ViewModelBinder() {
        @Override
        public void bind(ViewDataBinding viewDataBinding, ViewModel viewModel) {
            viewDataBinding.setVariable(BR.vm, viewModel);
        }
    };


    //转换！！
    //将Action 转换为View.onClickLickener
    //然后DataBinding会自动在绑定的方法中找到符合要求的方法进行转换。
    //但是，有可能影响到其他属性
    // 例如这个 @BindingConversion- convertColorToString 就会影响到android:visibility, 因为他们都是都符合从 int 到 int 的转换。  https://juejin.im/entry/578c6bc77db2a2005c003a58
    @BindingConversion
    public static View.OnClickListener toOnClickListener(final Action listener) {
        if (listener != null) {
            return view -> {
                try {
                    listener.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        } else {
            return null;
        }
    }

}
