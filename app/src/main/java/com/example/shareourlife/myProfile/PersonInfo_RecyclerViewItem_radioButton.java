package com.example.shareourlife.myProfile;

import android.graphics.drawable.Drawable;

public class PersonInfo_RecyclerViewItem_radioButton {
    public static final int RadioButton_MAN = 1;
    public static final int RadioButton_WOMAN = 2;

    private int sex_id; //这个人初始设置是男性还是女性呢
    private boolean Private; //是否是保密的呢？
    int lock_icon, unlock_icon;

    public PersonInfo_RecyclerViewItem_radioButton(int sex_id, boolean aPrivate, int lock_icon, int unlock_icon) {
        this.sex_id = sex_id;
        Private = aPrivate;
        this.lock_icon = lock_icon;
        this.unlock_icon = unlock_icon;
    }

    public int getSex_id() {
        return sex_id;
    }

    public void setSex_id(int sex_id) {
        this.sex_id = sex_id;
    }

    public boolean isPrivate() {
        return Private;
    }

    public void setPrivate(boolean aPrivate) {
        this.Private = aPrivate;
    }
}
