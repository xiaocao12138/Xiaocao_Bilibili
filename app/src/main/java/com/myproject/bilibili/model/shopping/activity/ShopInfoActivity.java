package com.myproject.bilibili.model.shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.myproject.bilibili.R;
import com.myproject.bilibili.model.found.activity.ShoppingActivity;
import com.myproject.bilibili.model.shopping.bean.ShopInfoBean;
import com.myproject.bilibili.model.shopping.view.AddSubView;
import com.myproject.bilibili.utils.AppNetConfig;
import com.myproject.bilibili.utils.Constants;
import com.myproject.bilibili.utils.VirtualkeyboardHeight;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ShopInfoActivity extends AppCompatActivity {

    @BindView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @BindView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @BindView(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @BindView(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @BindView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @BindView(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @BindView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @BindView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    //    private String title;
//    private String imageUrl;
//    private int price;
    private int skuId;
    private String shopInfoUrl;
    private ShopInfoBean.ResultBean.CurrSkuBean currSku;
    private String skuName;
    private int skuInventory;
    private int salePrice;
    private String Attributename;
    private String Attributevalue;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);
        ButterKnife.bind(this);
        getData();
        getDataFromNet(shopInfoUrl);
    }

    private void getDataFromNet(String shopInfoUrl) {
        OkHttpUtils.get()
                .url(shopInfoUrl)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ShopInfoBean shopInfoBean = JSON.parseObject(json, ShopInfoBean.class);
        imageUrl = shopInfoBean.getResult().getImgUrl();
        currSku = shopInfoBean.getResult().getCurrSku();
        skuName = currSku.getSkuName();
        skuInventory = currSku.getSkuInventory();
        salePrice = currSku.getSalePrice();
        Attributename = currSku.getAttribute().get(0).getName();
        Attributevalue = currSku.getAttribute().get(0).getValue();

        initData();
    }

    private void initData() {
        Glide.with(ShopInfoActivity.this).load(imageUrl).crossFade().into(ivGoodInfoImage);
        tvGoodInfoName.setText(skuName);
        tvGoodInfoPrice.setText(String.valueOf(salePrice));
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            skuId = intent.getIntExtra(Constants.SKUID, 0);
        } else {
            intent = getIntent();
        }

        shopInfoUrl = AppNetConfig.SHOPPING_INFO + skuId;
    }

    @OnClick({R.id.ib_good_info_back, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.tv_good_info_callcenter:
                Toast.makeText(this, "联系客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                Intent intent = new Intent(ShopInfoActivity.this, ShoppingActivity.class);
                intent.putExtra(Constants.CHECKEDID , R.id.rb_shop_cart);
                startActivity(intent);
                break;
            case R.id.btn_good_info_addcart:
                showPopwindow();
                break;
        }
    }

    private void showPopwindow() {
// 1 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        window.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.popupwindow_anim);

        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        AddSubView nas_goodinfo_num = (AddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);
        TextView shop_price_zong = (TextView) view.findViewById(R.id.shop_price_zong);
        TextView attribute_name = (TextView) view.findViewById(R.id.attribute_name);
        TextView attribute_value = (TextView) view.findViewById(R.id.attribute_value);
        TextView shopSkuInventoryNumber = (TextView) view.findViewById(R.id.shop_skuInventory_number);

        //加载图片
        Glide.with(ShopInfoActivity.this).load(imageUrl).into(iv_goodinfo_photo);
        //设置数据
        tv_goodinfo_name.setText(skuName);
        tv_goodinfo_price.setText("￥" + salePrice);

        attribute_name.setText(Attributename + "：");
        attribute_value.setText(Attributevalue);
        shopSkuInventoryNumber.setText(String.valueOf(skuInventory));

        // 设置最大值和当前值
        nas_goodinfo_num.setValue(1);
        nas_goodinfo_num.setMaxValue(skuInventory);

        //总价
        shop_price_zong.setText(salePrice * nas_goodinfo_num.getValue() + "￥");

        nas_goodinfo_num.setOnNumberChangesListener(new AddSubView.OnNumberChangesListener() {
            @Override
            public void OnSetNumberChanges(int value) {

            }
        });

        //确定
        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加到购物车
//                CartStorage.getInstance(mContext).addData(tempGoodsBean);
//                Log.e("TAG", "66:" + tempGoodsBean.toString());
                Toast.makeText(ShopInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
                window.dismiss();

            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });

        // 5 在底部显示
        window.showAtLocation(
                ShopInfoActivity.this.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0,
                //虚拟按键的计算
                VirtualkeyboardHeight.getBottomStatusHeight(ShopInfoActivity.this));

    }
}
