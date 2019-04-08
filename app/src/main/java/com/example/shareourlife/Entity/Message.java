package com.example.shareourlife.Entity;

import android.graphics.Bitmap;

//用户间的私信
public class Message {
    int from_user;
    int to_user;
    boolean isRead;     //对方是否已读，只看to_user
    boolean show_isRead;//to_user是否让别人知道他的已读
    msg_TypeAndContext context;

    class msg_TypeAndContext {
        boolean is_pic=false;
        boolean is_String=true;
        boolean is_emoji=true;

        Bitmap pic;
        String context;

        public msg_TypeAndContext(boolean is_pic, boolean is_String, boolean is_emoji) {
            this.is_pic = is_pic;
            this.is_String = is_String;
            this.is_emoji = is_emoji;
        }
    }


}
