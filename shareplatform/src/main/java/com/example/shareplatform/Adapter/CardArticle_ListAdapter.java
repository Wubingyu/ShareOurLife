package com.example.shareplatform.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 文件夹list中的文章展示
 */
public class CardArticle_ListAdapter extends RecyclerView.Adapter<CardArticle_ListAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    ArrayList<Article> items = new ArrayList<>();

    interface onListener {
        void clickCardArticle(int ArticleID);
    }

    onListener listener = null;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public CardArticle_ListAdapter(ArrayList<Article> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_folderlist, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article item = items.get(i);
        viewHolder.textView.setText(item.getTitle());

        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.clickCardArticle(item.getArticleId()));
        }

    }

    @Override
    public int getItemCount() {
        return items.size() >= 5 ? 5 : items.size();
    }

    @Override
    public void onSwipe(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        //1.数据交换 2.刷新
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.card_item_text);
            this.itemView = itemView;
        }
    }
}
