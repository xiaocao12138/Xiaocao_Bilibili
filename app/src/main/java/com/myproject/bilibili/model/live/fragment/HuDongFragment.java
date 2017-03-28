package com.myproject.bilibili.model.live.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;
import com.myproject.bilibili.model.live.bean.LiveInfoBean;
import com.myproject.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by chen on 2017/3/25 10:17.
 * 作用:XXXX
 */

public class HuDongFragment extends BaseFragment {

    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private List<LiveInfoBean.DataBean.HotWordBean> hotWord;
    private HuDongAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hudong, null);
        ButterKnife.bind(this, view);

        /**
         * 上拉下拉顺心
         */
        refresh.setMaterialRefreshListener(new MyMaterialRefreshListener());
        refresh.setLoadMore(true);

        return view;
    }

    /**
     * 判断上拉下拉是否刷新
     */
    private boolean isLoadMore = false;


    /**
     * 上拉下拉刷新
     */
    class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isLoadMore = false;
            getDataFromNet();
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            isLoadMore = true;
            getDataFromNet();

        }
    }

//    private String[] titles = {"11111", "222222222", "444444444444444", "555555555555555"};

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
       /* HuDongAdapter adapter = new HuDongAdapter(mContext, titles);
        listView.setAdapter(adapter);*/
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(AppNetConfig.LIVE_INFO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "onError" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "onResponse" + response);
                        processData(response);


                        if(!isLoadMore){
                            //完成刷新
                            refresh.finishRefresh();
                            adapter.notifyDataSetChanged();
                        }else{
                            //把上拉的隐藏
                            refresh.finishRefreshLoadMore();
                        }

                    }
                });
    }

    private void processData(String json) {


        /*if (!isLoadMore){
            RecommendBean recommendBean = JSON.parseObject(json, RecommendBean.class);
            data = recommendBean.getData();
            setAdapter();
        }else {
            RecommendBean bean = JSON.parseObject(json, RecommendBean.class);
            data.addAll(bean.getData());
            //刷新适配器
            adapter.notifyDataSetChanged();//getCount-->getView
        }*/

//        LiveInfoBean liveInfoBean = JSON.parseObject(json, LiveInfoBean.class);
//
//        hotWord = liveInfoBean.getData().getHot_word();

        /*if (!isLoadMore){
            LiveInfoBean liveInfoBean = JSON.parseObject(json, LiveInfoBean.class);

            hotWord = liveInfoBean.getData().getHot_word();
        }else {
            LiveInfoBean liveInfoBean = JSON.parseObject(json, LiveInfoBean.class);

            List<LiveInfoBean.DataBean.HotWordBean> hot_word = liveInfoBean.getData().getHot_word();

            hotWord.addAll(hot_word.get(1).getWords());
        }*/

        LiveInfoBean liveInfoBean = JSON.parseObject(json, LiveInfoBean.class);

        hotWord = liveInfoBean.getData().getHot_word();

        adapter = new HuDongAdapter(mContext, hotWord);
        listView.setAdapter(adapter);
    }

    class HuDongAdapter extends BaseAdapter {

        private final Context mContext;
        private final List<LiveInfoBean.DataBean.HotWordBean> list;

        public HuDongAdapter(Context mContext, List<LiveInfoBean.DataBean.HotWordBean> hotWord) {
            this.mContext = mContext;
            this.list = hotWord;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_hudong, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            LiveInfoBean.DataBean.HotWordBean hotWordBean = list.get(position);

            holder.textView.setText(hotWordBean.getWords());

            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.text_view)
            TextView textView;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}