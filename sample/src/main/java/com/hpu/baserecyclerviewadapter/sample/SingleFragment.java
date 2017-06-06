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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hpu.baserecyclerviewadapter.EndlessRecyclerOnScrollListener;
import com.hpu.baserecyclerviewadapter.adapter.BaseMultiTypeAdapter;
import com.hpu.baserecyclerviewadapter.holder.BaseViewHolder;
import com.hpu.baserecyclerviewadapter.item.BaseItem;
import com.hpu.baserecyclerviewadapter.item.SimpleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class SingleFragment extends Fragment {
    private boolean loadMore = true;
    private List<BaseItem> list = new ArrayList<BaseItem>();

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

        final List<BaseItem<String>> testData = new ArrayList();
        for (int i = 1; i < 20; i++) {
            testData.add(new BaseItem<String>("择天记 第" + i + "集") {

                @Override
                public int getLayoutResource() {
                    return R.layout.sample_fir_item;
                }

                @Override
                public void onBindViewHolder(BaseViewHolder holder, final int position) {
                    holder.setText(R.id.title, mData);
                }
            });
        }
        final BaseMultiTypeAdapter adapter = new BaseMultiTypeAdapter();
        recyclerView.setAdapter(adapter);
        
        adapter.setStatusItem(new SimpleItem(R.layout.layout_loading) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                // TODO 宽高设置
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.height = 900;
                holder.itemView.setLayoutParams(params);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setData(testData);
                adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));
            }
        }, 4000);
        adapter.setOnItemClickListener(new BaseMultiTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.addHeader(new BaseItem() {
            @Override
            public int getLayoutResource() {
                return R.layout.layout_head;
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, final int position) {
                ImageView img = holder.findViewById(R.id.head);
                Glide.with(img.getContext()).load(R.drawable.meinv).into(img);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), position + "我是头，哈哈哈", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.setOnClickListener(R.id.head, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), position + "我是头的子View", Toast.LENGTH_SHORT).show();
                    }
                });
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
                        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore_error) {
                            @Override
                            public void onBindViewHolder(BaseViewHolder holder, int position) {
                                holder.setOnClickListener(R.id.loadmore_error, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                List<BaseItem> list2 = new ArrayList<BaseItem>();
                                                for (int i = 20; i < 40; i++) {
                                                    list2.add(new BaseItem<String>("择天记 第" + i + "集", R.layout.sample_fir_item) {

                                                        @Override
                                                        public void onBindViewHolder(BaseViewHolder holder, final int position) {
                                                            holder.setText(R.id.title, mData);
                                                        }
                                                    });
                                                }
                                                list.addAll(list2);
                                                adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore_complete));
                                                loadMore = false;
                                                adapter.addData(list);
                                            }
                                        }, 4000);
                                    }
                                });
                            }
                        });
                    }
                }, 4000);
            }
        });

    }


}
