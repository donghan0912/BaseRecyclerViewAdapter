package com.hpu.baserecyclerviewadapter.item;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;


/**
 * Created by Administrator on 2017/5/19.
 */

public interface Item {
    @LayoutRes
    int getLayoutResource();

    int getItemViewType();

    BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(BaseViewHolder holder, int position);
}