package com.example.shareourlife.myProfile;

public class PersonInfo_RecyclerViewItem_security {
    private int security_tag_icon;  //手机图标、密码图标
    private String security_tag; //用户名、手机号、邮箱、修改密码
    private String security_context; //user_name 151xxxx3671
    private String security_MB_text; //已绑定
    private int security_MB_icon_OK;  //
    private int security_MB_icon_NOTOK; //

    public PersonInfo_RecyclerViewItem_security(int security_tag_icon, String security_tag, String security_context, String security_MB_text, int security_MB_icon_OK, int security_MB_icon_NOTOK) {
        this.security_tag_icon = security_tag_icon;
        this.security_tag = security_tag;
        this.security_context = security_context;
        this.security_MB_text = security_MB_text;
        this.security_MB_icon_OK = security_MB_icon_OK;
        this.security_MB_icon_NOTOK = security_MB_icon_NOTOK;
    }

    public int getSecurity_tag_icon() {
        return security_tag_icon;
    }

    public void setSecurity_tag_icon(int security_tag_icon) {
        this.security_tag_icon = security_tag_icon;
    }

    public String getSecurity_tag() {
        return security_tag;
    }

    public void setSecurity_tag(String security_tag) {
        this.security_tag = security_tag;
    }

    public String getSecurity_context() {
        return security_context;
    }

    public void setSecurity_context(String security_context) {
        this.security_context = security_context;
    }

    public String getSecurity_MB_text() {
        return security_MB_text;
    }

    public void setSecurity_MB_text(String security_MB_text) {
        this.security_MB_text = security_MB_text;
    }

    public int getSecurity_MB_icon_OK() {
        return security_MB_icon_OK;
    }

    public void setSecurity_MB_icon_OK(int security_MB_icon_OK) {
        this.security_MB_icon_OK = security_MB_icon_OK;
    }

    public int getSecurity_MB_icon_NOTOK() {
        return security_MB_icon_NOTOK;
    }

    public void setSecurity_MB_icon_NOTOK(int security_MB_icon_NOTOK) {
        this.security_MB_icon_NOTOK = security_MB_icon_NOTOK;
    }
}
