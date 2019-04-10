package com.example.shareourlife.myProfile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareourlife.Entity.Following;
import com.example.shareourlife.R;
import java.util.ArrayList;

public class FollowingList_RecyclerViewAdapter extends RecyclerView.Adapter<FollowingList_RecyclerViewAdapter.ViewHolder> {
    private ArrayList<FollowingList_RecyclerView_Item> items = new ArrayList<>();
    private Context context;

    /**
     * 响应事件.在这里用interface定义需要的响应事件，在activity中把它实现后，用一个set方法设置。
     */
    public interface OnClickListener {
        //item设置，传入item 设置特别关注、移动分组、消息提醒设置、设置备注名
        void OnSettingClick(int position, FollowingList_RecyclerView_Item item);

        //取消关注，传入其在followingList中的位置，完成移除
        void OnFollowButtonClick(int position);
    }

    private FollowingList_RecyclerViewAdapter.OnClickListener mOnClickListener;

    public void setOnClickListener(FollowingList_RecyclerViewAdapter.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
    /**
     * 初始化列表
     */
    public FollowingList_RecyclerViewAdapter(Context context) {
        this.context = context;
        init();
    }


    /**
     * 获得ViewHolder，提供view。
     * 想来提供不同的布局，也是在这里了吧。获得不同的viewHolder，并为每个ViewHolder提供不同的view
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_following, viewGroup, false);
        final FollowingList_RecyclerViewAdapter.ViewHolder viewHolder = new FollowingList_RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FollowingList_RecyclerView_Item item = items.get(i);

        //设置为圆形头像
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), id2Bitmap(item.getHeadImg_id()));
        circleDrawable.setCircular(true);
        viewHolder.Following_headImg.setImageDrawable(circleDrawable);

        //设置名称
        if (!item.getNoteName().isEmpty()) {
            viewHolder.Following_Name.setText(item.getNoteName());
        } else if (!item.getUser_Name().isEmpty()) {
            viewHolder.Following_Name.setText(item.getUser_Name());
        }

        if (mOnClickListener != null) {
            viewHolder.Following_button.setOnClickListener(v -> mOnClickListener.OnFollowButtonClick(i));
            viewHolder.Following_moreSetting.setOnClickListener(v -> mOnClickListener.OnSettingClick(i, item));
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Following_headImg, Following_moreSetting;
        TextView Following_Name;
        MaterialButton Following_button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Following_moreSetting = itemView.findViewById(R.id.Following_moreSetting);
            Following_headImg = itemView.findViewById(R.id.Following_headImg);
            Following_Name = itemView.findViewById(R.id.Following_Name);
            Following_button = itemView.findViewById(R.id.Following_button);
        }
    }


    private void init() {
        FollowingList_RecyclerView_Item item1 = new FollowingList_RecyclerView_Item(R.drawable.x_man1, "罗根·豪利特", "金刚狼", 1);
        FollowingList_RecyclerView_Item item2 = new FollowingList_RecyclerView_Item(R.drawable.x_man2, "埃里克·兰谢尔", "万磁王", 7);
        FollowingList_RecyclerView_Item item3 = new FollowingList_RecyclerView_Item(R.drawable.x_man3, "奥罗罗·门罗", "暴风女", 1);
        for (int i = 0; i < 5; i++) {
            items.add(item1);
            items.add(item2);
            items.add(item3);
        }

    }

    private Bitmap id2Bitmap(int id) {
        return BitmapFactory.decodeResource(context.getResources(),id);
    }
}
