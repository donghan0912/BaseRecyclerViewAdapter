package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.adapter.BaseMultiTypeAdapter;
import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.item.SimpleItem;
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


        baseMultiTypeAdapter.setOnItemClickListener(new BaseMultiTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });

        baseMultiTypeAdapter.setOnItemLongClickListener(new BaseMultiTypeAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        baseMultiTypeAdapter.setStatusItem(new SimpleItem(R.layout.layout_loading) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                TextView t = holder.findViewById(R.id.tv_message);
                Toast.makeText(getContext(), t.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseMultiTypeAdapter.setData(list);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentActivity activity = getActivity();
                        if (activity != null) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    baseMultiTypeAdapter.setStatusItem(new SimpleItem(R.layout.layout_empty) {
                                        @Override
                                        public void onBindViewHolder(BaseViewHolder holder, int position) {
                                            TextView t = holder.findViewById(R.id.tv_message);
                                            t.setText("sssssssssssssssssssss");
                                            Toast.makeText(getContext(), t.getText().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }
                }, 4000);
            }
        }, 4000);
    }
}
