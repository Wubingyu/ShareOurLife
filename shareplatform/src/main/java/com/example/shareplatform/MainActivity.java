package com.example.shareplatform;

import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;

import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.Entity.ExploreArticle;
import com.example.shareplatform.Entity.Folder;
import com.example.shareplatform.Entity.Message;
import com.example.shareplatform.Fragment.ArticleShowFragment;
import com.example.shareplatform.Fragment.ExploreFragment;
import com.example.shareplatform.Fragment.FolderListFragment;
import com.example.shareplatform.Fragment.MessageFragment;
import com.example.shareplatform.utils.InstanceEntityHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragments = new ArrayList<>();

    int this_id = 0;
    int last_id = 0;

    int userID = 1;


    /**
     * 在这里先全部初始化完成，之后的Fragment都是调用这里
     */
    ArrayList<Message> messageList = new ArrayList<>();
    ArrayList<ExploreArticle> exploreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initItems();
        initFragment();
        initView();
    }


    private void initItems() {
        initFolders();
        initMessage();
        initExplore();
        initArticles();

    }


    private void initFragment() {
        FolderListFragment folderListFragment = FolderListFragment.newInstance(InstanceEntityHelper.getFolders(userID));

        MessageFragment messageFragment = MessageFragment.newInstance(messageList);
        ExploreFragment exploreFragment = ExploreFragment.newInstance(exploreList);

        fragments.add(folderListFragment);
        fragments.add(messageFragment);
        fragments.add(exploreFragment);


    }


    private void initView() {
        bottomNavigationView = findViewById(R.id.BottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> onTabItemSelected(item.getItemId()));

        onTabItemSelected(R.id.navigation_bottom_folder_list);
    }

    private boolean onTabItemSelected(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.navigation_bottom_folder_list:
                fragment = fragments.get(0);
                this_id = 0;
                break;
            case R.id.navigation_bottom_message:
                fragment = fragments.get(1);
                this_id = 1;
                break;
            case R.id.navigation_bottom_explore:
                fragment = fragments.get(2);
                this_id = 2;
                break;
            case R.id.navigation_bottom_info:
                fragment = fragments.get(3);
                this_id = 3;
                break;
        }

        //想要根据左右，数量的方式实现滑动，就判断i是变大还是变小，i之间的距离是多少。
        if (fragment != null) {
            if (this_id > last_id) {

                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_right_in,
                                R.anim.slide_left_out,
                                R.anim.slide_left_in,
                                R.anim.slide_right_out)
                        .replace(R.id.fragment_placeholder, fragment).commit();

            } else if (this_id < last_id) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_left_in,
                                R.anim.slide_right_out,
                                R.anim.slide_right_in,
                                R.anim.slide_left_out
                        )
                        .replace(R.id.fragment_placeholder, fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_placeholder, fragment).commit();
            }
            last_id = this_id;
            return true;
        }
        return false;
    }

    public void showFragmentWithTransition(Fragment current, Fragment newFragment, View sharedView, String sharedElementName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            current.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
            current.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

            newFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
            newFragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_placeholder, newFragment)
                .addSharedElement(sharedView, sharedElementName)
                .addToBackStack(null)
                .commit();

    }

    /**
     * 做类似岛读的效果
     * @param current
     * @param ArticleID
     */
    public void showArticleWithTransition(Fragment current, int ArticleID) {
        ArticleShowFragment articleFragment = ArticleShowFragment.newInstance(ArticleID);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_placeholder, articleFragment)
                .addToBackStack(null)
                .commit();
    }

    private void initExplore() {
        ExploreArticle item1 = new ExploreArticle("新垣结衣", getString(R.string.gakki), R.drawable.gakki);
        ExploreArticle item2 = new ExploreArticle("罗马", "罗马不是一天建成的", R.drawable.i);
        ExploreArticle item3 = new ExploreArticle("李健", "音乐傲骨", R.drawable.j);
        ExploreArticle item4 = new ExploreArticle("罗马", "罗马不是一天建成的", R.drawable.i);
        ExploreArticle item5 = new ExploreArticle("李健", "音乐傲骨", R.drawable.j);
        exploreList.add(item1);
        exploreList.add(item2);
        exploreList.add(item3);
        exploreList.add(item4);
        exploreList.add(item5);
    }

    private void initMessage() {
        Message item1 = new Message(1, 2, "明天一起去吃东西", "三分钟前");
        Message item2 = new Message(1, 3, "回见", "两天前");
        messageList.add(item1);
        messageList.add(item2);
    }

    private void initFolders() {

        InstanceEntityHelper.newFolder(1, R.drawable.a, "罗马建筑");
        InstanceEntityHelper.newFolder(1, R.drawable.b, "苏格兰");
    }

    private void initArticles() {

        InstanceEntityHelper.newArticle(1, 1, R.drawable.a, "伊利亚特", getString(R.string.longtext1));
        InstanceEntityHelper.newArticle(1, 1, R.drawable.b, "奥德修斯", getString(R.string.longtext2));
        InstanceEntityHelper.newArticle(1, 1, R.drawable.c, "埃涅阿斯", getString(R.string.longtext3));
        InstanceEntityHelper.newArticle(1, 1, R.drawable.d, "阿斯卡尼俄斯", getString(R.string.longtext4));

        InstanceEntityHelper.newArticle(1, 2, R.drawable.e, "东正教", "东正教");
        InstanceEntityHelper.newArticle(1, 2, R.drawable.f, "星月旗", "星月旗");
        InstanceEntityHelper.newArticle(1, 2, R.drawable.g, "十字军", "十字军");
        InstanceEntityHelper.newArticle(1, 2, R.drawable.h, "摩西十诫", "摩西十诫");

    }

}
