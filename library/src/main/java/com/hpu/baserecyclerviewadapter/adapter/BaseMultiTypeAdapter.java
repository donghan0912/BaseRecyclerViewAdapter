package com.hpu.baserecyclerviewadapter.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.DisplayItem;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.item.OnBindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BaseMultiTypeAdapter<T extends Object> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<BaseItem<T>> mData;
    @LayoutRes
    private int mDisplayLayoutRes;

    public BaseMultiTypeAdapter() {
        this(null);
    }

    public BaseMultiTypeAdapter(List<BaseItem<T>> data) {
        this.mData = data == null ? new ArrayList<BaseItem<T>>() : data;
    }

    public void setData(List<BaseItem<T>> data) {
//        this.mData.clear();
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (int i = 0; i < mData.size(); i++) {
            BaseItem baseItem = mData.get(i);
            if (viewType == baseItem.getItemViewType()) {
                BaseViewHolder viewHolder = baseItem.onCreateViewHolder(parent, viewType);
                if (baseItem instanceof DisplayItem) {
                    viewHolder.setClickable(false);
                } else {
                    viewHolder.setClickable(true);
                }
                return viewHolder;
            }
        }
        throw new RuntimeException("no viewType valid");
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        this.mData.get(position).onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.mData.get(position).getItemViewType();
    }

    /**
     * 设置要展示的布局
     * @param resource
     */
    public void setDisplayLayout(@LayoutRes int resource) {
        this.mDisplayLayoutRes = resource;
        if (mDisplayLayoutRes <= 0) {
            return;
        }
        mData.clear();
        mData.add(new DisplayItem(mDisplayLayoutRes));
        notifyDataSetChanged();
    }

    /**
     * 获取当前显示布局view
     * @param bindView
     */
    public void getDisplayView(OnBindView bindView) {
        DisplayItem item = (DisplayItem) mData.get(0);
        if (item == null) {
            throw new IllegalStateException("you should call setDisplayLayout() method before call getDisplayView() method ");
        }
        item.onBindView(bindView);
    }
}
