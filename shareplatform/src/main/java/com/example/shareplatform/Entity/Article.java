package com.example.shareplatform.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文章的实体类
 */
public class Article implements Parcelable {
    int ArticleId;
    int UserId;
    int FolderId;

    int img_id;
    String title;
    String context;
    String img_path;

    public Article(int articleId, int userId, int folderId, int img_id, String title, String context) {
        this(articleId, userId, folderId, img_id, title, context, null);
    }

    public Article(int articleId, int userId, int folderId, String title, String context, String img_path) {
        this(articleId, userId, folderId, 0, title, context, img_path);
    }

    public Article(int articleId, int userId, int folderId, int img_id, String title, String context, String img_path) {
        ArticleId = articleId;
        UserId = userId;
        FolderId = folderId;
        this.img_id = img_id;
        this.title = title;
        this.context = context;
        this.img_path = img_path;
    }

    protected Article(Parcel in) {
        ArticleId = in.readInt();
        UserId = in.readInt();
        FolderId = in.readInt();
        img_id = in.readInt();
        title = in.readString();
        context = in.readString();
        img_path = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ArticleId);
        dest.writeInt(UserId);
        dest.writeInt(FolderId);
        dest.writeInt(img_id);
        dest.writeString(title);
        dest.writeString(context);
        dest.writeString(img_path);
    }

    public int getArticleId() {
        return ArticleId;
    }

    public void setArticleId(int articleId) {
        ArticleId = articleId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getFolderId() {
        return FolderId;
    }

    public void setFolderId(int folderId) {
        FolderId = folderId;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}