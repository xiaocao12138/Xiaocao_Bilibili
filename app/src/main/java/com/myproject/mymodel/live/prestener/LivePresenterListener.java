package com.myproject.mymodel.live.prestener;

import com.myproject.mymodel.base.BaseListener;

/**
 * Created by chen on 2017/4/6 18:54.
 * 作用:XXXX
 */

public interface LivePresenterListener {

    void getDataFromNet(String url);

    void onDestroy();

    void getDataFromNet(String url , BaseListener listener);
}
