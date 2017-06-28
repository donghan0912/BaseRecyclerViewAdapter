package com.hpu.baserecyclerviewadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/6/5
 */

public class SimpleItem<T> extends BaseItem<T> {

    public SimpleItem(@LayoutRes int resource) {
        super(resource);
    }

    public SimpleItem(@Nullable T t, @LayoutRes int resource) {
        super(t, resource);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }
}
