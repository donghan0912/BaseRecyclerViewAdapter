package com.hpu.baserecyclerviewadapter.sample.multi;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;

import static java.security.AccessController.getContext;

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
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.setText(R.id.content, mData);
        holder.setOnClickListener(R.id.content, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), position + "/普通", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
