package com.myproject.mymodel.partition.view;

/**
 * Created by chen on 2017/4/7 10:47.
 * 作用:XXXX
 */

public interface PartitionView {

    void onFailed(Exception ex);

    void processData(String response);
}
