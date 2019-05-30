package com.example.shareplatform.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.R;

import java.util.ArrayList;

/**
 * 文件夹中的文章展示
 */
public class Article_ListAdapter extends RecyclerView.Adapter<Article_ListAdapter.ViewHolder> {
    ArrayList<Article> items;

    public interface onListener {
        void onViewClick(int ArticleID);
    }

    private onListener listener = null;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    public Article_ListAdapter(ArrayList<Article> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_folder, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Article item = items.get(i);
        viewHolder.textView.setText(item.getTitle());
        viewHolder.imageView.setImageResource(item.getImg_id());
        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.onViewClick(item.getArticleId()));

        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_list_article_img);
            textView = itemView.findViewById(R.id.item_list_article_text);
            this.itemView = itemView;
        }
    }
}

