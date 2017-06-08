package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.EndlessRecyclerOnScrollListener;
import com.hpu.baserecyclerviewadapter.BaseRecyclerViewAdapter;
import com.hpu.baserecyclerviewadapter.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.SimpleItem;
import com.hpu.baserecyclerviewadapter.sample.multi.FourthItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class StaggeredFragment extends Fragment {
    private boolean loadMore = true;
    private List<BaseItem> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_multi, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        for (int i = 0; i < 20; i++) {
            list.add(new FourthItem(getRandomHeight(), R.layout.layout_staggered));
        }
        final BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.addHeader(new SimpleItem(R.layout.layout_head) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                p.setFullSpan(true);
            }
        });

        // 设置加载更多布局，并占满整行
        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                p.setFullSpan(true);
            }
        });
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (!loadMore) {
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                List<BaseItem> list2 = new ArrayList<BaseItem>();
                                for (int i = 0; i < 20; i++) {
                                    list2.add(new FourthItem(getRandomHeight(), R.layout.layout_staggered));
                                }
                                list.addAll(list2);
                                if (list.size() > 40) {
                                    adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore_complete) {
                                        @Override
                                        public void onBindViewHolder(BaseViewHolder holder, int position) {
                                            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                                            p.setFullSpan(true);
                                        }
                                    });
                                    loadMore = false;
                                }
                                adapter.addData(list2);
                            }
                        }, 4000);

                    }
                }, 4000);
            }
        });

    }

    private int getRandomHeight() {
        int height = (int) (400 + Math.random() * 100);
        return height;
    }
}
