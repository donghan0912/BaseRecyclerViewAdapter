package com.hpu.baserecyclerviewadapter.item;

import android.view.View;

/**
 * Created by Administrator on 2017/5/26.
 */

public interface OnBindView {
    /**
     *  because of the data binding delay, so in order to get the itemview by interface
     *  通过回调拿到itemview
     * @param view 当前绑定的itemView
     */
    void onBindView(View view);
}
