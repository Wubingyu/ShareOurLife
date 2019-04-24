package com.example.shareourlife;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Button;
import android.widget.Toast;

import com.example.shareourlife.Explore.ExploreMainActivity;
import com.example.shareourlife.myFloder.FolderListActivity;
import com.example.shareourlife.myProfile.ProfileActivity;

public class MainActivity extends BaseActivity {
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //没用。
/*        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_Design_NoActionBar);
        }*/
        super.onCreate(savedInstanceState);

//        tabLayout = findViewById(R.id.MainActivity_tabLayout);
//        initTabLayout();

        //切换方案：在FolderListActivity中 上滑出现一个类似于微信小程序的那种切换方式也挺好的
        Intent intent0 = new Intent(MainActivity.this, FolderListActivity.class);
        startActivity(intent0);

        Button button = findViewById(R.id.buttom_JumpToFolderList);
        button.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, FolderListActivity.class);
            startActivity(intent);
        });

        Button button1 = findViewById(R.id.button_JumpToExplore);
        button1.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ExploreMainActivity.class);
            startActivity(intent);
        });
    }

    private void initTabLayout() {
        TabLayout.Tab Folder_Tab = tabLayout.newTab().setText("文章").setIcon(R.drawable.ic_book_open);
        TabLayout.Tab Explore_Tab = tabLayout.newTab().setText("探索").setIcon(R.drawable.ic_explore);
        TabLayout.Tab Home_Tab = tabLayout.newTab().setText("主页").setIcon(R.drawable.ic_home);

        tabLayout.addTab(Folder_Tab);
        tabLayout.addTab(Explore_Tab);
        tabLayout.addTab(Home_Tab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, FolderListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "select explore", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent intent1 = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new MainViewModel(getNavigator());
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}