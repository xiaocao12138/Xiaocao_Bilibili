package com.myproject.mymodel.live.model;

/**
 * Created by chen on 2017/4/6 18:34.
 * 作用:XXXX
 */

public interface LiveListener {

    void onSuccess(String response);

    void onFailed(Exception e);

}
