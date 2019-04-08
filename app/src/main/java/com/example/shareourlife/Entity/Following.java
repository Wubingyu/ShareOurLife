package com.example.shareourlife.Entity;

public class Following {
    final int Following_id;  //你关注的这个人的id
    final int User_id;  //你自己的id
    final int Following_Group_id;  //关注组
    String Remarks_Name;  //备注名
    Following_options options;  //关注方式

    public Following(int following_id, int user_id, int following_group_id) {
        Following_id = following_id;
        User_id = user_id;
        Following_Group_id = following_group_id;
    }


    class Following_options {
        int time_interval = 0;  //这个人你多久看一次新动态 0表示实时 1表示每天 2表示每两天 以此类推
        int Reading_number = 1; //你一次看多少条

    }
}
