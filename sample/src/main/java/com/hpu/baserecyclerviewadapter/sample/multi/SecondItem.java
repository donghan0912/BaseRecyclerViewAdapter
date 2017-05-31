package com.hpu.baserecyclerviewadapter.sample.multi;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
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
        holder.setText(R.id.content3, mData);
        holder.setTextColor(R.id.content3, R.color.colorAccent);
        holder.setOnClickListener(R.id.content3, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), position + "/红色", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
