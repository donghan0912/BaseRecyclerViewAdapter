package com.hpu.baserecyclerviewadapter.item;

import android.support.annotation.LayoutRes;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;

/**
 * Created by Administrator on 2017/5/27.
 */

public class DisplayItem extends BaseItem {
    private OnBindView mBindView;

    public DisplayItem(@LayoutRes int resource) {
        this.mLayoutRes = resource;
    }

    @Override
    public int getLayoutResource() {
        return mLayoutRes;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        // 通过回调获取view
        if (mBindView != null) {
            mBindView.onBindView(holder.itemView);
        }
    }

    public void onBindView(OnBindView bindView) {
        this.mBindView = bindView;
    }
}
