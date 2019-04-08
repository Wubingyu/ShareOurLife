package com.example.shareourlife.myProfile;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.graphics.Bitmap;

import com.example.shareourlife.Navigator;

import java.time.LocalDate;

import io.reactivex.functions.Action;


//个人信息展示与修改，需要绑定：昵称，头像，E-mail TEL sexual Birthday age addAppDate Introduce-card
public class ProfileViewModel extends ViewModel {
    Navigator navigator;
    ObservableField<String> user_name;

    public ProfileViewModel(Navigator navigator) {
        this.navigator = navigator;
    }

    public final Action Jump_toFollowingList = () -> navigator.ToFollowinglistActivity();

    public final Action Jump_toFollowerList = () -> navigator.ToFollowerlsitActivity();

}
