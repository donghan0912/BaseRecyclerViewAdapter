package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.adapter.BaseMultiTypeAdapter;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.sample.multi.FourthItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class StaggeredFragment extends Fragment {
    // TODO 参考：http://blog.csdn.net/qq_28057577/article/details/54579320
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
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(manager);
        List<BaseItem> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(new FourthItem(R.layout.layout_staggered));
        }
        final BaseMultiTypeAdapter adapter = new BaseMultiTypeAdapter(list);
        recyclerView.setAdapter(adapter);
//        adapter.addHeader(new SimpleItem(R.layout.layout_head));
//        adapter.addFooter(new SimpleItem(R.layout.layout_foot));
//        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));

//        adapter.setStatusItem(new SimpleItem(R.layout.layout_loading));
        adapter.setOnItemClickListener(new BaseMultiTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
