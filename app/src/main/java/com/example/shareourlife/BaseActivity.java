package com.example.shareourlife;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.shareourlife.MVVM.ActivityTest.Activitytest;
import com.example.shareourlife.MVVM.MvvmActivity;

public abstract class BaseActivity extends MvvmActivity {

    //Navigator类，用于在VM中处理一切页面跳转
    Navigator getNavigator() {
        return  new Navigator() {
            @Override
            public void navigateToTestActivity() {
                toJump(Activitytest.class);
            }

            public void toJump(Class<?> des) {
                Intent intent = new Intent(BaseActivity.this, des);
                startActivity(intent);
            }
        };
    }


}
