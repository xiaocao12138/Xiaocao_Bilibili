package com.myproject.mymodel.partition.presenter;

import com.myproject.mymodel.base.BaseListener;

/**
 * Created by chen on 2017/4/7 10:48.
 * 作用:XXXX
 */

public interface PartitionPresenterListener {

    void getDataFromNet(String url);

    void onDestroy();

    void getDataFromNet(String url , BaseListener listener);

}
