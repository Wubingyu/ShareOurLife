package com.example.shareourlife;

import android.content.Intent;
import android.widget.Toast;

import com.example.shareourlife.MVVM.MvvmActivity;
import com.example.shareourlife.myProfile.FollowerlsitActivity;
import com.example.shareourlife.myProfile.FollowingListActivity;
import com.example.shareourlife.myProfile.ProfileActivity;

public abstract class BaseActivity extends MvvmActivity {

    //Navigator类，用于在VM中处理一切页面跳转
    protected Navigator getNavigator() {
        return  new Navigator() {

            @Override
            public void ToProfile() { toJump(ProfileActivity.class); }

            @Override
            public void ToFollowinglistActivity() { toJump(FollowingListActivity.class); }

            @Override
            public void ToFollowerlsitActivity() { toJump(FollowerlsitActivity.class); }

            public void toJump(Class<?> des) {
                Intent intent = new Intent(BaseActivity.this, des);
                startActivity(intent);
            }
        };
    }

    protected void ToastMessage(String text) {
        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
