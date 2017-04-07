package com.myproject.mymodel.live.prestener;

import android.util.Log;

import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.live.model.LiveImpl;
import com.myproject.mymodel.live.model.LiveListener;
import com.myproject.mymodel.live.model.LiveModel;
import com.myproject.mymodel.live.view.LiveFragment;
import com.myproject.mymodel.live.view.LiveView;

/**
 * Created by chen on 2017/4/6 18:55.
 * 作用:XXXX
 */

public class LivePresenter implements LivePresenterListener{

    private LiveView liveView;
    private LiveModel liveImpl;

    public LivePresenter(LiveFragment liveFragment) {
        this.liveView = liveFragment;
        liveImpl = new LiveImpl();
    }


    @Override
    public void getDataFromNet(String url) {
        liveImpl.getDataFromNet(url, new MyLiveListener());
    }

    /*public void getDataFromNet(String url) {

            liveImpl.getDataFromNet(url, new MyLiveListener());

        }
    */
    @Override
    public void onDestroy() {

    }

    @Override
    public void getDataFromNet(String url, final BaseListener listener) {
        liveImpl.getDataFromNet(url, new BaseListener() {
            @Override
            public void onSuccess(String response) {
                if (listener != null){
                    listener.onSuccess(response);
                }
            }

            @Override
            public void onFailed(Exception e) {
                if (listener != null){
                    listener.onFailed(e);
                }
            }
        });
    }

    class MyLiveListener implements LiveListener {

        @Override
        public void onSuccess(String response) {
            if (liveView != null) {
                liveView.processData(response);
            }
        }

        @Override
        public void onFailed(Exception e) {
            Log.e("TAG", "onFailed: "+e.getMessage() );
        }
    }

}
