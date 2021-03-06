![](https://img.shields.io/badge/build-v0.0.3-brightgreen.svg)
## 介绍
一个简单、易维护的RecyclerVeiw.Adapter封装库  

全库三个核心类，体积小，使用方便 

支持RecyclerView三种LinearLayoutManager、gridlayoutmanager、staggeredgridlayoutmanager布局管理器  

一行代码设置不同状态Layout（支持EmptyView、ErrorView、Loading等任意类型的布局以及数据绑定、事件点击）       

一行代码设置加载更多、加载失败等Layout（支持LoadMore、LoadMoreError等任意类型的布局以及数据绑定、事件点击）         

详情请查看sample示例，同时也可以下载apk，查看示例效果 

## APK下载
[下载 apk](https://fir.im/8n2j)

## 依赖

	dependencies {
		compile 'com.hpu:base-adapter:0.0.3'
	}

## 特点

1. 快速构建单/多类型item

2. 支持下拉刷新

3. 支持上拉加载更多(可自定义加载更多布局，包括上拉加载失败，完成等布局，高度解耦自定义)

4. 支持显示列表加载、出错、空等各种类型（高度解耦自定义）

5. 支持添加头布局（高度解耦自定义）

6. 支持添加尾布局（高度解耦自定义）

7. 支持item长按、点击事件

## 文章
[RecyclerView Adapter封装解析](http://www.jianshu.com/p/8792d76b8148)  

下图是adapter中数据结构：  
![图一](screenshot/adapter_data.png)

## 使用

1. 创建item列表

		public class SingleItem extends BaseItem<Integer> {

		    public SingleItem(@DrawableRes int id) {
		        this.mData = id;
		    }
		
		    @Override
		    public int getLayoutResource() {
		        return R.layout.item_single;
		    }
		
		    @Override
		    public void onBindViewHolder(BaseViewHolder holder, int position) {
		        ImageView avatar = holder.findViewById(R.id.avatar);
		        Glide.with(avatar.getContext()).load(mData).into(avatar);
		    }
		}

2. 添加测试item数据，并绑定adapter

		// 测试数据
        List<SingleItem> testData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            testData.add(new SingleItem(getAvatar()));
        }
        adapter = new BaseRecyclerViewAdapter<SingleItem>(testData);
        recyclerView.setAdapter(adapter);

3. 下拉刷新实现：  
  Activity or Framgent实现SwipeRefreshLayout.OnRefreshListener监听（具体可看SingleFragment类）  
  在OnRefresh()方法中，通过如下方法刷新数据

		adapter.insertData();

4. 上拉加载更多实现：

		// 上拉加载更多监听
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
               // 上拉加载
                
            }
        });

5. 添加加载更多、加载更多失败等布局：  

		adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));
	
6. 添加空、加载、出错等状态：

		adapter.setStatusItem(new SimpleItem(R.layout.layout_loading));

7. 添加Header布局

		// 添加头布局
        adapter.addHeader(new SimpleItem(R.layout.layout_head) {

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                // 绑定数据
            }
        });

8. 添加Footer布局

		// 添加尾布局
        adapter.addFooter(new SimpleItem(R.layout.layout_head) {

            @Override
            public void onBindViewHolder(final BaseViewHolder holder, final int position) {
                // 绑定数据
            }
        });

9. item长按、点击事件：

		adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // item点击事件

            }
        });

        adapter.setOnItemLongClickListener(new BaseRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                // item长按事件

            }
        });


 具体使用方式，请参考sample源码：[https://github.com/donghan0912/BaseRecyclerViewAdapter/tree/master/sample/src/main/java/com/hpu/baserecyclerviewadapter/sample](https://github.com/donghan0912/BaseRecyclerViewAdapter/tree/master/sample/src/main/java/com/hpu/baserecyclerviewadapter/sample)

## 注意   
**不同类型的条目，不要使用同一个Layout文件，因为是根据Layout唯一标识ID作为ViewType**

> 1. SimpleItem,是封装库中基于BaseItem的一个空实现类，目的是为了简化添加“头、尾、空、加载更多”等类型布局代码。
> 2. 当添加不需要绑定数据的Layout布局的时候，可以直接new SingleItem（layoutId）,添加到Adapter中，比如加载中、加载更多等
> 3. 当添加需要绑定数据的Layout布局的时候，可以通过实现SingleItem中的onBindViewHolder()方法，来绑定数据，或者处理点击事件，比如头布局、加载更多失败等

## 效果图
  
![图二](screenshot/linear.gif)  
![图三](screenshot/error.gif)  
![图四](screenshot/glid.gif)  
![图五](screenshot/staggered.gif)  
