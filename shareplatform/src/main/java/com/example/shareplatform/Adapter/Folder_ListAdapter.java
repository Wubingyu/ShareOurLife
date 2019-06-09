package com.example.shareplatform.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.shareplatform.R;
import com.example.shareplatform.utils.FolderListItemTouchHelperCallback;
import com.example.shareplatform.utils.InstanceEntityHelper;

import java.util.ArrayList;

/**
 * 文件夹list
 */
public class Folder_ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Folder> items; //null并没有请求内存空间，所以下面的card_items不能null，因为它要add。但是items是在构造函数中传过来。
    Context context;
    CardArticle_ListAdapter adapter;
    ArrayList<Article> card_items;
    private final int ADDITEM = 2;


    public interface OnClickListener {
        void onCardClick(int position, ImageView imageView, int folderID);

        void onArticleClick(int ArticleID);

        void onAddArticle(int FolderID);

        void addFolder();
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ADDITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_folder_list, viewGroup, false);
            Add_ViewHolder add_viewHolder = new Add_ViewHolder(view);
            return add_viewHolder;
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.folder, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof Add_ViewHolder) {
            Add_ViewHolder add_viewHolder = (Add_ViewHolder) viewHolder;
            add_viewHolder.itemView.setOnClickListener(v -> listener.addFolder());
        }else {
            ViewHolder viewHolder1 = (ViewHolder) viewHolder;
            onNormalBind(viewHolder1, i);
        }
    }

    public void onNormalBind(@NonNull ViewHolder viewHolder, int i) {
        Folder item = items.get(i);
        viewHolder.textView.setText(item.getTitle());
        viewHolder.imageView.setImageResource(item.getImg_id());


        card_items = InstanceEntityHelper.getArticleByFolderID(item.getFolderID());


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
            viewHolder.showView.setOnClickListener(v -> listener.onCardClick(i, viewHolder.imageView, item.getFolderID()));
            viewHolder.addView.setOnClickListener(v -> listener.onAddArticle(item.getFolderID()));
            adapter.setListener(ArticleID -> listener.onArticleClick(ArticleID)); //这个是设置卡片内文章列表的点击事件
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, showView;
        ImageView imageView, addView;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.card_item_folder_text);
            imageView = itemView.findViewById(R.id.card_item_folder_img);
            recyclerView = itemView.findViewById(R.id.card_item_folder_RecyclerView);
            showView = itemView.findViewById(R.id.card_item_folder_SHOW);
            addView = itemView.findViewById(R.id.card_item_folder_ADD);
        }
    }

    public class Add_ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        public Add_ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }



    public void notifyArticleList() {
    }

    @Override
    public int getItemViewType(int position) {
        if (position == items.size() - 1) {
            return ADDITEM;
        } else {
            return super.getItemViewType(position);
        }
    }

}
