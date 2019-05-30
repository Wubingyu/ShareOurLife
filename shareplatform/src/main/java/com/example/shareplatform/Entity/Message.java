package com.example.shareplatform.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 联系人的实体类
 */
public class Message implements Parcelable {
    int userID;
    int targetID;

    String MessageContext;
    String MessageTime;

    public Message(int userID, int targetID, String messageContext, String messageTime) {
        this.userID = userID;
        this.targetID = targetID;
        MessageContext = messageContext;
        MessageTime = messageTime;
    }

    protected Message(Parcel in) {
        userID = in.readInt();
        targetID = in.readInt();
        MessageContext = in.readString();
        MessageTime = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeInt(targetID);
        dest.writeString(MessageContext);
        dest.writeString(MessageTime);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTargetID() {
        return targetID;
    }

    public void setTargetID(int targetID) {
        this.targetID = targetID;
    }

    public String getMessageContext() {
        return MessageContext;
    }

    public void setMessageContext(String messageContext) {
        MessageContext = messageContext;
    }

    public String getMessageTime() {
        return MessageTime;
    }

    public void setMessageTime(String messageTime) {
        MessageTime = messageTime;
    }
}
