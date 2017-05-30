package com.hpu.baserecyclerviewadapter.listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;

/**
 * RecyclerView 点击事件
 * Created by lenovo on 2017/5/18.
 */

public class ItemClickListener extends RecyclerView.SimpleOnItemTouchListener {
    private GestureDetectorCompat gestureDetectorCompat;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    /**
     * RecyclerView 点击事件
     * @param recyclerView
     * @param listener
     */
    public ItemClickListener(final RecyclerView recyclerView, OnItemClickListener listener) {
        this.onItemClickListener = listener;
        gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        BaseViewHolder viewHolder = (BaseViewHolder) recyclerView.getChildViewHolder(childView);
                        if (viewHolder.getClickable() && childView != null && onItemClickListener != null) {
                            onItemClickListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
//                            clickListener.onItemClick(childView, viewHolder.getLayoutPosition());
                        }
                        return true;
                    }
                });
    }

    /**
     * RecyclerView 长按事件
     * @param recyclerView
     * @param listener
     */
    public ItemClickListener(final RecyclerView recyclerView, OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
        gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        BaseViewHolder viewHolder = (BaseViewHolder) recyclerView.getChildViewHolder(childView);
                        if (viewHolder.getClickable() && childView != null && onItemLongClickListener != null) {
                            onItemLongClickListener.onItemLongClick(childView,
                                    recyclerView.getChildAdapterPosition(childView));
//                        clickListener.onItemLongClick(childView, viewHolder.getLayoutPosition());
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }
}
