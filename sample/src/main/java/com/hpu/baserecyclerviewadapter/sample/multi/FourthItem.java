package com.hpu.baserecyclerviewadapter.sample.multi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7.
 */

public class FourthItem extends BaseItem {
    private Map<Integer, Integer> map = new HashMap<>();
    public FourthItem(int resource) {
        super(resource);
    }

    @Override
    public int getLayoutResource() {
        return super.getLayoutResource();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {



        //设置图片的相对于屏幕的宽高比


        ImageView img = holder.findViewById(R.id.img);
        WindowManager wm = (WindowManager) img.getContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        int width = point.x;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = width/2;
        if (position % 2 == 0) {
            params.height = 500;
        } else {
            params.height = 700;
        }
        img.setLayoutParams(params);
        Glide.with(img.getContext()).load(R.drawable.meinv).centerCrop().into(img);


    }
}
