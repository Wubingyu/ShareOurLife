package com.example.shareourlife.myProfile;

public class PersonInfo_RecyclerViewItem_edit {
    private String edit_tag;
    private String edit_context;

    public PersonInfo_RecyclerViewItem_edit(String edit_tag, String edit_context) {
        this.edit_tag = edit_tag;
        this.edit_context = edit_context;
    }

    public String getEdit_tag() {
        return edit_tag;
    }

    public void setEdit_tag(String edit_tag) {
        this.edit_tag = edit_tag;
    }

    public String getEdit_context() {
        return edit_context;
    }

    public void setEdit_context(String edit_context) {
        this.edit_context = edit_context;
    }
}
