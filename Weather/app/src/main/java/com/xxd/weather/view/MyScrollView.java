package com.xxd.weather.view;

/**
 * 滑动事件的监听，获取scrollView的位置信息
 */

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by j on 2017/4/22.
 */

public class MyScrollView extends ScrollView {
    private OnScrollChangedListener onScrollChangedListener;

    private OnScrollTouchEventListener onScrollTouchEventListener;

    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener){
        this.onScrollChangedListener = onScrollChangedListener;
    }

    public void setOnScrollTouchEventListener(OnScrollTouchEventListener onScrollTouchEventListener){
        this.onScrollTouchEventListener = onScrollTouchEventListener;
    }


    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("adsfasdfasdfasd", "myScrollViewPosY" + "");
        /*if(onScrollTouchEventListener != null){
            onScrollTouchEventListener.onScrollTouchEvent(ev);
        }*/
        return super.onTouchEvent(ev);
    }


    /**
     *
     * 滚动的回调接口
     *
     * @author xiaanming
     *
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         * 				、
         */
        void onScroll(int scrollY);
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY){
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        if(onScrollChangedListener != null)
            onScrollChangedListener.onScrollChanged(this,scrollX,scrollY,oldScrollX, oldScrollY);
    }


    public interface OnScrollChangedListener{
        void onScrollChanged(ScrollView scrollView,int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }

    public interface OnScrollTouchEventListener{
        void onScrollTouchEvent(MotionEvent ev);
    }

}
