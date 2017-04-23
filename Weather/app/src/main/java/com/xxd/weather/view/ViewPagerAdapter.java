package com.xxd.weather.view;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by j on 2017/4/23.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;

    public ViewPagerAdapter(List<View> views){
        this.views = views;
    }

    /**
     * 改变adapter的views
     * @param views
     */
    public void changeViews(List<View> views){
        this.views = views;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(views != null){
            return views.size();
        }
        return 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position);
        ((ViewPager)container).removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        ((ViewPager)container).addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}
