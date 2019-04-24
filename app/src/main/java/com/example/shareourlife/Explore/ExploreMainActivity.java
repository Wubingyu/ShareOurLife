package com.example.shareourlife.Explore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.shareourlife.R;

import java.util.ArrayList;

import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class ExploreMainActivity extends AppCompatActivity {
    RecyclerView cardSwipeView;
    ExploreMain_RecyclerViewAdapter adapter;
    ArrayList<ExploreMain_RecyclerViewItem> items = new ArrayList<>();

    //一叠卡片滑动，刷新一次五张，一次刷新中不喜欢的会放到垃圾桶里面。垃圾桶只存这次刷新的内容。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_main);

        cardSwipeView = findViewById(R.id.explore_recycler_cardSwipe);

        initItems();
        adapter = new ExploreMain_RecyclerViewAdapter(items);
        cardSwipeView.setAdapter(adapter);

        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(cardSwipeView.getAdapter(), items);
        ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback); CardLayoutManager cardLayoutManager = new CardLayoutManager(cardSwipeView, touchHelper);
        cardSwipeView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(cardSwipeView);
        cardCallback.setOnSwipedListener(new OnSwipeListener<ExploreMain_RecyclerViewItem>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                /**
                 * will callback when the card are swiping by user
                 * viewHolder : thee viewHolder of swiping card
                 * ratio : the ratio of swiping , you can add some animation by the ratio
                 * direction : CardConfig.SWIPING_LEFT means swiping from left；CardConfig.SWIPING_RIGHT means swiping from right
                 *             CardConfig.SWIPING_NONE means not left nor right
                 */
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, ExploreMain_RecyclerViewItem exploreMain_recyclerViewItem, int direction) {
                /**
                 *  will callback when the card swiped from screen by user
                 *  you can also clean animation from the itemview of viewHolder in this method
                 *  viewHolder : the viewHolder of swiped cards
                 *  t : the data of swiped cards from dataList
                 *  direction : CardConfig.SWIPED_LEFT means swiped from left；CardConfig.SWIPED_RIGHT means swiped from right
                 */

            }

            @Override
            public void onSwipedClear() {
                /**
                 *  will callback when all cards swiped clear
                 *  you can load more data
                 */
            }

        });


        adapter.setListener(new ExploreMain_RecyclerViewAdapter.onListener() {
            @Override
            public void clickCard() {
                Toast.makeText(ExploreMainActivity.this, "click card", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickUser() {
                Toast.makeText(ExploreMainActivity.this, "click user", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initItems() {
        ExploreMain_RecyclerViewItem item1 = new ExploreMain_RecyclerViewItem(R.drawable.summer_night, "夏祭", "花火浴衣，长泽雅美", "2019-4-22", R.drawable.back_number, "back_number");
        ExploreMain_RecyclerViewItem item2 = new ExploreMain_RecyclerViewItem(R.drawable.img2, "Bohemian Rhapsody", "Queen, Live, Wembley Stadium, July 1986", "2019-3-20", R.drawable.img3, "Queen");
        ExploreMain_RecyclerViewItem item3 = new ExploreMain_RecyclerViewItem(R.drawable.sakura_ayane, "佐仓绫音", getResources().getString(R.string.sakura_ayane),"2019-1-30",R.drawable.sakura,"佐仓绫音");
        items.add(item1);
        items.add(item2);
        items.add(item3);
    }
}
