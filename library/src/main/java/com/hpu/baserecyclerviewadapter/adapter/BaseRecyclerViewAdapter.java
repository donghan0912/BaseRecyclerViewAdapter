package com.hpu.baserecyclerviewadapter.adapter;

import android.content.res.Resources.NotFoundException;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BaseRecyclerViewAdapter<T extends Object> extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int INITIAL_VALUE = 0x00001001;
    @LayoutRes
    private int mLayoutResId;
    private List<T> mData = new ArrayList<>();

    public BaseRecyclerViewAdapter() {
        this(0, null);
    }

    public BaseRecyclerViewAdapter(List<T> data) {
        this(0, data);
    }

    public BaseRecyclerViewAdapter(@LayoutRes int resource) {
        this(resource, null);
    }

    /**
     * @param resource
     * @param data     data source
     */
    public BaseRecyclerViewAdapter(@LayoutRes int resource, @Nullable List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        this.mLayoutResId = resource;
    }

    public @LayoutRes
    int getLayoutId(int viewType) {
        return mLayoutResId;
    }

    public void setData(List<T> data) {
        this.mData.clear();
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resourceId = getLayoutId(viewType);
        if (resourceId <= 0) {
            throw new NotFoundException("Resource ID \"" + resourceId + "\" is not valid, " +
                    "you should override constructor or the method getResourceId()");
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
        if (itemView == null) {
            return null;
        }
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (INITIAL_VALUE == itemViewType) {
            onBindRecyclerViewHolder(holder, position);
        } else {
            onBindRecyclerViewHolder(holder, position, itemViewType);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getItem(int position) {
        if (position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return INITIAL_VALUE;
    }

    /**
     * when single itemViewType, override this method
     *
     * @param holder
     * @param position
     * @param itemViewType
     */
    public void onBindRecyclerViewHolder(BaseViewHolder holder, int position, int itemViewType) {}

    /**
     * when multi itemViewType, override this method
     * @param holder
     * @param position
     */
    public void onBindRecyclerViewHolder(BaseViewHolder holder, int position) {}
}
