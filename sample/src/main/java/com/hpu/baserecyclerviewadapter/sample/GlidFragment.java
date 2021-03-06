package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.BaseRecyclerViewAdapter;
import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.EndlessRecyclerOnScrollListener;
import com.hpu.baserecyclerviewadapter.SimpleItem;
import com.hpu.baserecyclerviewadapter.sample.item.SingleItem;
import com.hpu.baserecyclerviewadapter.sample.multi.FirstItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17
 */

public class GlidFragment extends Fragment {
    private boolean loadMore = true;
    private List<BaseItem> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swip_refresh);
        refreshLayout.setEnabled(false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(manager);
        for (int i = 0; i < 20; i++) {
            list.add(new SingleItem(getAvatar()));
        }
        final BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.addHeader(new SimpleItem(R.layout.layout_head));
        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isExtra(position) || adapter.isHeader(position)
                        || adapter.isFooter(position) || adapter.isStatus(position) ? 2 : 1;
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
                                List<BaseItem> list2 = new ArrayList<>();
                                for (int i = 0; i < 20; i++) {
                                    list2.add(new SingleItem(getAvatar()));
                                }
                                list.addAll(list2);
                                if (list.size() > 40) {
                                    adapter.removeExtraItem();
                                    loadMore = false;
                                }
                                adapter.addData(list2);
                            }
                        }, 3000);

                    }
                }, 3000);
            }
        });
    }

    private int getAvatar() {
        int[] avatars = new int[]{R.drawable.ic_launcher, R.drawable.avatar};
        return avatars[(int) (Math.random() * 2)];
    }
}
