package com.example.shareourlife;

/**
 * 直接在VM中是没有办法完成页面跳转的
 * 那么怎么办呢？那就使用一个类，来完成跳转
 * 这个类，应该是在Activity中定义好的，通过Activity创建VM的时候传递给VM，然后VM就用这个类来完成跳转
 * 定义在BaseActivity中是因为，new Intent(BaseActivity.this, destination.class)之后，所有的继承在BaseActivity的Activity都可以直接调用
 */
public interface Navigator {
    void navigateToTestActivity();


}
