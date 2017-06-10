## 介绍
一个简单、易维护的RecyclerVeiw.Adapter封装库

## APK下载
[下载 apk](https://fir.im/8n2j)

## 依赖
	compile 'com.hpu:base-adapter:0.0.1'

## 特点

1. 快速构建单/多类型item

2. 支持下拉刷新

3. 支持上拉加载更多(可自定义加载更多布局，包括上拉加载失败，完成等布局，高度解耦自定义)

4. 支持显示列表加载、出错、空等各种类型（高度解耦自定义）

5. 支持添加头布局（高度解耦自定义）

6. 支持添加尾布局（高度解耦自定义）

7. 支持item长按、点击事件

## 文章


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
        List<BaseItem> testData = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            testData.add(new SingleItem(getAvatar()));
        }
        adapter = new BaseRecyclerViewAdapter(testData);
        recyclerView.setAdapter(adapter);

3. 下拉刷新实现：  
  Activity or Framgent实现SwipeRefreshLayout.OnRefreshListener监听（具体可看SingleFragment类）  
  在OnRefresh()方法中，通过如下方法刷新数据

		adapter.insertData();

4. 加载更多实现：  

		adapter.setExtraItem(new SimpleItem(R.layout.layout_loadmore));
	
5. 空、加载、出错等状态：

		adapter.setStatusItem(new SimpleItem(R.layout.layout_loading));

6. 添加Header布局

		// 添加头布局
        adapter.addHeader(new BaseItem(R.layout.layout_head) {

            @Override
            public void onBindViewHolder(final BaseViewHolder holder, final int position) {
                // 绑定数据
            }
        });

7. 添加Footer布局

		// 添加尾布局
        adapter.addFooter(new BaseItem(R.layout.layout_head) {

            @Override
            public void onBindViewHolder(final BaseViewHolder holder, final int position) {
                // 绑定数据
            }
        });

8. item长按、点击事件：

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

## 注意  
> 1. SimpleItem,是封装库中基于BaseItem的一个空实现类，目的是为了简化添加“头、尾、空、加载更多”等类型布局代码。
> 2. 当添加不需要绑定数据的Layout布局的时候，可以直接new SingleItem（layoutId）,添加到Adapter中，比如加载中、加载更多等
> 3. 当添加需要绑定数据的Layout布局的时候，可以通过实现SingleItem中的onBindViewHolder()方法，来绑定数据，或者处理点击事件，比如头布局、加载更多失败等
> 4. **Adapter中的常规布局不要继承SingleItem，只有“头、尾、空、加载更多”等特殊类型布局，才可以使用这个。因为是通过 'item instanceof SimpleItem' 方式，来判断是否需要点击事件，当前item是否是Header、Footer等**

## 效果图
具体使用方法，可查看sample测试案例代码
