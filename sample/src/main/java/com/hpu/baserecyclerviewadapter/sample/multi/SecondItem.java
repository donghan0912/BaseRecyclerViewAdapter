package com.hpu.baserecyclerviewadapter.sample.multi;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpu.baserecyclerviewadapter.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;

/**
 * Created by Administrator on 2017/5/25.
 */

public class SecondItem extends BaseItem<String> {

    public SecondItem(@NonNull String s) {
        super(s);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.sample_sec_item;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        ImageView img = holder.findViewById(R.id.avatar);
        Glide.with(img.getContext()).load(R.drawable.avatar).into(img);
    }
}
