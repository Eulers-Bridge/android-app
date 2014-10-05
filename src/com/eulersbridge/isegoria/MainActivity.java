package com.eulersbridge.isegoria;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity {
	private Fragment mContent;
	private Isegoria application;
	
	public MainActivity(){
		super(R.string.app_name);	
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		application = (Isegoria) getApplicationContext();
		
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new MainView();
		
		setContentView(R.layout.content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent).commit();
		
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new SlidingMenuItems()).commit();
		
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setSlidingActionBarEnabled(true);
		
		switchContent(new LoginScreenFragment());
	}
	
	public Isegoria getIsegoriaApplication() {
		return application;
	}
	
	public void signupClicked(View view) {
		switchContent(new UserSignupFragment());
	}
	
	public void userSignupNext(View view) {
		switchContent(new UserConsentAgreementFragment());
	}
	
	public void userConsentNext(View view) {
		switchContent(new FeedFragment());
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}
}