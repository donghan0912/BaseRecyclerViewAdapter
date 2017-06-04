package com.hpu.baserecyclerviewadapter.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hpu.baserecyclerviewadapter.EndlessRecyclerOnScrollListener;
import com.hpu.baserecyclerviewadapter.adapter.BaseMultiTypeAdapter;
import com.hpu.baserecyclerviewadapter.adapter.BaseRecyclerViewAdapter;
import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
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
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        final List<BaseItem<String>> aaa = new ArrayList();
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
        final BaseMultiTypeAdapter adapter1 = new BaseMultiTypeAdapter();
        recyclerView.setAdapter(adapter1);
        adapter1.setData(aaa);
//        adapter1.setDisplayLayout(R.layout.layout_loading);
//        adapter1.setOnItemClickListener(new BaseMultiTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
//            }
//        });
//        adapter1.addHeader(new BaseItem() {
//            @Override
//            public int getLayoutResource() {
//                return R.layout.layout_head;
//            }
//
//            @Override
//            public void onBindViewHolder(BaseViewHolder holder, final int position) {
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(), position + "我是头，哈哈哈", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                holder.setOnClickListener(R.id.head, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(), position + "我是头的子View", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//        adapter1.addFooter(new BaseItem() {
//            @Override
//            public int getLayoutResource() {
//                return R.layout.layout_loadmore;
//            }
//
//            @Override
//            public void onBindViewHolder(BaseViewHolder holder, final int position) {
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getContext(), position + "尾巴，哈哈哈", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                adapter1.setDisplayLayout(R.layout.layout_error);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        adapter1.setData(aaa);
//                    }
//                }, 4000);
//
//            }
//        }, 4000);

        adapter1.setLoadMore(R.layout.layout_loadmore);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                List<BaseItem> list = new ArrayList<BaseItem>();
                for (int i = 0; i < 10; i++) {
                    list.add(new BaseItem<String>("这是第" + i + "条新数据", R.layout.sample_fir_item) {

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
                adapter1.addData(list);
            }
        });

    }


}
