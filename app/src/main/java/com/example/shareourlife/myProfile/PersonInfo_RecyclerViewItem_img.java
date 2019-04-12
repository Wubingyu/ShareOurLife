package com.example.shareourlife.myProfile;

import android.graphics.Bitmap;

public class PersonInfo_RecyclerViewItem_img {
    private String img_tag;
    private int img_id;
    private Bitmap img_bitmap;

    public PersonInfo_RecyclerViewItem_img(String img_tag, int img_id) {
        this.img_tag = img_tag;
        this.img_id = img_id;
    }

    public String getImg_tag() {
        return img_tag;
    }

    public void setImg_tag(String img_tag) {
        this.img_tag = img_tag;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public Bitmap getImg_bitmap() {
        return img_bitmap;
    }

    public void setImg_bitmap(Bitmap img_bitmap) {
        this.img_bitmap = img_bitmap;
    }
}
