package com.example.shareourlife.Entity;

import android.graphics.Bitmap;

import com.example.shareourlife.Entity.utils.Permission;

public class Floder {
    final int Floder_id;  //文件夹的id
    final int user_id;  //创建者的ID
    String name;
    String description;
    Bitmap picture;
    Permission permission;  //对别人可见

    public Floder(int floder_id, int user_id) {
        Floder_id = floder_id;
        this.user_id = user_id;
    }
}
