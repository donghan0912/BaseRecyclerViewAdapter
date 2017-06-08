package com.hpu.baserecyclerviewadapter;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;


/**
 * Created by Administrator on 2017/5/19
 */

interface Item {

    @LayoutRes
    int getLayoutResource();

    int getItemViewType();

    BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(BaseViewHolder holder, int position);
}
