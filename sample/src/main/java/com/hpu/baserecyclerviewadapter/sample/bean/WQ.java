package com.hpu.baserecyclerviewadapter.sample.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by lenovo on 2017/6/8.
 */

public class WQ {
    public int height;
    @DrawableRes
    public int id;

    public WQ(int height, @DrawableRes int id) {
        this.height = height;
        this.id = id;
    }
}
