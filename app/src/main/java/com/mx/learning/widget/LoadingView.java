package com.mx.learning.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.mx.learning.R;

/**
 * Created by Administrator on 2016/11/14.
 */
public class LoadingView extends View  {

    private int mWidth;
    private int mHeight;
    private int paintBold;//线条粗细
    private int lineLength;
    private int bgPaintColor;//背景线条颜色
    private int beforePaintColor;//上层线条颜色
    private int textColor;
    private int lines;
    private Paint bgPaint;
    private Paint bfPaint;
    private Paint textPaint;
    private int progress;
    private int max;


    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttrs(context,attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        bgPaint=new Paint();
        bgPaint.setColor(bgPaintColor);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeWidth(paintBold);
        //使得画笔更加圆滑
        bgPaint.setStrokeJoin(Paint.Join.ROUND);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);

        bfPaint=new Paint();
        bfPaint.setColor(beforePaintColor);
        bfPaint.setAntiAlias(true);
        bfPaint.setStrokeWidth(paintBold);
        bfPaint.setStrokeJoin(Paint.Join.ROUND);
        bfPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint=new Paint();
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(40);

    }

    /**
     * 加载在attrs.xml文件中的自定义属性
     * @param context
     * @param attrs
     */
    private void loadAttrs(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        paintBold=array.getDimensionPixelSize(R.styleable.LoadingView_paintBold,10);
        lineLength=array.getDimensionPixelSize(R.styleable.LoadingView_lineLength,25);
        bgPaintColor=array.getColor(R.styleable.LoadingView_backgroundColor, Color.GRAY);
        beforePaintColor=array.getColor(R.styleable.LoadingView_beforeColor,Color.YELLOW);
        lines=array.getInt(R.styleable.LoadingView_lines,20);
        max=array.getInt(R.styleable.LoadingView_max,100);
        progress=array.getInt(R.styleable.LoadingView_progress,0);
        textColor=array.getColor(R.styleable.LoadingView_textColor,Color.BLACK);

        array.recycle();


    }

    /**
     * 测量模式         表示意思
     * UNSPECIFIED      父容器没有对当前的控件有任何的限制，
     * 当前的View可以任意的取值
     * EXACTLY        当前的尺寸就是View应该的十寸
     * AT_MOST         当前的尺寸是View能取得最大值
     *
     * @param defaultSize
     * @param measureSpec
     * @return
     */
    private int getViewSize(int defaultSize,int measureSpec){
        int viewSize=defaultSize;

        int mode=MeasureSpec.getMode(measureSpec);
        int size=MeasureSpec.getSize(measureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                viewSize=defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                viewSize=size;

                break;
            case MeasureSpec.EXACTLY:
                viewSize=size;
                break;
        }
        return viewSize;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth=getViewSize(100,widthMeasureSpec);
        mHeight=getViewSize(100,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x=mWidth/2;
        int y=mHeight/2;
        int r=x-5;
        for (int i = 0; i < lines; i++) {

            //绘制下层的菊花
            canvas.drawLine(x,y-r,x,y-r+lineLength,bgPaint);
            canvas.rotate(360/lines,x,y);

        }

        //获取需要绘制多少个刻度
        int count=(progress*lines)/max;

        //绘制中间的文字进度
        canvas.drawText((progress*100)/max+"%",x,y+5,textPaint);

        //绘制上层的菊花，也就是进度
        canvas.rotate(360/lines,x,y);

        for(;count>0;count--){
            canvas.drawLine(x,y-r,x,y-r+lineLength,bfPaint);
            canvas.rotate(360/lines,x,y);
        }

    }

    /**
     * 为进度设置动画
     * ValueAnimator是整个属性动画机制当中最核心的一个类，属性动画的运行机制是通过不断地对值进行操作来实现的，
     * 而初始值和结束值之间的动画过渡就是由ValueAnimator这个类来负责计算的。
     * 它的内部使用一种时间循环的机制来计算值与值之间的动画过渡，
     * 我们只需要将初始值和结束值提供给ValueAnimator，并且告诉它动画所需运行的时长，
     * 那么ValueAnimator就会自动帮我们完成从初始值平滑地过渡到结束值这样的效果。
     * @param start  开始值
     * @param current 结束值
     * @param duration  时长
     */
    public void startAnimation(int start,int current,int duration){

        ValueAnimator progressAnimator=ValueAnimator.ofInt(start,current);
        progressAnimator.setDuration(duration);
        progressAnimator.setTarget(progress);
        progressAnimator.setInterpolator(new BounceInterpolator());
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress= (int) animation.getAnimatedValue();
                invalidate();
            }
        });

    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }
}
