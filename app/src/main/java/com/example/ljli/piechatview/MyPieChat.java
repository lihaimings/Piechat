package com.example.ljli.piechatview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by LJli on 2019/4/1.
 * 自定义饼状图
 */

public class MyPieChat extends View {

    private int[] mColors={Color.RED,Color.BLACK,Color.BLUE
    ,Color.GREEN};
    private float mStartAngle=0; //初始化的角度
    private ArrayList<PieData> mData;//数据
    private int mWidth,mHeight; //宽高
    private Paint mPaint=new Paint();
    public MyPieChat(Context context){
        super(context);
    }

    public MyPieChat(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL); //填充模式
        mPaint.setAntiAlias(true) ; //设置抗锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(intitSize(widthMeasureSpec),intitSize(heightMeasureSpec));
    }

    public  int intitSize(int viewSize){
        int result=0;
        int size=MeasureSpec.getSize(viewSize);
        int mode=MeasureSpec.getMode(viewSize);
        int defalus=200;
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                result=defalus;
                break;
            case MeasureSpec.EXACTLY:
                result=size;
                break;
            case MeasureSpec.AT_MOST:
                result=Math.min(defalus,size);
                break;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mData==null){
            return; //没有数据,不绘画
        }
        float currentStartAngle=mStartAngle;
        canvas.translate(mWidth/2,mHeight/2);//将画布移到中心
        float r=(float)(Math.min(mWidth,mHeight)/2*0.8); //设置矩形坐标，一半的0.8
        RectF rect=new RectF(-r,-r,r,r);
        for(int i=0;i<mData.size();i++){  //遍历数据，绘制圆弧
            PieData pie=mData.get(i);
            mPaint.setColor(pie.getColor());  //在java bean中调用颜色
            canvas.drawArc(rect,currentStartAngle,pie.getAngle(),true,mPaint); //绘制圆弧
            currentStartAngle+=pie.getAngle();
        }
    }

    // 赋值起始角度
    public  void setmStartAngle(int mStartAngle){
        this.mStartAngle=mStartAngle;
        invalidate();  //刷新
    }

    //赋值数据
    public void setData(ArrayList<PieData> mData){
        this.mData=mData;
        initData(mData);
        invalidate();
    }

    //
    private void initData(ArrayList<PieData> mData){
        if(mData==null || mData.size()==0){
            return;  //如果没有数据结束
        }
        float sumValue=0;
        for(int i=0;i<mData.size();i++){  //遍历数据，开始给数据赋值一些特性
            PieData pie=mData.get(i);
            sumValue+=pie.getValue();  //
            int j=i%mColors.length; //不超过mColor的长度
            pie.setColor(mColors[j]);
        }
        float sumAngle=0;
        for(int i=0;i<mData.size();i++){ //遍历数据给数据设置角度和百分比
            PieData pie=mData.get(i);
            float percentage=pie.getValue()/sumValue;  //计算百分比
            float angle=percentage*360; //计算角度
            pie.setPercentage(percentage);
            pie.setAngle(angle);
            sumAngle+=angle;
        }

    }
}
