package com.example.shareourlife.myProfile;

public class FollowingList_RecyclerView_Item {
    private int headImg_id;
    private String user_Name;
    private String NoteName;
    private int notice_time;    //多久获得一次消息推送
//    private int following_GroupID //关注分组就不用保存了，它从哪个分组里调用进来的，直接用就可以了

    public FollowingList_RecyclerView_Item(int headImg_id, String user_Name, String noteName, int notice_time) {
        this.headImg_id = headImg_id;
        this.user_Name = user_Name;
        NoteName = noteName;
        this.notice_time = notice_time;
    }

    public int getHeadImg_id() {
        return headImg_id;
    }

    public void setHeadImg_id(int headImg_id) {
        this.headImg_id = headImg_id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getNoteName() {
        return NoteName;
    }

    public void setNoteName(String noteName) {
        NoteName = noteName;
    }

    public int getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(int notice_time) {
        this.notice_time = notice_time;
    }
}
