package com.hpu.baserecyclerviewadapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private int lastVisibleItem;
    private int itemCount;
    private int lastItemCount = 0;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==
                itemCount && itemCount > lastItemCount) {
            lastItemCount = itemCount;
            this.onLoadMore();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager
                    = (StaggeredGridLayoutManager) layoutManager;
            int[] lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
            staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
            lastVisibleItem = findMax(lastPositions);
        } else {
            lastVisibleItem = ((LinearLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public abstract void onLoadMore();

}
