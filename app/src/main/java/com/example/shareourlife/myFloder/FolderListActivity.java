package com.example.shareourlife.myFloder;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.shareourlife.R;
import com.example.shareourlife.myProfile.Introduce_Fragment;
import com.example.shareourlife.myProfile.Introduce_ShadowTransformer;

import java.util.ArrayList;

import static com.example.shareourlife.myProfile.ProfileActivity.dpToPixels;

public class FolderListActivity extends AppCompatActivity {
    private static final String TAG = "FolderListActivity";

     public static boolean FolderListShowAsBooks = true;

    Toolbar toolbar;
    BottomAppBar bottomAppBar;
    ViewPager viewPager;
    Introduce_ShadowTransformer transformer;
    FolderList_ViewPageAdapter viewPagerAdapter;
    ArrayList<Folder_Fragment> fragments = new ArrayList<>();


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        toolbar = findViewById(R.id.FolderList_Toolbar);
        bottomAppBar = findViewById(R.id.FolderList_BottomAppBar);

//        toolbar设置
        toolbar.setNavigationOnClickListener(v -> finish()); //设置导航栏响应事件
        toolbar.inflateMenu(R.menu.folder_list_toolbar_menu); // 绑定菜单栏
        toolbar.setOnMenuItemClickListener(menuItem -> {            //设置响应事件
            if (menuItem.getItemId() == R.id.FolderList_Toolbar_menu_search) {  //搜索
                Toast.makeText(FolderListActivity.this, "Search", Toast.LENGTH_SHORT).show();
            }
            if (menuItem.getItemId() == R.id.FolderList_Toolbar_menu_showMode) {  //显示方式
                if (FolderListShowAsBooks) {
                    menuItem.setIcon(getDrawable(R.drawable.ic_list));
                    FolderListShowAsBooks = false;
                } else {
                    menuItem.setIcon(getDrawable(R.drawable.ic_books));
                    FolderListShowAsBooks = true;
                }
            }
            return false;
        });

        //初始化viewPager （之后可以考虑开源项目：CardSlider 、 Expanding Coll...
        init_fragments();
        viewPager = findViewById(R.id.FolderList_ViewPager);
        viewPagerAdapter = new FolderList_ViewPageAdapter(getSupportFragmentManager(), fragments,  dpToPixels(2, this));
        viewPager.setAdapter(viewPagerAdapter);
        


        //设置底部bottomAppBar
        bottomAppBar.inflateMenu(R.menu.folder_list_bottom_appbar_menu);
        ActionMenuItemView item = findViewById(R.id.FolderList_BottomAppBar_menu_title);
        item.setTitle("新标题~~");
        bottomAppBar.setOnMenuItemClickListener(menuItem -> {// 设置菜单栏 响应事件,这里的
            if (menuItem.getItemId() == R.id.FolderList_BottomAppBar_menu_title) {
                Toast.makeText(FolderListActivity.this, "FolderList_BottomAppBar_menu_title click", Toast.LENGTH_SHORT).show();
            }
            if (menuItem.getItemId() == R.id.FolderList_BottomAppBar_menu_edit) {
                Toast.makeText(FolderListActivity.this, "FolderList_BottomAppBar_menu_edit click", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }

    private void init_fragments() {
        Folder_Fragment fragment1 = Folder_Fragment.newInstance("新垣结衣", getResources().getString(R.string.gakki), getDrawable(R.drawable.gakki));
        Folder_Fragment fragment2 = Folder_Fragment.newInstance("长泽雅美", getResources().getString(R.string.masami), getDrawable(R.drawable.masami));
        Folder_Fragment fragment3 = Folder_Fragment.newInstance("佐仓绫音", getResources().getString(R.string.sakura_ayane), getDrawable(R.drawable.sakura_ayane));
        Folder_Fragment fragment4 = Folder_Fragment.newInstance("わたがし", getResources().getString(R.string.summer_night), getDrawable(R.drawable.summer_night));

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);

    }


    /**
     * 这个到底是怎么用的呢？
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
/*        bottomAppBar.inflateMenu(R.menu.folder_list_bottom_appbar_menu);
//        getMenuInflater().inflate(R.menu.folder_list_bottom_appbar_menu,menu);

        Menu mMenu=menu;

        MenuItem item=mMenu.findItem(R.id.FolderList_BottomAppBar_menu_title);
        menuItem.setTitle("动态设置标题~");*/
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //动画效果 || 怎么做到的呢？
        transformer = new Introduce_ShadowTransformer(viewPager, viewPagerAdapter);
        transformer.enableScaling(true);
        viewPager.setPageTransformer(false, transformer);

    }
}
