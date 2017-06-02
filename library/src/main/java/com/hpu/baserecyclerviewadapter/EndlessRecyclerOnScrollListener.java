package com.hpu.baserecyclerviewadapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * Created by Administrator on 2017/6/2.
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static final int LINEAR = 0;
    public static final int GRID = 1;
    public static final int STAGGERED_GRID = 2;

    //标识RecyclerView的LayoutManager是哪种
    protected int layoutManagerType;
    // 瀑布流的最后一个的位置
    protected int[] lastPositions;
    // 最后一个的位置
    protected int lastVisibleItem;
    private int itemCount;
    private int lastItemCount = 0;

    public EndlessRecyclerOnScrollListener() {

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
//        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 >=
//                getItemCount() && pullUpListener != null) {
//            pullUpListener.onBottom(state);//回调加载更多监听
//        }

        // TODO 加载更多触发条件：
        // 1. 最后一个可见条目是总条目的最后一个位置(也就是当前条目都已经加载完成)
        // 2. 当前总条目个数大于记录的上一次总条目个数
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==
                itemCount && itemCount > lastItemCount) {
            lastItemCount = itemCount;
            Log.e("======", "lastItem=" + lastVisibleItem + ", itemCount" + itemCount);
            this.onLoadMore();//回调加载更多监听
        }
    }

    //根据不同的Layout类型处理FootView
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            layoutManagerType = LINEAR;
        } else if (layoutManager instanceof GridLayoutManager) {
            layoutManagerType = GRID;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            layoutManagerType = STAGGERED_GRID;
        } else {
            throw new RuntimeException(
                    "Unsupported LayoutManager used. Valid ones are " +
                            "LinearLayoutManager, GridLayoutManager and " +
                            "StaggeredGridLayoutManager");
        }

        switch (layoutManagerType) {
            case LINEAR:
                lastVisibleItem = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItem = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager) layoutManager;
                lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItem = findMax(lastPositions);
                break;
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




//    private int previousTotal = 0;
//    private boolean loading = true;
//    int firstVisibleItem, visibleItemCount, totalItemCount;
//
//    private int currentPage = 1;
//
//    private LinearLayoutManager mLinearLayoutManager;
//
//    public EndlessRecyclerOnScrollListener(
//            LinearLayoutManager linearLayoutManager) {
//        this.mLinearLayoutManager = linearLayoutManager;
//    }
//
//    @Override
//    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//        super.onScrolled(recyclerView, dx, dy);
//
//        visibleItemCount = recyclerView.getChildCount();
//        totalItemCount = mLinearLayoutManager.getItemCount();
//        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
//
//        if (loading) {
//            if (totalItemCount > previousTotal) {
//                loading = false;
//                previousTotal = totalItemCount;
//            }
//        }
//        if (!loading
//                && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
//            currentPage++;
//            onLoadMore(currentPage);
//            loading = true;
//        }
//    }
//
//    public abstract void onLoadMore(int currentPage);

}
