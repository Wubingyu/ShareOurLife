package com.example.shareplatform.utils;

public interface ItemTouchHelperAdapter {
    void onSwipe(int position);

    void onMove(int fromPosition, int toPosition);
}
