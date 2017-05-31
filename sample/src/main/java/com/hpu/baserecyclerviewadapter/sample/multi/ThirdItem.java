package com.hpu.baserecyclerviewadapter.sample.multi;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        ImageView img = holder.findViewById(R.id.img);
        Glide.with(img.getContext()).load(mData).into(img);
        holder.setOnClickListener(R.id.img, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), position + "/图片", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
