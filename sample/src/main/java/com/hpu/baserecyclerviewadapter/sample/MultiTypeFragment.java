package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.adapter.BaseMultiTypeAdapter;
import com.hpu.baserecyclerviewadapter.listener.ItemClickListener;
import com.hpu.baserecyclerviewadapter.listener.OnItemClickListener;
import com.hpu.baserecyclerviewadapter.listener.OnItemLongClickListener;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.item.OnBindView;
import com.hpu.baserecyclerviewadapter.sample.multi.FirstItem;
import com.hpu.baserecyclerviewadapter.sample.multi.SecondItem;
import com.hpu.baserecyclerviewadapter.sample.multi.ThirdItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class MultiTypeFragment extends Fragment {
    private List<BaseItem> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_multi, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new FirstItem("第" + i + "条数据"));
            list.add(new SecondItem("第" + i + "条数据"));
            list.add(new ThirdItem(R.drawable.meinv));
        }
        final BaseMultiTypeAdapter baseMultiTypeAdapter = new BaseMultiTypeAdapter();
        recyclerView.setAdapter(baseMultiTypeAdapter);

        recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        }));

        recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        }));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseMultiTypeAdapter.setDisplayLayout(R.layout.layout_loading);
                baseMultiTypeAdapter.getDisplayView(new OnBindView() {
                    @Override
                    public void onBindView(View view) {
                        TextView t = (TextView) view.findViewById(R.id.tv_message);
                        Toast.makeText(getContext(),  t.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        baseMultiTypeAdapter.setData(list);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                baseMultiTypeAdapter.setDisplayLayout(R.layout.layout_empty);
                                baseMultiTypeAdapter.getDisplayView(new OnBindView() {
                                    @Override
                                    public void onBindView(View view) {
                                        TextView t = (TextView) view.findViewById(R.id.tv_message);
                                        Toast.makeText(getContext(),  t.getText().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        List list = new ArrayList<>();
                                        for (int i = 0; i < 5; i++) {
                                            list.add(new FirstItem("第" + i + "条数据"));
                                            list.add(new SecondItem("第" + i + "条数据"));
                                            list.add(new ThirdItem(R.drawable.meinv));
                                        }
                                        baseMultiTypeAdapter.setData(list);
                                    }
                                }, 4000);
                            }
                        }, 4000);
                    }
                }, 4000);


            }}, 4000);
    }
}