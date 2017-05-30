package com.hpu.baserecyclerviewadapter.sample.multi;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;


/**
 * Created by Administrator on 2017/5/25.
 */

public class ThirdItem extends BaseItem<Integer> {

    public ThirdItem(@NonNull Integer integer) {
        super(integer);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.sample_thr_item;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ImageView img = holder.findViewById(R.id.img);
        Glide.with(img.getContext()).load(mData).into(img);
    }
}
