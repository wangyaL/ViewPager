package com.example.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 
 * @author WangYaL
 * @version 2015年4月27日 下午2:26:02
 */
public class MyAdapter extends PagerAdapter{
    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;
    private int imgSize;
    
    /**
     * @param mImageViews
     */
    public MyAdapter(ImageView[] mImageViews) {
        super();
        this.mImageViews = mImageViews;
        imgSize = mImageViews.length;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(mImageViews[position % imgSize]);
//        ((ViewPager)container).removeAllViews();
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//    	return super.instantiateItem(container, position);
    	
    	View v = mImageViews[position % imgSize];
    	ViewPager parent = (ViewPager) v.getParent();
        if (parent != null) {
        	Log.d("=====", "==parent不为空=="+(position % imgSize));
			parent.removeAllViews();
			container.addView(v);
        } else {
        	Log.d("=====", "==parent为空=="+(position % imgSize));
        	container.addView(v);
		}
    	
    	return v;
    }

    /**
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
     */
//    @Override
//    public Object instantiateItem(View container, final int position) {
//
////        ViewPager parent = (ViewPager) mImageViews[position % imgSize].getParent();
////        if (parent != null) {
////			parent.removeAllViews();
////		}
////    	((ViewPager)container).addView(mImageViews[position % imgSize], 0);
////        mImageViews[position % imgSize].setOnClickListener(new OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // TODO 指尖家政广告点击事件
////                Log.d("==", "==点击了图片=="+(position % imgSize));
////            }
////        });
////        return mImageViews[position % imgSize];
//        
//    	View v = mImageViews[position % imgSize];
//    	ViewPager parent = (ViewPager) v.getParent();
//        if (parent != null) {
//        	Log.d("=====", "==parent不为空=="+(position % imgSize));
//			parent.removeAllViews();
//			((ViewPager) container).addView(v);
//        } else {
//        	Log.d("=====", "==parent为空=="+(position % imgSize));
//        	((ViewPager) container).addView(v);
//		}
//    	
//    	return v;
//    }


    @Override 
    public int getItemPosition(Object object) { 

        return super.getItemPosition(object); 
    } 

}
