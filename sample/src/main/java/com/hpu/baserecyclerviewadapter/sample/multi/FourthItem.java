package com.hpu.baserecyclerviewadapter.sample.multi;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpu.baserecyclerviewadapter.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;

/**
 * Created by Administrator on 2017/6/7.
 */

public class FourthItem extends BaseItem<Integer> {
    public FourthItem(int height, int resource) {
        super(height, resource);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ImageView img = holder.findViewById(R.id.img);
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.height = mData;
        img.setLayoutParams(params);
        Glide.with(img.getContext()).load(R.drawable.meinv).centerCrop().into(img);
    }
}
