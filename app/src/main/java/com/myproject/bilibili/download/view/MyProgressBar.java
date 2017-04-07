package com.myproject.bilibili.download.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.myproject.bilibili.R;
import com.myproject.bilibili.download.utils.BitmapUtils;

public class MyProgressBar extends View {

    private int viewHeight;
    private int viewWidth;
    private float progressWidth; //progress宽度
    private Paint paint;
    private int bgColor;//背景圆环颜色
    private int srcColor;//前景圆环颜色
    private int textColor;//字体颜色
    private int sweepArc;//当前弧度
    private float textSize;//字体大小

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.progress);
            bgColor = array.getColor(R.styleable.progress_bgColor, Color.GRAY);
            srcColor = array.getColor(R.styleable.progress_srcColor, Color.RED);
            textColor = array.getColor(R.styleable.progress_textColor, Color.BLUE);
            sweepArc = array.getInt(R.styleable.progress_sweepArc, 0);
            progressWidth = array.getDimension(R.styleable.progress_progressWidth, BitmapUtils.dip2px(context,8));
            textSize = array.getDimension(R.styleable.progress_textSize, BitmapUtils.dip2px(context,16));
            array.recycle();
        } else {
            bgColor = Color.GRAY;
            srcColor = Color.RED;
            textColor = Color.BLUE;
            sweepArc = 0;
            progressWidth = BitmapUtils.dip2px(context,8);
            textSize = BitmapUtils.dip2px(context,16);
        }

    }

    //初始化 画笔
    private void init() {
        paint = new Paint();

        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = viewWidth / 2;
        int cy = viewHeight / 2;
        float radius = viewWidth / 2 - progressWidth / 2;
        //背景圆
        paint.setStrokeWidth(progressWidth);
        paint.setColor(bgColor);
        canvas.drawCircle(cx, cy, radius, paint);

        //前景的弧
        paint.setStrokeWidth(progressWidth);
        paint.setColor(srcColor);
        RectF rectf = new RectF(progressWidth / 2, progressWidth / 2, viewWidth - progressWidth / 2, viewHeight - progressWidth / 2);
        canvas.drawArc(rectf, 0, sweepArc * 360 / 100, false, paint);

        //文字
        String text = sweepArc + "%";
        //包裹文字 计算文字宽度
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        float tx = viewWidth / 2 - bounds.width() / 2;
        float ty = viewHeight / 2 + bounds.height() / 2;
        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        canvas.drawText(text, tx, ty, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
    }

    //传入当前百分比即可 0~100
    public void setProgress(final int progress) {
        sweepArc = progress;
        postInvalidate();

    }
}
