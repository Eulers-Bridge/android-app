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
import android.widget.TextView;

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
	
	public void login(View v) {
		TextView userNameField = (TextView) findViewById(R.id.username);
		TextView passwordField = (TextView) findViewById(R.id.password);
		
		application.setUsername(userNameField.getText().toString());
		application.setPassword(passwordField.getText().toString());
		
		application.login();
		
		switchContent(new FeedFragment());
	}
	
	public void userSignupNext(View view) {
		TextView firstNameField = (TextView) findViewById(R.id.firstName);
		TextView lastNameField = (TextView) findViewById(R.id.lastName);
		TextView universityEmailField = (TextView) findViewById(R.id.universityEmail);
		TextView newPasswordField = (TextView) findViewById(R.id.newPassword);
		TextView confirmNewPasswordField = (TextView) findViewById(R.id.confirmNewPassword);
		TextView countryField = (TextView) findViewById(R.id.country);
		TextView institutionField = (TextView) findViewById(R.id.institution);
		
		String firstName = firstNameField.getText().toString();
		String lastName = lastNameField.getText().toString(); 
		String email = universityEmailField.getText().toString();
		String password = newPasswordField.getText().toString(); 
		String confirmPassword = confirmNewPasswordField.getText().toString(); 
		String country = countryField.getText().toString();
		String institution = institutionField.getText().toString();
		
		application.getNetwork().signup(firstName, lastName, email, password, confirmPassword, country, institution);
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