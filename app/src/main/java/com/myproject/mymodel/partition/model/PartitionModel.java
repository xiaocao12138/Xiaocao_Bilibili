package com.myproject.mymodel.partition.model;

import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.live.model.LiveListener;

/**
 * Created by chen on 2017/4/7 10:45.
 * 作用:XXXX
 */

public interface PartitionModel {

    void getDataFromNet(String url , BaseListener listener);

    void getDataFromNet(String url , LiveListener listener);

}
