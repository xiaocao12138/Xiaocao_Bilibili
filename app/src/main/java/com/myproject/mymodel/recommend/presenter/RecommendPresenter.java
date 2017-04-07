package com.myproject.mymodel.recommend.presenter;

import android.util.Log;

import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.recommend.model.RecommendImpl;
import com.myproject.mymodel.recommend.model.RecommendModel;
import com.myproject.mymodel.recommend.view.RecommendView;

/**
 * Created by chen on 2017/4/6 21:11.
 * 作用:XXXX
 */

public class RecommendPresenter {

    private RecommendView recommendView;
    private RecommendModel recommendModel;

    public RecommendPresenter(RecommendView recommendView) {
        this.recommendView = recommendView;
        this.recommendModel = new RecommendImpl();
    }

    public void getData(String url){
        recommendModel.getDataFromNet(url , new MyListener());
    }

    private class MyListener implements BaseListener {
        @Override
        public void onSuccess(String response) {
            Log.e("TAG", "onSuccess: "+response );
            if (response != null){
                recommendView.processData(response);
            }
        }

        @Override
        public void onFailed(Exception e) {
            recommendView.onFailed(e);
        }
    }
}
