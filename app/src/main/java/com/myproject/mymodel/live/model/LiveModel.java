package com.myproject.mymodel.live.model;

import com.myproject.mymodel.base.BaseListener;

/**
 * Created by chen on 2017/4/6 18:45.
 * 作用:XXXX
 */

public interface LiveModel {

    void getDataFromNet(String url , BaseListener listener);

    void getDataFromNet(String url , LiveListener listener);

}
