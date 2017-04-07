package com.myproject.mymodel.shopping.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.mymodel.shopping.bean.ShopInfoBean;
import com.myproject.mymodel.shopping.database.bean.GoodsBean;
import com.myproject.mymodel.shopping.database.dao.GoodsDao;
import com.myproject.mymodel.shopping.view.AddSubView;
import com.myproject.mymodel.utils.CommonUtil;

import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/29 10:26.
 * 作用:XXXX
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;
    private final GoodsDao dao;
    private final ShopInfoBean.ResultBean.CurrSkuBean currSku;

    public ShoppingCartAdapter(Context mContext, ShopInfoBean.ResultBean.CurrSkuBean currSku, GoodsDao dao, List<GoodsBean> list, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.mContext = mContext;
        this.datas = list;
        this.dao = dao;
        this.currSku = currSku;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;
        showTotalPrice();
    }

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("￥" + getTotalPrice());
    }

    private int getTotalPrice() {
        int totalPrice = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChecked()) {
                    totalPrice += goodsBean.getPrice() * goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final GoodsBean goodsBean = datas.get(position);

        boolean networkAvailable = CommonUtil.isNetworkAvailable(mContext);
        boolean isMobile = CommonUtil.isMobile(mContext);
        if (networkAvailable){
            if (isMobile){
                holder.ivGov.setImageResource(R.drawable.aa);
                Toast.makeText(mContext, "非wifi状态下不显示图片", Toast.LENGTH_SHORT).show();
            }else {
                Glide.with(mContext).load(goodsBean.getImageUrl()).crossFade().into(holder.ivGov);
            }
        }

//        Glide.with(mContext).load(goodsBean.getImageUrl()).crossFade().into(holder.ivGov);

        holder.tvDescGov.setText(goodsBean.getName());

        boolean checked = goodsBean.isChecked();
//        if (checked){
            holder.cbGov.setChecked(goodsBean.isChecked());
//        }

        holder.tvPriceGov.setText("￥" + goodsBean.getPrice());

        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMinValue(1);
        holder.addSubView.setMaxValue(currSku.getSkuInventory());
        holder.addSubView.setOnNumberChangesListener(new AddSubView.OnNumberChangesListener() {
            @Override
            public void OnSetNumberChanges(int value) {
                goodsBean.setNumber(value);

                dao.updataGoods(goodsBean);
                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    /**
     * 校验是否全选
     */
    public void checkAll() {
        if (datas != null && datas.size() > 0){
            int number = 0;
            for (int i=0 ; i<datas.size() ; i++){
                GoodsBean goodsBean = datas.get(i);
                if (! goodsBean.isChecked()){
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                }else {
                    number ++;
                }
            }

            if (datas.size() == number){
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }
        }else {
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    public void checkAll_none(boolean isChecked) {
        if (datas != null && datas.size() > 0){
            for (int i=0 ; i<datas.size() ; i++){
                GoodsBean goodsBean = datas.get(i);

                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                //设置CheckBox的状态
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);

                //根据位置刷新
                notifyItemChanged(i);
            }
        }
    }

    public void deleteData() {

        if (datas != null && datas.size() > 0) {
            for (Iterator iterator = datas.iterator(); iterator.hasNext(); ) {
                GoodsBean goodsBean = (GoodsBean) iterator.next();
                if (goodsBean.isChecked()) {
                    int i = datas.indexOf(goodsBean);
                    //本地删除
                    dao.deleteGoods(goodsBean);
                    //更新
                    notifyItemRemoved(i);

                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageButton ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.addSubView)
        AddSubView addSubView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.OnClickListener(v, getLayoutPosition());
                    }
                }
            });
        }
    }


    /**
     * 接口回调
     * item的监听事件
     */
    public interface OnItemClickListener {
        void OnClickListener(View view, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
