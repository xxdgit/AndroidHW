package com.xxd.weather.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by j on 2017/4/22.
 */

public class CircleViewPager extends ViewPager implements Runnable {

    private boolean autoRunflag = false; //初始化自动循环播放为false
    private ViewPagerDots mViewPagerDots; //点提示

    public CircleViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
        postDelayed(this,3000);//启动自动循环线程
    }


    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            child.measure(widthMeasureSpec,View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height){
                height = h;
            }
        }
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {

        super.setOnPageChangeListener(listener);
    }

    //重写OnPageChangeListener
    class MyOnPageChangeListener implements OnPageChangeListener{
        private  OnPageChangeListener listener;

        public MyOnPageChangeListener(OnPageChangeListener listener){
            this.listener = listener;
        }
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            listener.onPageScrolled(i,v,i1);
        }

        //实现循环，所以在实例化CircleViewPager时一定要调用setOnPageChangeListener方法才可实现
        @Override
        public void onPageScrollStateChanged(int i) {
            if(i == 0){   //状态经过顺序为1、2、0
                if(getCurrentItem() == 0){
                    setCurrentItem(getAdapter().getCount(),false);
                }else if(getCurrentItem() == getAdapter().getCount() - 1 ){
                    setCurrentItem(1,false);
                }
                if(mViewPagerDots != null){
                    mViewPagerDots.setDots(getTrueCurrentItem());
                }
            }

            listener.onPageScrollStateChanged(i);
        }

        @Override
        public void onPageSelected(int i) {
            listener.onPageSelected(i);
        }
    }

    /**
     * 自动播放设置
     */
    @Override
    public void run(){
        if(autoRunflag){
            if(getCurrentItem() != 0 && getCurrentItem() != getAdapter().getCount()-1){
                setCurrentItem(getCurrentItem()+1,true);
            }
        }
        postDelayed(this,3000);
    }

    /**
     * 自动播放设置
     */
    public void setAutoRunFlag(boolean flag){
        if (autoRunflag != flag){
            autoRunflag = flag;
        }
    }

    /**
     * 实际位置
     */
    public int getTrueCurrentItem(){
        if(getCurrentItem() == 0){
            return getAdapter().getCount()-3;
        }else if(getCurrentItem() == getAdapter().getCount()-1){
            return 0;
        }
        return getCurrentItem()-1;
    }

    public void setDefaultPagerDots(ViewPagerDots mViewPagerDots){
        this.mViewPagerDots = mViewPagerDots == null ? null : mViewPagerDots;
    }

    /**
     * 实现点接口
     */
    public interface ViewPagerDots {
        void setDots(int index);
    }
}
