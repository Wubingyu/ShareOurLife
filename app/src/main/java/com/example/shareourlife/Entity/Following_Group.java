package com.example.shareourlife.Entity;

import com.example.shareourlife.Entity.utils.Permission;

import java.util.List;

public class Following_Group {
    final int Following_Group_id;
    final int User_id;

    List<Integer> FollowingList;  //这个分组中，你关注的所有人
    Permission permission; //这个分组是不是对他人可见的

    public Following_Group(int following_group_id, int user_id) {
        Following_Group_id = following_group_id;
        User_id = user_id;
    }
}
