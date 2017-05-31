package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.adapter.BaseRecyclerViewAdapter;
import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class MultiFragment extends Fragment {

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
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("第" + i + "数据");
        }
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter() {

            private static final int TYPE_FIR = 1;
            private static final int TYPE_SEC = 0;

            @Override
            public int getLayoutId(int viewType) {
                if (viewType == TYPE_FIR) {
                    return R.layout.sample_fir_item;
                } else {
                    return R.layout.sample_sec_item;
                }
            }

            @Override
            public int getItemViewType(int position) {
                if (position % 2 == 0) {
                    return TYPE_FIR;
                } else {
                    return TYPE_SEC;
                }
            }

            @Override
            public void onBindRecyclerViewHolder(BaseViewHolder holder, final int position, int itemViewType) {
                if (itemViewType == TYPE_FIR) {
                    holder.setText(R.id.content, (String) getItem(position))
                            .setText(R.id.content2, getItem(position) + "sss");
                    holder.setOnClickListener(R.id.content, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), position + "/111", Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.setOnClickListener(R.id.content2, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), position + "/222", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    holder.setText(R.id.content3, (String) getItem(position))
                            .setText(R.id.content4, getItem(position) + "sss");
                }
            }

        };
        recyclerView.setAdapter(adapter);
        adapter.setData(list);


    }
}
