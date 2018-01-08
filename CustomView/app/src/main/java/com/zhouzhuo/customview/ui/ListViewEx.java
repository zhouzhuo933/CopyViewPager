package com.zhouzhuo.customview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by zhouzhuo on 2018/1/5.
 */

public class ListViewEx extends ListView{
    private final String TAG = "ListViewEx";

    private HorizontalScrollViewEx2 horizontalScrollViewEx2;
    //分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    public void setHorizontalScrollViewEx2(HorizontalScrollViewEx2 horizontalScrollViewEx2){
        this.horizontalScrollViewEx2 = horizontalScrollViewEx2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                horizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x -mLastX;
                int deltaY = y -mLastY;
                if(Math.abs(deltaX)>Math.abs(deltaY)){
                    horizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }

    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
