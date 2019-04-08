package com.example.shareourlife.Entity;


import android.graphics.Bitmap;

import com.example.shareourlife.Entity.utils.Permission;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//在先不适用数据库的情况下，是否需要ID呢？要的吧，不然- - 重复拿引用，会很麻烦吧？
//是否需要用Flowable？不是所有地方都需要一变动就做通知的啊。更多的时候在产生变化的时候进行通知就够了，也就是在ViewModel中进行通知就可以了。
public class User {
    final int user_id;          //id开头是 user-xxx   floder_xxx  等等
    String Username;
    String password;
    String E_mail;
    int Tel;
    Boolean Sexual;
    LocalDate Birthday;
    int age;
    LocalDate addAppDATE;
    Bitmap head_img;
    Bitmap BackGround_img;  //用户的背景图片

    List<Integer> introduceCardList;   //你的简介
    List<Integer> yourFloderList;   //你创建的收藏夹
    List<Integer> following_groupList;    //你的关注分组
    List<Integer> FollowerList;  //你的粉丝
    List<Integer> BlackList;  //黑名单

    List<Integer> starFloderList;    //你关注的收藏夹
    List<Integer> star_LableList;    //你关注的标签

    LocalTime[] getFollwingArticle = new LocalTime[2]; //什么时间接受关注区的文章通知
    LocalTime[] getMessage = new LocalTime[2];  //什么时候允许接收私信
    LocalTime[] getNotification = new LocalTime[2]; //什么时候允许接收提醒
    Boolean show_isRead;  //是否给对方发送已读

    Permission permission;  //接收哪些人的私信   关注者，粉丝，陌生人 对应设置 分别为0,1


    public User(int user_id) {
        this.user_id = user_id;
    }
}
