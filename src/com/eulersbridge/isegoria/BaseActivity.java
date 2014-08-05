package com.eulersbridge.isegoria;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity {
	
	private int mTitleRes;
	protected SlidingMenuItems mFrag;
	
	public BaseActivity(int titleRes){
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(mTitleRes);
		
		setBehindContentView(R.layout.menu_frame);
		android.support.v4.app.FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		mFrag = new SlidingMenuItems();
		ft.replace(R.id.menu_frame, mFrag);
		ft.commit();
		
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidth(15);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffset(60);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case android.R.id.home:
				toggle();
				return true;
			}
		return onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public class BasePagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> mFragments = new ArrayList<Fragment>();
		private ViewPager mPager;
		
		public BasePagerAdapter(FragmentManager fm, ViewPager vp){
			super(fm);
			mPager = vp;
			mPager.setAdapter(this);
		}
		
		@Override
		public int getCount(){
			return mFragments.size();
		}

		@Override
		public android.support.v4.app.Fragment getItem(int arg0) {
			return null;
		}
	}
}
