package com.hpu.baserecyclerviewadapter.sample.multi;

import android.support.annotation.NonNull;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;

/**
 * Created by Administrator on 2017/5/25.
 */

public class FirstItem extends BaseItem<String> {

    public FirstItem(@NonNull String s) {
        super(s);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.sample_fir_item;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.content, mData);
    }
}
