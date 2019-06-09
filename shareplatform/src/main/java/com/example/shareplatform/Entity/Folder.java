package com.example.shareplatform.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文件夹的实体类
 */
public class Folder implements Parcelable {
    int FolderID;
    int userID;

    int img_id;
    String img_path;
    String title;

    public Folder(int folderID, int userID, int img_id, String title) {
        this(folderID, userID, img_id, null, title);
    }

    public Folder(int folderID, int userID, String imgPath, String title) {
        this(folderID, userID, 0, imgPath, title);
    }

    public Folder(int folderID, int userID, int img_id, String img_path, String title) {
        FolderID = folderID;
        this.userID = userID;
        this.img_id = img_id;
        this.img_path = img_path;
        this.title = title;
    }

    protected Folder(Parcel in) {
        FolderID = in.readInt();
        userID = in.readInt();
        img_id = in.readInt();
        img_path = in.readString();
        title = in.readString();
    }

    public static final Creator<Folder> CREATOR = new Creator<Folder>() {
        @Override
        public Folder createFromParcel(Parcel in) {
            return new Folder(in);
        }

        @Override
        public Folder[] newArray(int size) {
            return new Folder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(FolderID);
        dest.writeInt(userID);
        dest.writeInt(img_id);
        dest.writeString(img_path);
        dest.writeString(title);
    }

    public int getFolderID() {
        return FolderID;
    }

    public void setFolderID(int folderID) {
        FolderID = folderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}