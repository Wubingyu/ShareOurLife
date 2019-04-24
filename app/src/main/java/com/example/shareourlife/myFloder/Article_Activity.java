package com.example.shareourlife.myFloder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareourlife.R;

import static com.example.shareourlife.myFloder.Folder_Activity.CONTEXT;
import static com.example.shareourlife.myFloder.Folder_Activity.IMG_ID;
import static com.example.shareourlife.myFloder.Folder_Activity.TITLE;

public class Article_Activity extends AppCompatActivity {

    ImageView imageView;
    TextView titleView, contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        imageView = findViewById(R.id.article_img);
        titleView = findViewById(R.id.article_title);
        contextView = findViewById(R.id.article_context);

        Intent intent = getIntent();
        int img_id = intent.getIntExtra(IMG_ID, R.drawable.img2);
        String title = intent.getStringExtra(TITLE);
        String context = intent.getStringExtra(CONTEXT);

        imageView.setImageResource(img_id);
        titleView.setText(title);
        contextView.setText(context);

        //Navigation怎么做：
        //学习平行世界，自定义behavior，下滑到底出现菜单，上滑出现菜单
    }
}
