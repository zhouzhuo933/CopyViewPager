package com.zhouzhuo.customview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zhouzhuo on 2018/1/5.
 */

public class HorizontalScrollViewEx2 extends ViewGroup{
    private static final String TAG = "HorizontalScrollViewEx2";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;
    //分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    //分别记录上次滑动的坐标（onInterceptTouchEvent）
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller ;
    private VelocityTracker mVelcoityTracker;



    public HorizontalScrollViewEx2(Context context) {
        super(context);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("zhouzhuo","onTouchEvent action:"+event.getAction());
        mVelcoityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //手指按下去的时候,如果动画没有结束就结束动画
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            //在手指滑动的过程中，慢慢滑动界面
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d("zhouzhuo","move,deltaX:"+deltaX +" deltaY:"+deltaY);
                scrollBy(-deltaX,0);
                break;
            //在手指抬起后，才正式确定是滑到哪个界面去
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX/mChildWidth;
                Log.d("zhouzhuo","current index:"+scrollToChildIndex);
                mVelcoityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelcoityTracker.getXVelocity();
                if(Math.abs(xVelocity)>=50){
                    mChildIndex = xVelocity >0?mChildIndex - 1:mChildIndex+1;
                }else {
                    mChildIndex = (scrollX + mChildWidth/2)/mChildWidth;
                }
                mChildIndex = Math.max(0,Math.min(mChildIndex,mChildrenSize-1));
                int dx = mChildIndex* mChildWidth - scrollX;
                smoothScrollBy(dx,0);
                mVelcoityTracker.clear();
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    //调用Scroll的方法后,一定要用invalidate()刷新
    private void smoothScrollBy(int dx,int dy){
        mScroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelcoityTracker = VelocityTracker.obtain();
    }

    public HorizontalScrollViewEx2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        int childCount = getChildCount();
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpaceMode = MeasureSpec.getMode(heightMeasureSpec);
        if(childCount == 0){
            setMeasuredDimension(0,0);
        }else if(heightSpaceMode == MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize,measureHeight);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth()*childCount;
            setMeasuredDimension(measureWidth,heightSpaceSize);
        }else {
            View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth()*childCount;
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measureWidth,measureHeight);
        }

    }

    //这种自定义的View,如果不重新layout的话，由于都是匹配父窗体的，第二个肯定把第一个盖住了
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("zhouzhuo","width:"+getWidth());
        int childLeft = 0;
        int childCount = getChildCount();
        mChildrenSize = childCount;
        for(int i=0;i<childCount;i++){
            View view = getChildAt(i);
            //一定要考虑GONE的情况
            if(view.getVisibility() !=View.GONE){
                int childWidth = view.getMeasuredWidth();
                mChildWidth = childWidth;
                view.layout(childLeft,0,childLeft+childWidth,view.getMeasuredHeight());
                childLeft+=childWidth;
            }
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        mVelcoityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
