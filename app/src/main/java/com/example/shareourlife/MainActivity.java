package com.example.shareourlife;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    //createViewModel 返回的是什么呢？
    //懂了，
    // createViewModel返回的是一个ViewModel，继承自ViewModel。
    //ViewModel是绑定给View的，并且包含View所需的所有信息
    //而我们的Activity又是通过集成MvvmActivity进行绑定的，
    // 那么MvvmActivity怎么能绑定各个activity的 不同layout 与 各自对应的ViewModel 呢？

    //就是使用了createViewModel方法 和 getLayoutID方法
    //getLayout方法，就是绑定layout
    //createViewModel就是绑定ViewModel，并且在绑定这个ViewModel的时候，通过构造方法，把需要的内容全部传给实例ViewModel。

    //得到了ViewModel，和 Layout，来看看MvvmActivity如何来绑定他们
    //getDefaultBinder().bind(binding, createViewModel());
    //getDefaultBinder 望文生义：得到一个binder
    //bind() 开始绑定
    //createViewModel 就是我们在实体类中声明的方法，会返回我们该实体类需要的 实体ViewModel
    protected ViewModel createViewModel() {
//        getNavigator方法继承自BaseActivity
        return new MainViewModel(getNavigator());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}