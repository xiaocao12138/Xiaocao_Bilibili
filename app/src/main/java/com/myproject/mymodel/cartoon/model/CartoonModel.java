package com.myproject.mymodel.cartoon.model;

import com.myproject.mymodel.base.BaseListener;

/**
 * Created by chen on 2017/4/6 23:38.
 * 作用:XXXX
 */

public interface CartoonModel {

    void getDataFromNet(String url , BaseListener listener);

}
