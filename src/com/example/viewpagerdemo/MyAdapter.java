package com.example.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 
 * @author WangYaL
 * @version 2015��4��27�� ����2:26:02
 */
public class MyAdapter extends PagerAdapter{
    /**
     * װImageView����
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

    /**
     * ����ͼƬ��ȥ���õ�ǰ��position ���� ͼƬ���鳤��ȡ�����ǹؼ�
     */
    @Override
    public Object instantiateItem(View container, final int position) {
        ((ViewPager)container).addView(mImageViews[position % imgSize], 0);
        mImageViews[position % imgSize].setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ָ�����������¼�
                Log.d("==", "==�����ͼƬ=="+(position % imgSize));
            }
        });
        return mImageViews[position % imgSize];
    }
    
}