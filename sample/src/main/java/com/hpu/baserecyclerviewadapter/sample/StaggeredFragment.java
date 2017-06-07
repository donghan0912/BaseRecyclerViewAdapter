package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpu.baserecyclerviewadapter.adapter.BaseMultiTypeAdapter;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.item.SimpleItem;
import com.hpu.baserecyclerviewadapter.sample.multi.FirstItem;
import com.hpu.baserecyclerviewadapter.sample.multi.FourthItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class StaggeredFragment extends Fragment {

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
        List<BaseItem> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(new FourthItem());
        }
        final BaseMultiTypeAdapter adapter = new BaseMultiTypeAdapter(list);
        recyclerView.setAdapter(adapter);
//        adapter.addHeader(new SimpleItem(R.layout.layout_head));
//        adapter.addFooter(new SimpleItem(R.layout.layout_foot));
//        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));

//        adapter.setStatusItem(new SimpleItem(R.layout.layout_loading));
    }
}
