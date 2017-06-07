package com.hpu.baserecyclerviewadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.item.SimpleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class BaseMultiTypeAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<BaseItem<T>> mData;
    private List<BaseItem> mHeader = new ArrayList<>();
    private List<BaseItem> mFooter = new ArrayList<>();
    private List<SimpleItem> mExtra = new ArrayList<>();
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
        if (mData.size() > 0) {
            mData.clear();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(BaseItem data) {
        mData.add(data);
        notifyItemInserted(mHeader.size() + mData.size());
    }

    public void addData(List<BaseItem<T>> data) {
        int index = mData.size() + mHeader.size();
        mData.addAll(data);
        notifyItemRangeChanged(index, data.size());
//        notifyItemRangeInserted(index, data.size());
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        for (BaseItem item : mHeader) {
            if (viewType == item.getItemViewType()) {
                return item.onCreateViewHolder(parent, viewType);
            }
        }
        for (BaseItem item : mFooter) {
            if (viewType == item.getItemViewType()) {
                return item.onCreateViewHolder(parent, viewType);
            }
        }
        for (SimpleItem item : mExtra) {
            if (viewType == item.getItemViewType()) {
                return item.onCreateViewHolder(parent, viewType);
            }
        }
        for (BaseItem item : mData) {
            if (viewType == item.getItemViewType()) {
                final BaseViewHolder viewHolder = item.onCreateViewHolder(parent, viewType);
                if (item instanceof SimpleItem) {
                    return viewHolder;
                }
                if (mOnItemClickListener != null) {
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition() - mHeader.size());
                        }
                    });
                }
                if (mOnItemLongClickListener != null) {
                    viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return mOnItemLongClickListener.onItemLongClick(viewHolder.itemView, viewHolder.getAdapterPosition() - mHeader.size());
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
        int footerPosition = position - mData.size() - mHeader.size() - mExtra.size();
        int loadMorePosition = position - mData.size() - mHeader.size();

        if (mHeader.size() > 0 && position < mHeader.size()) {
            mHeader.get(position).onBindViewHolder(holder, position);
        } else if (mFooter.size() > 0 && footerPosition >= 0) {
            mFooter.get(footerPosition).onBindViewHolder(holder, footerPosition);
        } else if (mExtra.size() > 0 && loadMorePosition >= 0) {
            mExtra.get(loadMorePosition).onBindViewHolder(holder, loadMorePosition);
        } else {
            int dataPosition = position - mHeader.size();
            mData.get(dataPosition).onBindViewHolder(holder, dataPosition);
        }
    }

    @Override
    public int getItemCount() {
        return mHeader.size() + mData.size() + mFooter.size() + mExtra.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeader.size() > 0 && position < mHeader.size()) {
            return mHeader.get(position).getItemViewType();
        } else if (mFooter.size() > 0 && position >= mData.size() + mHeader.size() + mExtra.size()) {
            return mFooter.get(position - mData.size() - mHeader.size() - mExtra.size()).getItemViewType();
        } else if (mExtra.size() > 0 && position >= mData.size() + mHeader.size()) {
            return mExtra.get(position - mData.size() - mHeader.size()).getItemViewType();
        }
        return mData.get(position - mHeader.size()).getItemViewType();
    }

    public void addHeader(BaseItem item) {
        if (item == null) {
            throw new NullPointerException("item can't be null");
        }
        mHeader.add(item);
        notifyItemInserted(0);
    }

    public void addFooter(BaseItem item) {
        if (item == null) {
            throw new NullPointerException("item can't be null");
        }
        mFooter.add(item);
        notifyItemInserted(mData.size() + mHeader.size());
    }

    /**
     * 设置空页面、加载中、网络错误等类型布局
     *
     * @param simpleItem
     */
    public void setStatusItem(SimpleItem simpleItem) {
        if (simpleItem == null) {
            throw new NullPointerException("the parameter simpleItem can't be null");
        }
        removeExtraItem();
        int count = mData.size();
        mData.clear();
        notifyItemRangeRemoved(mHeader.size(), count);
        mData.add(simpleItem);
        notifyItemInserted(mHeader.size());
    }

    /**
     * 设置加载更多、加载更多失败等类型布局
     *
     * @param simpleItem
     */
    public void setExtraItem(SimpleItem simpleItem) {
        if (simpleItem == null) {
            throw new NullPointerException("the parameter simpleItem can't be null");
        }
        mExtra.clear();
        notifyItemRemoved(mHeader.size() + mData.size());
        mExtra.add(simpleItem);
        notifyItemInserted(mHeader.size() + mData.size());
    }

    public void removeExtraItem() {
        mExtra.clear();
        notifyItemRemoved(mHeader.size() + mData.size());
    }

    public boolean isHeader(int position) {
        if (mHeader.size() > 0 && position < mHeader.size()) {
            return true;
        }
        return false;
    }

    public boolean isFooter(int position) {
        if (mFooter.size() > 0 && position >= mData.size() + mHeader.size() + mExtra.size()) {
            return true;
        }
        return false;
    }

    public boolean isExtra(int position) {
        if (mExtra.size() > 0 && position >= mData.size() + mHeader.size() && position < (mData.size() + mHeader.size() + mExtra.size())) {
            return true;
        }
        return false;
    }

    public boolean isStatus(int position) {
        if (mData.size() == 1 && mData.get(0) instanceof SimpleItem && (position >= mHeader.size()
                && position < mHeader.size() + mData.size())) {
            return true;
        }
        return false;
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
