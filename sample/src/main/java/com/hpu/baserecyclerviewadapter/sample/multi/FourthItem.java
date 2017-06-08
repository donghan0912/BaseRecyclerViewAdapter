package com.hpu.baserecyclerviewadapter.sample.multi;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hpu.baserecyclerviewadapter.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;
import com.hpu.baserecyclerviewadapter.sample.bean.WQ;

/**
 * Created by Administrator on 2017/6/7.
 */

public class FourthItem extends BaseItem<WQ> {

    public FourthItem(WQ wq) {
        super(wq);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.item_staggered;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ImageView img = holder.findViewById(R.id.img);
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.height = mData.height;
        img.setLayoutParams(params);
        Glide.with(img.getContext()).load(mData.id).centerCrop().into(img);
    }
}
