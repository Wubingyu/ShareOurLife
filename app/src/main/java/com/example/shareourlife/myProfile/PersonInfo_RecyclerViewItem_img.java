package com.example.shareourlife.myProfile;

public class PersonInfo_RecyclerViewItem_img {
    private String img_tag;
    private int img_id;

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
}
