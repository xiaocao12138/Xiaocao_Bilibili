package com.myproject.mymodel.utils;

import android.content.Context;
public class PermissionApplicationUtils {

    public static void CheckPerssion(final Context context, final IperssionCallBack callBack) {
//        RxPermissions.getInstance(context).request(Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_SMS,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.CAMERA,
//                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                if(callBack != null){
//                    if (aBoolean ) {
//                        callBack.isSuccess();
//                    } else {
//                        callBack.isFalure();
//                    }
//                }
//            }
//        });
    }

    public interface IperssionCallBack {
        void isSuccess();

        void isFalure();

    }

}