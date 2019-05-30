package com.example.shareplatform.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareplatform.Entity.Message;
import com.example.shareplatform.R;

import java.util.ArrayList;

public class Message_ListAdapter extends RecyclerView.Adapter<Message_ListAdapter.ViewHolder> {
    ArrayList<Message> items;

    public Message_ListAdapter(ArrayList<Message> items) {
        this.items = items;
    }

    public interface onListener {
        void clickItem();
    }

    private onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Message item = items.get(i);
/*        viewHolder.headView.setImageResource(item.getImg_id());
        viewHolder.nameView.setText(item.getName());
        viewHolder.LastTimeView.setText(item.getLastTime());
        viewHolder.LastChatView.setText(item.getLast_Message());*/
        viewHolder.headView.setImageResource(R.drawable.g);
        viewHolder.nameView.setText("朋友A");
        viewHolder.LastTimeView.setText("三分钟前");
        viewHolder.LastChatView.setText("一会儿中午一起去吃饭");

        if (listener != null) {
            viewHolder.itemView.setOnClickListener(v -> listener.clickItem());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView headView;
        TextView nameView, LastChatView, LastTimeView;
        View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headView = itemView.findViewById(R.id.MessageList_item_img);
            nameView = itemView.findViewById(R.id.MessageList_item_name);
            LastChatView = itemView.findViewById(R.id.MessageList_item_lastChat);
            LastTimeView = itemView.findViewById(R.id.MessageList_item_lastTime);
            this.itemView = itemView;
        }
    }
}

