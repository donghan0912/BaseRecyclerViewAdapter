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

import com.hpu.baserecyclerviewadapter.BaseRecyclerViewAdapter;
import com.hpu.baserecyclerviewadapter.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.BaseItem;
import com.hpu.baserecyclerviewadapter.SimpleItem;
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
        return inflater.inflate(R.layout.fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(new FirstItem("第" + i + "条数据"));
            list.add(new SecondItem("第" + i + "条数据"));
            if (i == 0) {
                list.add(new ThirdItem(R.drawable.luoluo_1));
            } else if (i == 1) {
                list.add(new ThirdItem(R.drawable.luoluo_2));
            } else if (i == 2) {
                list.add(new ThirdItem(R.drawable.luoluo_3));
            } else if (i == 3) {
                list.add(new ThirdItem(R.drawable.luoluo_4));
            } else if (i == 4) {
                list.add(new ThirdItem(R.drawable.luoluo_5));
            } else if (i == 5) {
                list.add(new ThirdItem(R.drawable.luoluo_6));
            }
        }
        final BaseRecyclerViewAdapter baseRecyclerViewAdapter = new BaseRecyclerViewAdapter();
        recyclerView.setAdapter(baseRecyclerViewAdapter);


        baseRecyclerViewAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });

        baseRecyclerViewAdapter.setOnItemLongClickListener(new BaseRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        baseRecyclerViewAdapter.setStatusItem(new SimpleItem(R.layout.layout_loading) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                TextView t = holder.findViewById(R.id.tv_message);
                Toast.makeText(getContext(), t.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseRecyclerViewAdapter.setData(list);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FragmentActivity activity = getActivity();
                        if (activity != null) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    baseRecyclerViewAdapter.setStatusItem(new SimpleItem(R.layout.layout_empty) {
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
