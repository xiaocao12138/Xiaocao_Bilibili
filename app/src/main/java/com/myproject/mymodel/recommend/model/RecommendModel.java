package com.myproject.mymodel.recommend.model;

import com.myproject.mymodel.base.BaseListener;

/**
 * Created by chen on 2017/4/6 21:07.
 * 作用:XXXX
 */

public interface RecommendModel {

    void getDataFromNet(String url , BaseListener listener);

}
