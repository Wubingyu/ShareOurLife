package com.example.shareplatform.utils;

import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.Entity.Folder;
import com.example.shareplatform.Entity.Message;
import com.example.shareplatform.Entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class InstanceEntityHelper {
    private static int USER_ID = 0;
    private static int ARTICLE_ID = 0;
    private static int FOLDER_ID = 0;
    private static int Message_ID = 0;

    private static User user;
    private static Article article;
    private static Folder folder;
    private static Message message;

    private static ArrayList<Folder> folders = new ArrayList<>();
    private static ArrayList<Article> articles = new ArrayList<>();

    public static User newUser(String name, String password, String email, String tel, int headImg_id) {
        user = new User(setUserID(USER_ID), name, password, email, tel, headImg_id);
        return user;
    }

    public static Folder newFolder(int userID, int img_id, String title) {
        folder = new Folder(setFolderID(FOLDER_ID), userID, img_id, title);
        folders.add(folder);
        return folder;
    }

    public static Article newArticleByRid(int userId, int folderId, int img_id, String title, String context) {
        article = new Article(setArticleID(ARTICLE_ID), userId, folderId, img_id, title, context);
        articles.add(article);
        return article;
    }

    public static Article newArticleByPath(int userID, int folderID, String title, String context, String path) {
        article = new Article(setArticleID(ARTICLE_ID), userID, folderID, title, context, path);
        articles.add(article);
        return article;
    }

    public static ArrayList<Article> getArticleByFolderID(int folderID) {
        ArrayList<Article> FolderArticles = new ArrayList<>();
        for (Article article : articles) {
            if (article.getFolderId() == folderID) {
                FolderArticles.add(article);
            }
        }
        return FolderArticles;
    }

    public static ArrayList<Folder> getFolders(int userID) {
        ArrayList<Folder> UserFolders = new ArrayList<>();
        for (Folder folder : folders) {
            if (folder.getUserID() == userID) {
                UserFolders.add(folder);
            }
        }
        return UserFolders;
    }

    public static Article getArticleByID(int ArticleID){
        for (Article article : articles) {
            if (article.getArticleId() == ArticleID) {
                return article;
            }
        }
        return null;
    }

    public static Folder getFolderByID(int FolderID){
        for (Folder folder : folders) {
            if (folder.getFolderID() == FolderID) {
                return folder;
            }
        }
        return null;
    }

    public static void removeArticleByID(int ArticleID){
        for (Article article : articles) {
            if (article.getArticleId() == ArticleID) {
                articles.remove(article);
                return;
            }
        }
    }

    private static int setArticleID(int articleId) {
        ARTICLE_ID++;
        return ARTICLE_ID;
    }

    private static int setFolderID(int folderId) {
        FOLDER_ID++;
        return FOLDER_ID;
    }

    private static int setUserID(int userId) {
        USER_ID++;
        return USER_ID;
    }

}
