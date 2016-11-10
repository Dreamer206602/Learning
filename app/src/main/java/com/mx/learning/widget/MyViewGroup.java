package com.mx.learning.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/10.
 */

/**
 * 模仿LinearLayout的垂直布局
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 获取字View的宽度最大值
     * @return
     */
    private int getMaxChildWidth(){
        int childCount=getChildCount();
        int maxWidth=0;
        for (int i = 0; i <childCount ; i++) {

            View childView=getChildAt(i);
            if(childView.getMeasuredWidth()>maxWidth){
                maxWidth=childView.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    /**
     * 将所有的字View高度进行相加
     * @return
     */
    private int getTotalHeight(){
        int childCount=getChildCount();
        int height=0;
        for (int i = 0; i < childCount; i++) {

            View childView=getChildAt(i);
            height+=childView.getMeasuredHeight();

        }

        return height;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 将所有的子View进行测量，这会触发每个自View的onMeasure函数
        // 注意和measureChild()进行区分，measureCHild是对每个View进行测量
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);


        int heightMode=MeasureSpec.getSize(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int childCount=getChildCount();

        if(childCount==0){
            setMeasuredDimension(0,0);
        }else{

            // 如果宽度和高度都是包裹内容
            if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
                //我们将高度设置为所有自View的高度进行相加，宽度设置为View中最大的宽度
                int height=getTotalHeight();
                int width=getMaxChildWidth();

                setMeasuredDimension(width,height);
            }else if(heightMode==MeasureSpec.AT_MOST){
                //宽度设置为ViewGroup自己的测量高度，高度设置为所有子View的高度总和
                setMeasuredDimension(widthSize,getTotalHeight());
            }else if(widthMode==MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxChildWidth(),heightSize);
            }



        }









    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count=getChildCount();
        //记录当前的高度位置
        int curHeight=t;
        for (int i = 0; i <count ; i++) {

            View child=getChildAt(i);

            int height=child.getMeasuredHeight();
            int width=child.getMeasuredWidth();

            child.layout(l,curHeight,l+width,curHeight+height);
            curHeight+=height;


        }


    }
}
