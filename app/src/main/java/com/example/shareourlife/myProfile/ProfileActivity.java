package com.example.shareourlife.myProfile;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.shareourlife.BaseActivity;
import com.example.shareourlife.R;

import java.util.ArrayList;

public class ProfileActivity extends BaseActivity {
    private static final int EDIT_INFO = 1;
    private static final String TAG = "ProfileActivity";
    Toolbar toolbar;
    //    ImageView imageView;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    ProfileAdapter adapter;
    ViewPager viewPager;

    Introduce_ViewPagerAdapter introduce_viewPagerAdapter;

    Introduce_ShadowTransformer transformer;

    //introduceCards
    ArrayList<Introduce_Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_Design_NoActionBar);
        }
//        setTheme(R.style.Theme_Design_NoActionBar);
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.toolbar_profile);
//            imageView = findViewById(R.id.userHead_profile);
        appBarLayout = findViewById(R.id.appBar_profile);
        collapsingToolbarLayout = findViewById(R.id.CollapsingToolbarLayout_profile);
        linearLayout = findViewById(R.id.LinearLayout_Toolbar);
        recyclerView = findViewById(R.id.RecyclerView_profile);
        viewPager = findViewById(R.id.profile_ViewPager);

        //navigation的设置
        toolbar.setNavigationOnClickListener(v -> finish());

        //菜单栏的设置
//            toolbar.inflateMenu(R.menu.profile_appsetting);
//            toolbar.setOnMenuItemClickListener(item -> {
//                Log.d(TAG, "click setting");
//                return false;
//            });

        //滑动设置
        // collapsingToolbarLayout.setTitle("AppbarLayout"); 这一行没有用？也不知道为什么
        appBarLayout.addOnOffsetChangedListener((appbarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) >= appbarLayout.getTotalScrollRange()) {
                linearLayout.setVisibility(View.INVISIBLE);
                collapsingToolbarLayout.setTitle("AppbarLayout");
            } else {
                linearLayout.setVisibility(View.VISIBLE);
                collapsingToolbarLayout.setTitle("");
            }
        });

        //recyclerView的设置
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ProfileAdapter();
        recyclerView.setAdapter(adapter);
        //recyclerView的响应事件的设置.通过adapter中的set方法设置
        adapter.setOnClickListener(new ProfileAdapter.OnClickListener() {
            @Override
            public void onItemClick(int respondID) {
                switch (respondID) {
                    case 0:
                        ToastMessage("编辑个人资料");
                        Intent intent_editInfo = new Intent(ProfileActivity.this, PersonInfoActivity.class);
                        startActivityForResult(intent_editInfo, EDIT_INFO);
                        break;
                    case 1:
                        ToastMessage("消息");
                        Intent intent_message = new Intent(ProfileActivity.this, MessageActivity.class);
                        startActivity(intent_message);
                        break;
                    case 2:
                        ToastMessage("分享本软件");
                        break;
                }
            }

            @Override
            public void onSwitchChanged(int respondID, Boolean isChecked) {
                switch (respondID) {
                    case 3:
                        if (isChecked) {
                            ToastMessage("Switch On");
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            ToastMessage("Switch Off");
                        }
                        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });

        //将介绍卡片的fragments进行初始化
        initIntroduceCards();
        introduce_viewPagerAdapter = new Introduce_ViewPagerAdapter(getSupportFragmentManager(), fragments,
                dpToPixels(2, this));
        viewPager = findViewById(R.id.profile_ViewPager);
        viewPager.setAdapter(introduce_viewPagerAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //动画效果 || 怎么做到的呢？
        transformer = new Introduce_ShadowTransformer(viewPager, introduce_viewPagerAdapter);
        transformer.enableScaling(true);
        viewPager.setPageTransformer(false, transformer);

    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new ProfileViewModel(getNavigator());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case EDIT_INFO:
                    //获取修改的个人信息
                    break;
            }
        }
    }

    private void initIntroduceCards() {
        Introduce_Fragment fragment1 = Introduce_Fragment.newInstance("兴趣", getResources().getString(R.string.interest));
        Introduce_Fragment fragment2 = Introduce_Fragment.newInstance("读书", getResources().getString(R.string.book));
        Introduce_Fragment fragment3 = Introduce_Fragment.newInstance("动画", getResources().getString(R.string.cartoon));
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
    }

    /**
     * 它返回的是什么？
     * @param dp
     * @param context
     * @return
     */
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
