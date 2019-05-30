package com.example.shareplatform.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    int userID;
    String name;
    String password;
    String Email;
    String Tel;

    int headImg_id;

    public User(int userID, String name, String password, String email, String tel, int headImg_id) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        Email = email;
        Tel = tel;
        this.headImg_id = headImg_id;
    }

    protected User(Parcel in) {
        userID = in.readInt();
        name = in.readString();
        password = in.readString();
        Email = in.readString();
        Tel = in.readString();
        headImg_id = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(Email);
        dest.writeString(Tel);
        dest.writeInt(headImg_id);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public int getHeadImg_id() {
        return headImg_id;
    }

    public void setHeadImg_id(int headImg_id) {
        this.headImg_id = headImg_id;
    }
}
