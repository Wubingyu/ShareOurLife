package com.example.shareourlife.myFolder;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.shareourlife.R;

import java.util.ArrayList;

public class Folder_Activity extends AppCompatActivity {
    public static final String IMG_ID = "IMG_ID";
    public static final String TITLE = "TITLE";
    public static final String CONTEXT = "CONTEXT";

    Toolbar toolbar;
    ArrayList<Folder_Article_RecyclerViewItem> items = new ArrayList<>();
    Folder_Article_RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        //初始化toolbar
        toolbar = findViewById(R.id.FolderActivity_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        //RecyclerView
        recyclerView = findViewById(R.id.FolderActivity_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        initItems();
        adapter = new Folder_Article_RecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setListener(new Folder_Article_RecyclerViewAdapter.onListener() {

            @Override
            public void onItemClick(int article_group_id, int article_id) {
                Intent intent = new Intent(Folder_Activity.this, Article_Activity.class);
                //传递数据
                intent.putExtra(IMG_ID, R.drawable.summer_night);
                intent.putExtra(TITLE, "back number");
                intent.putExtra(CONTEXT, getResources().getString(R.string.summer_night));

                startActivity(intent);
            }

            @Override
            public void onImgClick(int id) {
                LayoutInflater inflater = LayoutInflater.from(Folder_Activity.this);

                AlertDialog dialog = new AlertDialog.Builder(Folder_Activity.this).create();
                View view = inflater.inflate(R.layout.dialog_img,null);
                dialog.setView(view);

                ImageView imageView = view.findViewById(R.id.dialog_imgView);
                imageView.setImageResource(id);

                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(0));

                dialog.show();

                imageView.setOnClickListener(v -> dialog.cancel());
/*                dialog.setCanceledOnTouchOutside(true);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.x = 0;
                layoutParams.y = 40;
                dialog.onWindowAttributesChanged(layoutParams);
                imageView.setOnClickListener(v -> dialog.dismiss());*/

            }
        });
    }

    private void initItems() {
        Folder_Article_RecyclerViewItem item1 = new Folder_Article_RecyclerViewItem("波西米亚狂想曲", "3-23日上映", R.drawable.img2);
        Folder_Article_RecyclerViewItem item2 = new Folder_Article_RecyclerViewItem("李健全国巡演", "5-1苏州站", R.drawable.li_jian);
        items.add(item1);
        items.add(item2);
    }
}
