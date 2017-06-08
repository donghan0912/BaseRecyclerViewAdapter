package com.hpu.baserecyclerviewadapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View mItemView;
    private SparseArray<View> views;

    public BaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
        this.mItemView = itemView;
    }

    /**
     * return view by id
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(@IdRes int id) {
        View view = views.get(id);
        if (view == null) {
            view = mItemView.findViewById(id);
            if (view == null) {
                throw new NullPointerException("can not find view by id");
            }
            views.put(id, view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(@IdRes int id, CharSequence text) {
        TextView textView = findViewById(id);
        textView.setText(text);
        return this;
    }

    public BaseViewHolder setTextColor(@IdRes int id, @ColorRes int color) {
        TextView textView = findViewById(id);
        textView.setTextColor(ContextCompat.getColor(textView.getContext(), color));
        return this;
    }

    public BaseViewHolder setText(@IdRes int id, @StringRes int resid) {
        TextView textView = findViewById(id);
        textView.setText(resid);
        return this;
    }

    public BaseViewHolder setBackgroundResource(@IdRes int id, @DrawableRes int resid) {
        ImageView imageView = findViewById(id);
        imageView.setBackgroundResource(resid);
        return this;
    }

    public BaseViewHolder setBackgroundColor(@IdRes int id, @ColorRes int resid) {
        ImageView imageView = findViewById(id);
        imageView.setBackgroundColor(ContextCompat.getColor(imageView.getContext(), resid));
        return this;
    }

    public BaseViewHolder setImageBitmap(@IdRes int id, Bitmap bm) {
        ImageView imageView = findViewById(id);
        imageView.setImageBitmap(bm);
        return this;
    }

    public BaseViewHolder setImageDrawable(@IdRes int id, @Nullable Drawable drawable) {
        ImageView imageView = findViewById(id);
        imageView.setImageDrawable(drawable);
        return this;
    }

    /**
     *
     * @param id
     * @param visibility the value should be View.GONE, View.INVISIBLE or View.VISIBLE
     * @return
     */
    public BaseViewHolder setVisibility(@IdRes int id, int visibility) {
        View view = findViewById(id);
        if (visibility != View.GONE && visibility != View.INVISIBLE && visibility != View.VISIBLE) {
            throw new IllegalStateException("the visibility value not right");
        }
        view.setVisibility(visibility);
        return this;
    }

    public BaseViewHolder setOnClickListener(@IdRes int id, View.OnClickListener listener) {
        View view = findViewById(id);
        view.setOnClickListener(listener);
        return this;
    }


}
