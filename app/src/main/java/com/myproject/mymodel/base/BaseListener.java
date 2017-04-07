package com.myproject.mymodel.base;

/**
 * Created by chen on 2017/4/6 21:05.
 * 作用:XXXX
 */

public interface BaseListener {

    void onSuccess(String response);

    void onFailed(Exception e);
}
