package com.zhouzhuo.customview.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by zhouzhuo on 2018/1/4.
 */

public class TestButton extends android.support.v7.widget.AppCompatTextView{
    private static final String TAG = "TestButton";
    private int mScaledTouchSlop;
    //分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    public TestButton(Context context) {
        this(context,null);

    }

    public TestButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.d("zhouzhuo","sts:"+mScaledTouchSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y -mLastY;
                Log.d(TAG,"move,deltaX:"+deltaX+"deltaY:"+deltaY);
                int translationX = (int)ViewHelper.getTranslationX(this)+deltaX;
                int translationY =(int) ViewHelper.getTranslationY(this)+deltaY;
                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }
}
