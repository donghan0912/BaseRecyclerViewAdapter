package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.BaseRecyclerViewAdapter;
import com.hpu.baserecyclerviewadapter.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.EndlessRecyclerOnScrollListener;
import com.hpu.baserecyclerviewadapter.SimpleItem;
import com.hpu.baserecyclerviewadapter.sample.item.SingleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17
 */

public class SingleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mRefreshLayout;
    private BaseRecyclerViewAdapter<BaseItem> adapter;
    private boolean loadMore = true;
    private List<BaseItem> list = new ArrayList<>();
    private boolean isRefresh = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swip_refresh);
        mRefreshLayout.setColorSchemeResources(R.color.blue_bright, R.color.green_light, R.color.red_light);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnabled(false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 测试数据
        final List<BaseItem> testData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            testData.add(new SingleItem(getAvatar()));
        }
        adapter = new BaseRecyclerViewAdapter<>();
        recyclerView.setAdapter(adapter);

        // 添加头布局
        adapter.addHeader(new SimpleItem(R.layout.layout_head) {

            @Override
            public void onBindViewHolder(final BaseViewHolder holder, final int position) {
                // 绑定数据
            }
        });
        // 添加正在加载布局
        adapter.setStatusItem(new SimpleItem(R.layout.layout_loading));

        // 模拟网络环境，3秒后加载数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 数据加载成功之后，方可下拉刷新
                mRefreshLayout.setEnabled(true);
                // adapter加载数据
                adapter.setData(testData);
                // 如果有更多数据，设置加载更多布局
                adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));
            }
        }, 3000);

        // 添加点击事件
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });

        // 上拉加载更多监听
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (!loadMore) {
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 设置加载更多失败
                        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore_error) {
                            @Override
                            public void onBindViewHolder(BaseViewHolder holder, int position) {
                                // 点击重试加载更多事件
                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                List<BaseItem> list2 = new ArrayList<>();
                                                for (int i = 20; i < 40; i++) {
                                                    list2.add(new SingleItem(getAvatar()));
                                                }
                                                list.addAll(list2);
                                                adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore_complete));
                                                loadMore = false;
                                                adapter.addData(list);
                                            }
                                        }, 3000);
                                    }
                                });
                            }
                        });
                    }
                }, 3000);
            }
        });

    }

    private int getAvatar() {
        int[] avatars = new int[]{R.drawable.ic_launcher, R.drawable.avatar};
        return avatars[(int) (Math.random() * 2)];
    }

    @Override
    public void onRefresh() {
        if (!isRefresh) {
            isRefresh = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRefreshLayout.setRefreshing(false);
                    isRefresh = false;
                    List<BaseItem> list = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        list.add(new SingleItem(R.drawable.actor));
                    }
                    adapter.insertData(list, 0);
                }
            }, 3000);
        }
    }
}
