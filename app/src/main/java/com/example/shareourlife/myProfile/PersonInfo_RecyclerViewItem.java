package com.example.shareourlife.myProfile;

public class PersonInfo_RecyclerViewItem<T> {
    T t;
    int Type;

    public static final int PERSON_INFO_EDIT_ITEM = 0;
    public static final int PERSON_INFO_radioButton_ITEM = 1;
    public static final int PERSON_INFO_IMG_ITEM = 2;

    //绑定手机号、修改密码
    public static final int PERSON_INFO_SECURITY_ITEM = 3;

    public PersonInfo_RecyclerViewItem(T t, int type) {
        this.t = t;
        Type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
