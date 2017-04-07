package com.myproject.mymodel.live.view;

/**
 * Created by chen on 2017/4/6 18:53.
 * 作用:XXXX
 */

public interface LiveView {

    void onFailed(Exception ex);

    void processData(String response);


}
