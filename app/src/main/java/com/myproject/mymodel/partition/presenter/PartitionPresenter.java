package com.myproject.mymodel.partition.presenter;

import android.util.Log;

import com.myproject.mymodel.base.BaseListener;
import com.myproject.mymodel.live.model.LiveListener;
import com.myproject.mymodel.partition.model.PartitionImpl;
import com.myproject.mymodel.partition.model.PartitionModel;
import com.myproject.mymodel.partition.view.PartitionView;

/**
 * Created by chen on 2017/4/7 10:48.
 * 作用:XXXX
 */

public class PartitionPresenter implements PartitionPresenterListener {

    private PartitionView partitionView;
    private PartitionModel partitionModel;

    public PartitionPresenter(PartitionView partitionView) {
        this.partitionView = partitionView;
        this.partitionModel = new PartitionImpl();
    }

    @Override
    public void getDataFromNet(String url) {
        partitionModel.getDataFromNet(url, new MyLiveListener());
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
        partitionModel.getDataFromNet(url, new BaseListener() {
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
            if (partitionView != null) {
                partitionView.processData(response);
            }
        }

        @Override
        public void onFailed(Exception e) {
            Log.e("TAG", "onFailed: "+e.getMessage() );
        }
    }

}
