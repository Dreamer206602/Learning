package com.mx.learning.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.mx.learning.R;

/**
 * Created by Administrator on 2016/11/10.
 */
public class MyView extends View {
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    private int defaultSize;
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.MyView);

        defaultSize=a.getDimensionPixelSize(R.styleable.MyView_default_size,100);

        a.recycle();

    }

    private int getMySize(int defaultSize,int measureSpec){
        int mySize=defaultSize;

        int mode=MeasureSpec.getMode(measureSpec);
        int size=MeasureSpec.getSize(measureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                //如果没有指定大小，就是默认的大小
                mySize=defaultSize;
                break;
            case MeasureSpec.AT_MOST:
           //如果测量模式是最大取值为size
                mySize=size;
                break;
            case MeasureSpec.EXACTLY:
                mySize=size;
                break;
        }
        return mySize;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=getMySize(100,widthMeasureSpec);
        int height=getMySize(100,heightMeasureSpec);

        if(width<height){
            height=width;
        }else{
            width=height;
        }

        setMeasuredDimension(width,height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int r=getMeasuredWidth()/2;
        int centerX=getLeft()+r;
        int centerY=getTop()+r;

        Paint paint=new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawCircle(centerX,centerX,r,paint);

    }
}
