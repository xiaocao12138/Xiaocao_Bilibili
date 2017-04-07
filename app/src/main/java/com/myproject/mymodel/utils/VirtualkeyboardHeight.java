package com.myproject.mymodel.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by chen on 2017/3/1 15:30.
 * 作用:获取虚拟键盘的高度
 */

public class VirtualkeyboardHeight {

    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();

        @SuppressWarnings("rawtypes")
        Class c;
            try {
                    c = Class.forName("android.view.Display");
                    @SuppressWarnings("unchecked")
                    Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                    method.invoke(display, displayMetrics);
                    dpi = displayMetrics.heightPixels;
                } catch (Exception e) {
                e.printStackTrace();
            }
        return dpi;
     }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getBottomStatusHeight(Context context) {

        int totalHeight = getDpi(context);
        int contentHeight = getScreenHeight(context);
        return totalHeight - contentHeight;
    }

}
