package com.example.shareplatform.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.shareplatform.Adapter.Explore_ListAdapter;

public class ExploreCardItemTouchHelperCallback extends ItemTouchHelper.Callback {
    ItemTouchHelperAdapter adapter;

    public ExploreCardItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//        }
        return makeMovementFlags(0, swipeFlags);
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnTouchListener(null);
        adapter.onSwipe(0);
    }
}
