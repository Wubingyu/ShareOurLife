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

    public ExploreArticle(String title, String brief, int img_id) {
        this.title = title;
        this.brief = brief;
        this.img_id = img_id;
    }

    protected ExploreArticle(Parcel in) {
        title = in.readString();
        brief = in.readString();
        img_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(brief);
        dest.writeInt(img_id);
    }

    @Override
    public int describeContents() {
        return 0;
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
