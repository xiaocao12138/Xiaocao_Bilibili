package com.myproject.mymodel.recommend.view;

/**
 * Created by chen on 2017/4/6 21:10.
 * 作用:XXXX
 */

public interface RecommendView {

    void processData(String response);

    void onFailed(Exception e);
}
