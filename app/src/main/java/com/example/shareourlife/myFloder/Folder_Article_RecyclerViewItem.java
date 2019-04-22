package com.example.shareourlife.myFloder;

public class Folder_Article_RecyclerViewItem {
    String title;
    String brief;
    int img_id;

    public Folder_Article_RecyclerViewItem(String title, String brief, int img_id) {
        this.title = title;
        this.brief = brief;
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

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
