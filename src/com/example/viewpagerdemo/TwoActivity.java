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
	 * �ײ����
	 */
	private ViewGroup dotGroup;
	/**
	 * װ����ImageView����
	 */
	private ImageView[] tips;
	
	/**
	 * װImageView����
	 */
	private ImageView[] mImageViews;
	
	/**
	 * ͼƬ��Դid
	 */
	private int[] imgIdArray ;
	private int currentIndex;       // ��¼��ǰѡ��λ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dotGroup = (ViewGroup)findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		
		//����ͼƬ��ԴID
//		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04,
//				R.drawable.item05,R.drawable.item06, R.drawable.item07, R.drawable.item08};
		//�����������ʱ��ͱ���
		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02};
		//�����������ʱ���û������
//		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item01, R.drawable.item02, R.drawable.item01, R.drawable.item02};
//		imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03};
		
		initTips(imgIdArray.length);
		
		initImages(imgIdArray.length);
		
		//����Adapter
		viewPager.setAdapter(new MyAdapter(mImageViews));
		//���ü�������Ҫ�����õ��ı���
		viewPager.setOnPageChangeListener(pageChangeListener);
		//����ViewPager��Ĭ����, ����Ϊ���ȵ�100���������ӿ�ʼ�������󻬶�
		viewPager.setCurrentItem((mImageViews.length) * 100);
		
	}
	/**
	 * ��ʼ���ײ�
	 * @param pageSize
	 */
	private void initTips(int pageSize){
	    dotGroup.removeAllViews();
	    //�������뵽ViewGroup��
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
	 * ��ʼ��ͼƬ
	 * @param pageSize
	 */
	private void initImages(int pageSize){
	    //��ͼƬװ�ص�������
        mImageViews = new ImageView[pageSize];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imgIdArray[i]);
            mImageViews[i] = imageView;
        }
	}
	
	/**
	 * viewpager�������������õ��ı���
	 */
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
	    @Override
        public void onPageSelected(int arg0) {
            //���������һЩС���������統�㻬������һҳ��ʱ����Ҫ�Ӹ�ͼ�꣬����ɶ��
            // �˷�����ҳ����ת���õ����ã�arg0���㵱ǰѡ�е�ҳ���Position��λ�ñ�ţ���
            // ���õײ�С��ѡ��״̬
	        Log.d("===", "==="+arg0);
	        Log.d("===", "==="+arg0 % mImageViews.length);
            setImageBackground(arg0 % mImageViews.length);
        }
	    
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // �˷�������״̬�ı��ʱ����ã�����arg0�������������״̬��0��1��2����
            // arg0 ==1��ʱ��Ĭʾ���ڻ�����arg0==2��ʱ��Ĭʾ��������ˣ�arg0==0��ʱ��Ĭʾʲô��û����
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // ��ҳ���ڻ�����ʱ�����ô˷������ڻ�����ֹ֮ͣǰ���˷�����һֱ�õ����á��������������ĺ���ֱ�Ϊ��
            //  arg0 :��ǰҳ�棬������������ҳ��
            //  arg1:��ǰҳ��ƫ�Ƶİٷֱ�
            //  arg2:��ǰҳ��ƫ�Ƶ�����λ�� 
        }
    };
	
	/**
	 * ����ѡ�е�tip�ı���
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
