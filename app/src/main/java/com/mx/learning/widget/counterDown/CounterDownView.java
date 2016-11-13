package com.mx.learning.widget.counterDown;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.mx.learning.R;

/**
 * Created by Administrator on 2016/11/13.
 */

/**
 * 倒计时View
 */
public class CounterDownView extends View {
    public static final String TAG=CounterDownView.class.getSimpleName();

    public static final int BACKGROUND_COLOR=0x50555555;
    public static final float BORDER_WIDTH=15F;
    public static final int BORDER_COLOR=0xff6ADBFE;
    public static final String TEXT="跳过广告";
    public static final float TEXT_SIZE=50F;
    public static final int TEXT_COLOR=0xFFFFFF;

    private int backgroundColor;
    private float borderWidth;
    private String text;
    private int borderColor;
    private int textColor;
    private float textSize;

    private Paint circlePaint;
    private TextPaint textPaint;
    private Paint borderPaint;
    private float progress=135;
    private StaticLayout staticLayout;
    public CountDownListener mCountDownListener;

    public void setCountDownListener(CountDownListener countDownListener) {
        mCountDownListener = countDownListener;
    }

   public interface  CountDownListener{
        void onStartCountDown();
        void onFinishCountDown();
    }


    public CounterDownView(Context context) {
        this(context,null);
    }

    public CounterDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.CounterDownView);
        backgroundColor=ta.getColor(R.styleable.CounterDownView_background_color,BACKGROUND_COLOR);
        borderWidth=ta.getDimension(R.styleable.CounterDownView_border_width,BORDER_WIDTH);
        borderColor=ta.getColor(R.styleable.CounterDownView_border_color,borderColor);
        text=ta.getString(R.styleable.CounterDownView_text);
        if(text==null){
            text=TEXT;
        }
        textSize=ta.getDimension(R.styleable.CounterDownView_text_size,TEXT_SIZE);
        textColor=ta.getColor(R.styleable.CounterDownView_text_color,TEXT_COLOR);
        ta.recycle();

        init();

    }

    private void init() {

        circlePaint=new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setDither(true);
        circlePaint.setColor(backgroundColor);
        circlePaint.setStyle(Paint.Style.FILL);


        textPaint=new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        borderPaint=new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setDither(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);


        int textWidth= (int) textPaint.measureText(text.substring(0,(text.length()+1)/2));
        staticLayout=new StaticLayout(text,textPaint,textWidth, Layout.Alignment.ALIGN_NORMAL,1F,0,false);



    }

    public void start(){

        if(mCountDownListener!=null){
            mCountDownListener.onStartCountDown();
        }

        CountDownTimer countDownTimer=new CountDownTimer(3600,36) {
            @Override
            public void onTick(long millisUntilFinished) {

                progress=((3600-millisUntilFinished)/3600f)*360;
                invalidate();
            }

            @Override
            public void onFinish() {
                progress=360;
                invalidate();
                if(mCountDownListener!=null){
                    mCountDownListener.onFinishCountDown();
                }



            }
        }.start();



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);

        int height=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        if(widthMode!=MeasureSpec.EXACTLY){
            width=staticLayout.getWidth();
        }

        if(heightMode!=MeasureSpec.EXACTLY){
            height=staticLayout.getHeight();
        }
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width=getMeasuredWidth();
        int height=getMeasuredHeight();

        int min=Math.min(width,height);

        //画底盘
        canvas.drawCircle(width/2,height/2,min/2,circlePaint);

        //画边框
        RectF rectF;

        if(width>height){
            rectF=new RectF(width/2-min/2+borderWidth/2,0+borderWidth/2,width/2+min/2-borderWidth/2,height/2-borderWidth/2);
        }else{
            rectF=new RectF(borderWidth/2,height/2-min/2+borderWidth/2,width-borderWidth/2,height/2-borderWidth/2+min/2);
        }

        canvas.drawArc(rectF,-90,progress,false,borderPaint);

        //画居中的文字
        canvas.translate(width/2,height-staticLayout.getHeight()/2);
        staticLayout.draw(canvas);



    }
}
