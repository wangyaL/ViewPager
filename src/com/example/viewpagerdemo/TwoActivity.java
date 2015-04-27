package com.example.viewpagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class TwoActivity extends Activity {
	/**
	 * ViewPager
	 */
	private ViewPager viewPager;
	/**
	 * 底部点点
	 */
	private ViewGroup dotGroup;
	/**
	 * 装点点的ImageView数组
	 */
	private ImageView[] tips;
	
	/**
	 * 装ImageView数组
	 */
	private ImageView[] mImageViews;
	
	/**
	 * 图片资源id
	 */
	private int[] imgIdArray ;
	private int currentIndex;       // 记录当前选中位置

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dotGroup = (ViewGroup)findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		
		//载入图片资源ID
//		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04,
//				R.drawable.item05,R.drawable.item06, R.drawable.item07, R.drawable.item08};
		//用这个滑动的时候就报错
		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02};
		//用这个滑动的时候就没问题了
//		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item01, R.drawable.item02, R.drawable.item01, R.drawable.item02};
//		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03};
		
		initTips(imgIdArray.length);
		
		initImages(imgIdArray.length);
		
		//设置Adapter
		viewPager.setAdapter(new MyAdapter(mImageViews));
		//设置监听，主要是设置点点的背景
		viewPager.setOnPageChangeListener(pageChangeListener);
		//设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
		viewPager.setCurrentItem((mImageViews.length) * 100);
		
	}
	/**
	 * 初始化底部
	 * @param pageSize
	 */
	private void initTips(int pageSize){
	    dotGroup.removeAllViews();
	    //将点点加入到ViewGroup中
        tips = new ImageView[pageSize];
        for(int i=0; i<tips.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LayoutParams(10,10));
            imageView.setPadding(5, 0, 5, 0);
            tips[i] = imageView;
            if(i == 0){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            
            dotGroup.addView(imageView);
        }
	}
	/**
	 * 初始化图片
	 * @param pageSize
	 */
	private void initImages(int pageSize){
	    //将图片装载到数组中
        mImageViews = new ImageView[pageSize];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imgIdArray[i]);
            mImageViews[i] = imageView;
        }
	}
	
	/**
	 * viewpager滚动监听，设置点点的背景
	 */
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
	    @Override
        public void onPageSelected(int arg0) {
            //这里可以做一些小动作，不如当你滑动到那一页的时候你要加个图标，文字啥的
            // 此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position（位置编号）。
            // 设置底部小点选中状态
	        Log.d("===", "==="+arg0);
	        Log.d("===", "==="+arg0 % mImageViews.length);
            setImageBackground(arg0 % mImageViews.length);
        }
	    
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // 此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。其中三个参数的含义分别为：
            //  arg0 :当前页面，及你点击滑动的页面
            //  arg1:当前页面偏移的百分比
            //  arg2:当前页面偏移的像素位置 
        }
    };
	
	/**
	 * 设置选中的tip的背景
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems){
		for(int i=0; i<tips.length; i++){
			if(i == selectItems){
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			}else{
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

}
