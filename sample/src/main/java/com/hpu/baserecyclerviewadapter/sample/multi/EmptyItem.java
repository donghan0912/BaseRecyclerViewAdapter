package com.hpu.baserecyclerviewadapter.sample.multi;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;

/**
 * Created by lenovo on 2017/5/25.
 */

public class EmptyItem extends BaseItem {

    @Override
    public int getLayoutResource() {
        return R.layout.layout_empty;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        // TODO adapter中实现EmptyView ErrorView等功能
    }
}
