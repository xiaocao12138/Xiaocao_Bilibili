package com.myproject.bilibili.model.shopping.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myproject.bilibili.R;
import com.myproject.bilibili.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chen on 2017/3/27 16:14.
 * 作用:XXXX
 */

public class ShopCartFragment extends BaseFragment {

    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_cart, null);
        ButterKnife.bind(this, view);

        //设置编辑状态栏
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //显示去结算布局
        llCheckAll.setVisibility(View.VISIBLE);

        /**
         * 切换状态
         */
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT){
                    //切换到完成状态
                    showDelete();
                }else {
                    //切换到编辑状态
                    hideDelete();
                }
            }
        });

        return view;
    }

    private void hideDelete() {
        //设置编辑状态栏
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //显示去结算布局
        llCheckAll.setVisibility(View.VISIBLE);
        //隐藏删除页面
        llDelete.setVisibility(View.GONE);
        //5.把所有的数据设置勾选择状态
        /*if (adapter != null){
            adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }*/
    }

    private void showDelete() {
        //设置删除状态栏
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        tvShopcartEdit.setText("删除");
        //隐藏去结算布局
        llCheckAll.setVisibility(View.GONE);
        //显示删除页面
        llDelete.setVisibility(View.VISIBLE);
        //5.把所有的数据设置非选择状态
        /*if (adapter != null){
            adapter.checkAll_none(false);
            adapter.checkAll();
            adapter.showTotalPrice();
        }*/
    }


    @Override
    public void initData() {
        super.initData();
    }

}
