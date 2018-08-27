package com.zhy.stickynavlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import com.zhy.stickynavlayout.view.SimpleViewPagerIndicator;


public class MainActivity extends FragmentActivity
{
	private static final String TITLE_1 = "简介",TITLE_2 = "评价",TITLE_3 = "相关";
	private String[] mTitles = new String[] { TITLE_1,TITLE_2,TITLE_3 };
	private SimpleViewPagerIndicator mIndicator;
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private TabFragment[] mFragments = new TabFragment[mTitles.length];

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initDatas();
		initEvents();
	}

	private void initEvents()
	{
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				mIndicator.scroll(position, positionOffset);
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});
		//点击 title 的回调
		mIndicator.setOnTitleClickListener(new SimpleViewPagerIndicator.OnTitleClickListener() {
			@Override
			public void clickTitle(String currentTitle) {
				Log.d("currentTitle--",currentTitle);
				switch (currentTitle){
					case TITLE_1:
						mViewPager.setCurrentItem(0);
						break;
					case TITLE_2:
						mViewPager.setCurrentItem(1);
						break;
					case TITLE_3:
						mViewPager.setCurrentItem(2);
						break;
				}
			}
		});

	}

	private void initDatas()
	{
		mIndicator.setTitles(mTitles);

		for (int i = 0; i < mTitles.length; i++)
		{
			mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
		}

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mTitles.length;
			}

			@Override
			public Fragment getItem(int position)
			{
				return mFragments[position];
			}

		};

		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
	}

	private void initViews()
	{
		mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
		mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
		
		/*
		RelativeLayout ll = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);
		TextView tv = new TextView(this);
		tv.setText("我的动态添加的");
		tv.setBackgroundColor(0x77ff0000);
		ll.addView(tv, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 600));
		*/
	}


}
