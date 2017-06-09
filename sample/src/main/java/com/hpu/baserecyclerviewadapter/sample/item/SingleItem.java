package com.hpu.baserecyclerviewadapter.sample.item;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.sample.R;

/**
 * Created by Administrator on 2017/6/9.
 */

public class SingleItem extends BaseItem<Integer> {

    public SingleItem(@DrawableRes int id) {
        this.mData = id;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_single;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ImageView avatar = holder.findViewById(R.id.avatar);
        Glide.with(avatar.getContext()).load(mData).into(avatar);
    }
}
