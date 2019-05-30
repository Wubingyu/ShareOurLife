package com.example.shareplatform.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareplatform.Entity.ExploreArticle;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.ItemTouchHelperAdapter;

import java.util.ArrayList;

/**
 * 探索界面
 */
public class Explore_ListAdapter extends RecyclerView.Adapter<Explore_ListAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    ArrayList<ExploreArticle> items;


    public interface onListener {
        void clickItem(int position);
    }

    onListener listener = null;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public Explore_ListAdapter(ArrayList<ExploreArticle> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Explore_ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_explore, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Explore_ListAdapter.ViewHolder viewHolder, int i) {
        ExploreArticle item = items.get(i);
        viewHolder.imageView.setImageResource(item.getImg_id());
        viewHolder.titleView.setText(item.getTitle());
        viewHolder.briefView.setText(item.getBrief());

        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.clickItem(i));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView, briefView;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ExploreItem_img);
            titleView = itemView.findViewById(R.id.ExploreItem_Title);
            briefView = itemView.findViewById(R.id.ExploreItem_brief);
            this.itemView = itemView;
        }
    }

    @Override
    public void onSwipe(int position) {
        items.remove(0);
        notifyDataSetChanged();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {

    }
}
