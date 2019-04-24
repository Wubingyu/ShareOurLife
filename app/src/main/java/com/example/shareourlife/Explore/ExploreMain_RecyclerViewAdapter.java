package com.example.shareourlife.Explore;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shareourlife.R;

import java.util.ArrayList;

public class ExploreMain_RecyclerViewAdapter  extends RecyclerView.Adapter<ExploreMain_RecyclerViewAdapter.ExploreMain_ViewHolder>  {
    ArrayList<ExploreMain_RecyclerViewItem> items;

    interface onListener {
        void clickCard();
        void clickUser();
    }

    onListener listener = null;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public ExploreMain_RecyclerViewAdapter(ArrayList<ExploreMain_RecyclerViewItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ExploreMain_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_explore_main, viewGroup, false);
        ExploreMain_ViewHolder viewHolder = new ExploreMain_ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreMain_ViewHolder viewHolder, int i) {
        ExploreMain_RecyclerViewItem item = items.get(i);
        viewHolder.imageView.setImageResource(item.getImg_id());
        viewHolder.titleView.setText(item.getTitle());
        viewHolder.briefView.setText(item.getBrief());
        viewHolder.dateView.setText(item.getDate());
        viewHolder.userImgView.setImageResource(item.getUser_img_id());
        viewHolder.userNameView.setText(item.getUser_name());

        if (listener != null) {
            viewHolder.cardView.setOnClickListener(v -> listener.clickCard());
            viewHolder.userLayout.setOnClickListener(v -> listener.clickUser());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ExploreMain_ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        LinearLayout userLayout;
        ImageView imageView, userImgView;
        TextView titleView, briefView, dateView,userNameView;

        public ExploreMain_ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.recycler_item_explore_CardView);
            imageView = itemView.findViewById(R.id.recycler_item_explore_Img);
            titleView = itemView.findViewById(R.id.recycler_item_explore_Title);
            briefView = itemView.findViewById(R.id.recycler_item_explore_brief);
            dateView = itemView.findViewById(R.id.recycler_item_explore_date);

            userLayout = itemView.findViewById(R.id.recycler_item_explore_userLayout);
            userImgView = itemView.findViewById(R.id.recycler_item_explore_userImg);
            userNameView = itemView.findViewById(R.id.recycler_item_explore_userName);
        }
    }
}
