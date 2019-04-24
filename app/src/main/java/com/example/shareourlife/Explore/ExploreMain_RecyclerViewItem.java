package com.example.shareourlife.Explore;

public class ExploreMain_RecyclerViewItem {
    int img_id;
    String title;
    String brief;
    String date;

    int user_img_id;
    String user_name;

    public ExploreMain_RecyclerViewItem(int img_id, String title, String brief, String date, int user_img_id, String user_name) {
        this.img_id = img_id;
        this.title = title;
        this.brief = brief;
        this.date = date;
        this.user_img_id = user_img_id;
        this.user_name = user_name;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_img_id() {
        return user_img_id;
    }

    public void setUser_img_id(int user_img_id) {
        this.user_img_id = user_img_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}