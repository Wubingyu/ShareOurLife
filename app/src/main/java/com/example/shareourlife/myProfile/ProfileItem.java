package com.example.shareourlife.myProfile;

public class ProfileItem {
    private int item_imaId;
    private String item_text;
    private boolean showSwitch;

    public ProfileItem(int item_imaId, String item_text, Boolean isShowSwitch) {
        this.item_imaId = item_imaId;
        this.item_text = item_text;
        showSwitch = isShowSwitch;
    }

    public int getItem_imaId() {
        return item_imaId;
    }

    public void setItem_imaId(int item_imaId) {
        this.item_imaId = item_imaId;
    }

    public String getItem_text() {
        return item_text;
    }

    public void setItem_text(String item_text) {
        this.item_text = item_text;
    }

    public boolean isShowSwitch() {
        return showSwitch;
    }
}

