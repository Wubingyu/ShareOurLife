package com.example.shareplatform.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 探索界面的实体类
 */
public class ExploreArticle implements Parcelable {
    String title;
    String brief;
    int img_id;

    int head_img;
    String user_name;
    String time;

    public ExploreArticle(String title, String brief, int img_id, int head_img, String user_name, String time) {
        this.title = title;
        this.brief = brief;
        this.img_id = img_id;
        this.head_img = head_img;
        this.user_name = user_name;
        this.time = time;
    }

    protected ExploreArticle(Parcel in) {
        title = in.readString();
        brief = in.readString();
        img_id = in.readInt();
        head_img = in.readInt();
        user_name = in.readString();
        time = in.readString();
    }

    public static final Creator<ExploreArticle> CREATOR = new Creator<ExploreArticle>() {
        @Override
        public ExploreArticle createFromParcel(Parcel in) {
            return new ExploreArticle(in);
        }

        @Override
        public ExploreArticle[] newArray(int size) {
            return new ExploreArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(brief);
        dest.writeInt(img_id);
        dest.writeInt(head_img);
        dest.writeString(user_name);
        dest.writeString(time);
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

    public int getHead_img() {
        return head_img;
    }

    public void setHead_img(int head_img) {
        this.head_img = head_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
