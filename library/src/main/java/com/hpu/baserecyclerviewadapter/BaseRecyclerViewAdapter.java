package com.hpu.baserecyclerviewadapter;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<BaseItem> mData;
    private List<BaseItem> mHeader = new ArrayList<>();
    private List<BaseItem> mFooter = new ArrayList<>();
    private List<SimpleItem> mExtra = new ArrayList<>();
    private List<SimpleItem> mStatus = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BaseRecyclerViewAdapter() {
        this(null);
    }

    public BaseRecyclerViewAdapter(List<? extends BaseItem> data) {
        mData = new ArrayList<>();
        if (data != null) {
            mData.addAll(data);
        }
    }

    public void setData(List<? extends BaseItem> data) {
        if (mData.size() > 0) {
            mData.clear();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public List<BaseItem> getData() {
        return mData;
    }

    /**
     *
     * @param position the position of the item
     * @return the item data
     */
    public BaseItem getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @SuppressWarnings("unused")
    public void addData(BaseItem data) {
        mData.add(data);
        notifyItemInserted(mHeader.size() + mStatus.size() + mData.size());
    }

    public void addData(List<? extends BaseItem> data) {
        int index = mHeader.size() + mStatus.size() + mData.size();
        mData.addAll(data);
        notifyItemRangeChanged(index, data.size());
    }

    public void insertData(BaseItem data, @IntRange(from = 0) int index) {
        mData.add(index, data);
        notifyItemInserted(mHeader.size() + mStatus.size() + index);
    }

    public void insertData(@NonNull List<? extends BaseItem> data, @IntRange(from = 0) int index) {
        mData.addAll(index, data);
        notifyItemRangeInserted(mHeader.size() + mStatus.size() + index, data.size());
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(mHeader.size() + mStatus.size() + position);
    }

    public void remove(BaseItem data) {
        if (data == null) {
            return;
        }
        int indexOf = mData.indexOf(data);
        if (mData.remove(data)) {
            notifyItemRemoved(mHeader.size() + mStatus.size() + indexOf);
        }
    }

    public void clear() {
        int count = mData.size();
        mData.clear();
        notifyItemRangeRemoved(mHeader.size(), count);
        removeExtraItem();
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
        for (SimpleItem item : mStatus) {
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
                if (mOnItemClickListener != null) {
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition() - mHeader.size() - mStatus.size());
                        }
                    });
                }
                if (mOnItemLongClickListener != null) {
                    viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return mOnItemLongClickListener.onItemLongClick(viewHolder.itemView, viewHolder.getAdapterPosition() - mHeader.size() - mStatus.size());
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
        if (isHeader(position)) {
            mHeader.get(position).onBindViewHolder(holder, position);
        } else if (isFooter(position)) {
            int footerPosition = position - mHeader.size() - mStatus.size() - mData.size() - mExtra.size();
            mFooter.get(footerPosition).onBindViewHolder(holder, footerPosition);
        } else if (isStatus(position)) {
            int statusPosition = position- mHeader.size();
            mStatus.get(statusPosition).onBindViewHolder(holder, statusPosition);
        } else if (isExtra(position)) {
            int extraPosition = position- mHeader.size() - mStatus.size() - mData.size();
            mExtra.get(extraPosition).onBindViewHolder(holder, extraPosition);
        } else {
            int dataPosition = position - mHeader.size() - mStatus.size();
            mData.get(dataPosition).onBindViewHolder(holder, dataPosition);
        }
    }

    @Override
    public int getItemCount() {
        return mHeader.size() + mStatus.size() + mData.size() + mFooter.size() + mExtra.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return mHeader.get(position).getItemViewType();
        } else if (isFooter(position)) {
            return mFooter.get(position - mHeader.size() - mStatus.size() - mData.size() - mExtra.size()).getItemViewType();
        } else if (isStatus(position)) {
            return mStatus.get(position - mHeader.size()).getItemViewType();
        } else if (isExtra(position)) {
            return mExtra.get(position - mHeader.size() - mStatus.size() - mData.size()).getItemViewType();
        }
        return mData.get(position - mHeader.size() - mStatus.size()).getItemViewType();
    }

    public void addHeader(BaseItem item) {
        if (item == null) {
            throw new NullPointerException("item can't be null");
        }
        mHeader.add(item);
        notifyItemInserted(0);
    }

    @SuppressWarnings("unused")
    public void addFooter(BaseItem item) {
        if (item == null) {
            throw new NullPointerException("item can't be null");
        }
        mFooter.add(item);
        notifyItemInserted(mHeader.size() + mStatus.size() + mData.size() + mExtra.size());
    }

    /**
     * 设置空页面、加载中、网络错误等类型布局
     *
     * @param simpleItem the status item
     */
    public void setStatusItem(SimpleItem simpleItem) {
        if (simpleItem == null) {
            throw new NullPointerException("the parameter simpleItem can't be null");
        }
        mStatus.clear();
        notifyItemRemoved(mHeader.size());
        mStatus.add(simpleItem);
        notifyItemInserted(mHeader.size());
    }

    public void removeStatusItem() {
        if (mStatus.size() > 0) {
            mStatus.clear();
            notifyItemRemoved(mHeader.size());
        }
    }

    /**
     * 设置加载更多、加载更多失败等类型布局
     *
     * @param simpleItem the extra item
     */
    public void setExtraItem(SimpleItem simpleItem) {
        if (simpleItem == null) {
            throw new NullPointerException("the parameter simpleItem can't be null");
        }
        mExtra.clear();
        notifyItemRemoved(mHeader.size() + mStatus.size() + mData.size());
        mExtra.add(simpleItem);
        notifyItemInserted(mHeader.size() + mStatus.size() + mData.size());
    }

    public void removeExtraItem() {
        if (mExtra.size() > 0) {
            mExtra.clear();
            notifyItemRemoved(mHeader.size() + mData.size());
        }
    }

    public boolean isHeader(int position) {
        return mHeader.size() > 0 && position < mHeader.size();
    }

    public boolean isFooter(int position) {
        return mFooter.size() > 0 && position >= mHeader.size() + mStatus.size() + mData.size() + mExtra.size();
    }

    public boolean isExtra(int position) {
        return mExtra.size() > 0 && position >= mHeader.size() + mStatus.size() + mData.size()
                && position < mHeader.size() + mStatus.size() + mData.size() + mExtra.size();
    }

    public boolean isStatus(int position) {
        return mStatus.size() > 0 && position >= mHeader.size()
                && position < mHeader.size() + mStatus.size();
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
