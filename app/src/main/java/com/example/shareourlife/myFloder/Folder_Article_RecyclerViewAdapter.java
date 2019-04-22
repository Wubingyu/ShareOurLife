package com.example.shareourlife.myFloder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shareourlife.R;

import java.util.ArrayList;

public class Folder_Article_RecyclerViewAdapter extends RecyclerView.Adapter<Folder_Article_RecyclerViewAdapter.Folder_Article_ViewHolder> {
    ArrayList<Folder_Article_RecyclerViewItem> items = new ArrayList<>();

    public Folder_Article_RecyclerViewAdapter(ArrayList<Folder_Article_RecyclerViewItem> items) {
        this.items = items;
    }

    onListener listener = null;
    public interface onListener {
        void onItemClick(int article_group_id, int article_id);

        void onImgClick(int id);
    }

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Folder_Article_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_folder_article_list, viewGroup, false);
        Folder_Article_ViewHolder viewHolder = new Folder_Article_ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Folder_Article_ViewHolder viewHolder, int i) {
        Folder_Article_RecyclerViewItem item = items.get(i);
        viewHolder.briefView.setText(item.getBrief());
        viewHolder.titleView.setText(item.getTitle());
        viewHolder.imageView.setImageResource(item.getImg_id());

        if (listener != null) {
            viewHolder.textLayout.setOnClickListener(v -> listener.onItemClick(0, 0));
            viewHolder.imageView.setOnClickListener(v -> listener.onImgClick(item.getImg_id()));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Folder_Article_ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleView, briefView;
        LinearLayout textLayout;

        public Folder_Article_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_item_article_list_img);
            titleView = itemView.findViewById(R.id.recycler_item_article_list_title);
            briefView = itemView.findViewById(R.id.recycler_item_article_list_brief);
            textLayout = itemView.findViewById(R.id.recycler_item_article_list_textLayout);
        }
    }
}
