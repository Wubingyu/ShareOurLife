package com.example.shareplatform.Fragment;


import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.icu.util.ValueIterator;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.MainActivity;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.InstanceEntityHelper;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleEditFragment extends Fragment {
    public final int CHOOSE_PHOTO = 1;
    private static final String FOLDER_ID = "folderID";
    int Folder_ID;
    ImageView imageView;
    EditText titleView, contextView;
    FloatingActionButton floatingActionButton;
    String imagePath;


    public ArticleEditFragment() {
        // Required empty public constructor
    }

    public static ArticleEditFragment newInstance(int FolderID) {

        Bundle args = new Bundle();
        args.putInt(FOLDER_ID, FolderID);
        ArticleEditFragment fragment = new ArticleEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Folder_ID = getArguments().getInt(FOLDER_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_edit, container, false);
        imageView = view.findViewById(R.id.ArticleEdit_img);
        titleView = view.findViewById(R.id.ArticleEdit_title);
        contextView = view.findViewById(R.id.ArticleEdit_context);
        floatingActionButton = view.findViewById(R.id.ArticleEdit_finish);


        imageView.setOnClickListener(v -> openAlbum());
        floatingActionButton.setOnClickListener(v -> {
            if (String.valueOf(titleView.getText()) != null && String.valueOf(contextView.getText()) != null && imagePath != null) {
                ((MainActivity) getActivity()).notifyAddArticle(Folder_ID, String.valueOf(titleView.getText()), String.valueOf(contextView.getText()), imagePath);
            }else {
                Toast.makeText(getContext(), "请输入文章内容", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    handleImageOnKitKat(data);
                }
        }
    }

    private void handleImageOnKitKat(Intent data) {
        Uri uri = data.getData();
        imagePath = getTruePath(uri);
        displayImage(imagePath); // 根据图片路径显示图片

    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
        }
    }

    private String getTruePath(Uri uri) {
        if (DocumentsContract.isDocumentUri(getActivity(), uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                return getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                return getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            return getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            return uri.getPath();
        }

        return null;
    }

    //内容提供器，取得图片。
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

}
