package com.example.shareourlife;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import io.reactivex.functions.Action;

/**
 * ViewModel 绑定在MainActivity
 */
public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    Navigator navigator;

    MainViewModel(Navigator navigator) {
        this.navigator = navigator;
    }

    //怎么做页面跳转呢？android-mvvm-root项目中是怎么完成的呢？
    //加一层抽象，通过传递一个Navigator类，来处理跳转。
    //Navigator是通过BaseActivity确定的，里面有跳转到所有页面的方法。所以VM中不需要持有Activity引用
    public final Action Click_toJump = () -> navigator.navigateToTestActivity();

}
