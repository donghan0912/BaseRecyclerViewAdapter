package com.hpu.baserecyclerviewadapter.adapter;

import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.DisplayItem;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.item.OnBindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BaseMultiTypeAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<BaseItem<T>> mData;
    private List<BaseItem> mHeaders = new ArrayList<>();
    private List<BaseItem> mFooters = new ArrayList<>();
    private List<DisplayItem> mDisplays = new ArrayList<>();
    private List<DisplayItem> mLoadMore = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BaseMultiTypeAdapter() {
        this(null);
    }

    public BaseMultiTypeAdapter(List<BaseItem<T>> data) {
        mData = new ArrayList<>();
        if (data != null) {
            mData.addAll(data);
        }
    }

    public void setData(List<BaseItem<T>> data) {
        mDisplays.clear();
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(BaseItem data) {
        mData.add(data);
        notifyItemInserted(mHeaders.size() + mData.size());
    }

    public void addData(List<BaseItem<T>> data) {
        int index = mData.size() + mHeaders.size();
        mData.addAll(data);
        notifyItemRangeInserted(index, data.size());
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        for (BaseItem item : mHeaders) {
            if (viewType == item.getItemViewType()) {
                return item.onCreateViewHolder(parent, viewType);
            }
        }
        for (BaseItem item : mFooters) {
            if (viewType == item.getItemViewType()) {
                return item.onCreateViewHolder(parent, viewType);
            }
        }
        for (DisplayItem item : mDisplays) {
            if (viewType == item.getItemViewType()) {
                return item.onCreateViewHolder(parent, viewType);
            }
        }
        for (DisplayItem item : mLoadMore) {
            if (viewType == item.getItemViewType()) {
                return item.onCreateViewHolder(parent, viewType);
            }
        }
        for (BaseItem item : mData) {
            if (viewType == item.getItemViewType()) {
                final BaseViewHolder viewHolder = item.onCreateViewHolder(parent, viewType);
                if (mOnItemClickListener != null) {
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition() - mHeaders.size());
                        }
                    });
                }
                if (mOnItemLongClickListener != null) {
                    viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return mOnItemLongClickListener.onItemLongClick(viewHolder.itemView, viewHolder.getAdapterPosition() - mHeaders.size());
                        }
                    });
                }
                return viewHolder;
            }
        }
        throw new RuntimeException("no viewType valid");
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int footerPosition = position - mData.size() - mHeaders.size() - mDisplays.size() - mLoadMore.size();
        int displayPosition = position - mHeaders.size();
        int loadMorePosition = position - mData.size() - mHeaders.size() - mDisplays.size();

        if (mHeaders.size() > 0 && position < mHeaders.size()) {
            mHeaders.get(position).onBindViewHolder(holder, position);
        } else if (mFooters.size() > 0 && footerPosition >= 0) {
            mFooters.get(footerPosition).onBindViewHolder(holder, footerPosition);
        } else if (mDisplays.size() > 0 && displayPosition >= 0) {
            mDisplays.get(displayPosition).onBindViewHolder(holder, displayPosition);
        } else if (mLoadMore.size() > 0 && loadMorePosition >= 0) {
            mLoadMore.get(loadMorePosition);
        } else {
            int dataPosition = position - mHeaders.size();
            mData.get(dataPosition).onBindViewHolder(holder, dataPosition);
        }
    }

    @Override
    public int getItemCount() {
        return mHeaders.size() + mData.size() + mFooters.size() + mDisplays.size() + mLoadMore.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaders.size() > 0 && position < mHeaders.size()) {
            return mHeaders.get(position).getItemViewType();
        } else if (mFooters.size() > 0 && position >= mData.size() + mHeaders.size() + mDisplays.size() + mLoadMore.size()) {
            return mFooters.get(position - mData.size() - mHeaders.size() - mDisplays.size() - mLoadMore.size()).getItemViewType();
        } else if (mDisplays.size() > 0 && position >= mHeaders.size()) {
            return mDisplays.get(position - mHeaders.size()).getItemViewType();
        } else if (mLoadMore.size() > 0 && position >= mData.size() + mHeaders.size() + mDisplays.size()) {
            return mLoadMore.get(position - mData.size() - mHeaders.size() - mDisplays.size()).getItemViewType();
        }
        return mData.get(position - mHeaders.size()).getItemViewType();
    }

    /**
     * 设置要展示的布局
     *
     * @param resource
     */
    public void setDisplayLayout(@LayoutRes int resource) {
        if (resource <= 0) {
            throw new Resources.NotFoundException("Resource ID \"" + resource + "\" is not valid, ");
        }
        mDisplays.clear();
        mData.clear();
        mDisplays.add(new DisplayItem(resource));
        notifyItemChanged(mHeaders.size());
    }

    /**
     * 获取当前显示布局view
     *
     * @param bindView
     */
    public void getDisplayView(OnBindView bindView) {
        if (mDisplays.size() == 0) {
            throw new IllegalStateException("you should call setDisplayLayout() method before call getDisplayView() method ");
        }
        DisplayItem item = mDisplays.get(0);
        item.onBindView(bindView);
    }

    public void addHeader(BaseItem item) {
        if (item == null) {
            throw new NullPointerException("item can't be null");
        }
        mHeaders.add(item);
        notifyItemInserted(0);
    }

    public void addFooter(BaseItem item) {
        if (item == null) {
            throw new NullPointerException("item can't be null");
        }
        mFooters.add(item);
        notifyItemInserted(mData.size() + mHeaders.size());
    }

    public void setLoadMore(@LayoutRes int resource) {
        if (resource <= 0) {
            throw new Resources.NotFoundException("Resource ID \"" + resource + "\" is not valid, ");
        }
        mLoadMore.add(new DisplayItem(resource));
        notifyItemInserted(mHeaders.size() + mData.size());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }
}
