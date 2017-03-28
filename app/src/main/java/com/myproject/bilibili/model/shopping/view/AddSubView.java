package com.myproject.bilibili.model.shopping.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myproject.bilibili.R;


/**
 * Created by chen on 2017/2/28 14:34.
 * 作用:XXXX
 */

public class AddSubView extends LinearLayout {
    private final ImageView iv_sub;
    private final TextView tv_value;
    private final ImageView iv_add;

    private int value = 1;
    private int minValue = 1;
    private int maxValue= 10;

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View.inflate(context, R.layout.add_sub_view, AddSubView.this);
        iv_sub = (ImageView) findViewById(R.id.iv_sub);
        tv_value = (TextView) findViewById(R.id.tv_value);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        iv_sub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value > minValue){
                    value --;
                }

                setValue(value);

                if (listener != null){
                    listener.OnSetNumberChanges(value);
                }
            }
        });

        iv_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value < maxValue){
                    value ++;
                }

                setValue(value);

                if (listener != null){
                    listener.OnSetNumberChanges(value);
                }
            }
        });


        if (attrs != null) {
            //取出属性
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);
            int value = tintTypedArray.getInt(R.styleable.AddSubView_value, 0);
            if (value > 0) {
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_minValue, 0);
            if (value > 0) {
                setMinValue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_maxValue, 0);
            if (value > 0) {
                setMaxValue(maxValue);
            }
            Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberAddBackground);
            if (addDrawable != null) {
                iv_add.setImageDrawable(addDrawable);
            }
            Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberSubBackground);
            if (subDrawable != null) {
                iv_sub.setImageDrawable(subDrawable);
            }
        }
    }



    /**
     * 定义接口回调
     */
    public interface OnNumberChangesListener{
        void OnSetNumberChanges(int value);
    }

    private OnNumberChangesListener listener;

    public void setOnNumberChangesListener(OnNumberChangesListener listener) {
        this.listener = listener;
    }

}
