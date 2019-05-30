package com.example.shareplatform.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareplatform.Entity.Article;
import com.example.shareplatform.Entity.Folder;
import com.example.shareplatform.MainActivity;
import com.example.shareplatform.R;
import com.example.shareplatform.utils.FolderListItemTouchHelperCallback;
import com.example.shareplatform.utils.InstanceEntityHelper;

import java.util.ArrayList;

/**
 * 文件夹list
 */
public class Folder_ListAdapter extends RecyclerView.Adapter<Folder_ListAdapter.ViewHolder> {
    ArrayList<Folder> items; //null并没有请求内存空间，所以下面的card_items不能null，因为它要add。但是items是在构造函数中传过来。
    Context context;

    public interface OnClickListener {
        void onCardClick(int position, ImageView imageView, int folderID);

        void onArticleClick(int ArticleID);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public Folder_ListAdapter(ArrayList<Folder> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.folder, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
/*
        recyclerView = view.findViewById(R.id.card_item_folder_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);*/


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Folder item = items.get(i);
        viewHolder.textView.setText(item.getTitle());
        viewHolder.imageView.setImageResource(item.getImg_id());


        ArrayList<Article> card_items = InstanceEntityHelper.getArticleByFolderID(item.getFolderID());

        CardArticle_ListAdapter adapter;
        adapter = new CardArticle_ListAdapter(card_items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView.setAdapter(adapter);
        viewHolder.recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        viewHolder.imageView.setTransitionName("transition" + i);


        ItemTouchHelper.Callback callback = new FolderListItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(viewHolder.recyclerView);

        //设置点击事件
        if (listener != null) {
            viewHolder.cardView.setOnClickListener(v -> listener.onCardClick(i, viewHolder.imageView, item.getFolderID()));
            adapter.setListener(ArticleID -> listener.onArticleClick(ArticleID));

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;
        RecyclerView recyclerView;
        int[] articles_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_item_folder_card);
            textView = itemView.findViewById(R.id.card_item_folder_text);
            imageView = itemView.findViewById(R.id.card_item_folder_img);
            recyclerView = itemView.findViewById(R.id.card_item_folder_RecyclerView);
        }
    }

}
