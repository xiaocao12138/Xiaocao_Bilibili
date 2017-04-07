package com.myproject.mymodel.cartoon.presenter;

import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.cartoon.model.CartoonImpl;
import com.myproject.mymodel.cartoon.model.CartoonModel;
import com.myproject.mymodel.cartoon.view.CartoonView;

/**
 * Created by chen on 2017/4/6 23:43.
 * 作用:XXXX
 */

public class CartoonPresenter {

    private CartoonView cartoonView;
    private CartoonModel cartoonModel;

    public CartoonPresenter(CartoonView cartoonView) {
        this.cartoonView = cartoonView;
        this.cartoonModel = new CartoonImpl();
    }

    public void getData(String url){
        cartoonModel.getDataFromNet(url, new MyListener());
    }

    class MyListener implements BaseListener{

        @Override
        public void onSuccess(String response) {
            if (response != null){
                cartoonView.processData(response);
            }
        }

        @Override
        public void onFailed(Exception e) {
            cartoonView.onFailed(e);
        }
    }
}
