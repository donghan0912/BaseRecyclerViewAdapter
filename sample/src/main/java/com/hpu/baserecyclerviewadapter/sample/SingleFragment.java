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
import com.hpu.baserecyclerviewadapter.adapter.BaseMultiTypeAdapter;
import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.listener.ItemClickListener;
import com.hpu.baserecyclerviewadapter.listener.OnItemClickListener;
import com.hpu.baserecyclerviewadapter.listener.OnItemLongClickListener;
import com.hpu.baserecyclerviewadapter.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class SingleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single, null);
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
        BaseRecyclerViewAdapter adapter = new BaseRecyclerViewAdapter(R.layout.sample_fir_item) {

            @Override
            public void onBindRecyclerViewHolder(BaseViewHolder holder, int position) {
                holder.setText(R.id.content, (String)getItem(position))
                        .setText(R.id.content2, getItem(position) + "sss");
            }
        };
//        recyclerView.setAdapter(adapter);
//        adapter.setData(list);

//        recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
//            }
//        }));
//
//        recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView, new OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
//            }
//        }));

        List<BaseItem<String>> aaa = new ArrayList();
        for (int i = 0; i < 20; i++) {
//            aaa.add(new FirstItem("第" + i + "数据aaa"));
            final String s = "第" + i + "数据aaagggggggggggg";
            aaa.add(new BaseItem<String>("第" + i + "数据sssssssss") {

                @Override
                public int getLayoutResource() {
                    return R.layout.sample_fir_item;
                }

                @Override
                public void onBindViewHolder(BaseViewHolder holder, final int position) {
                    holder.setText(R.id.content, mData);
                    holder.setOnClickListener(R.id.content, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), position + "/1234", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        BaseMultiTypeAdapter adapter1 = new BaseMultiTypeAdapter();
        recyclerView.setAdapter(adapter1);
        adapter1.setData(aaa);
        adapter1.setOnItemClickListener(new BaseMultiTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
