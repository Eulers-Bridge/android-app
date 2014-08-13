package com.eulersbridge.isegoria;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class FeedFragment extends android.support.v4.app.Fragment implements TabListener {
	private View rootView;
	private NewsFragment newsFragment = null;
	private PhotosFragment photosFragment = null;
	private EventsFragment eventsFragment = null;
	private FragmentManager fragmentManager = null;
	private ViewGroup container = null;
	
	public FeedFragment () {
		newsFragment = new NewsFragment();
		photosFragment = new PhotosFragment();
		eventsFragment = new EventsFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getActivity().setTitle("Isegoria");
		this.container = container;
		
		//rootView = inflater.inflate(R.layout.news_fragment, container, false);
		fragmentManager = getFragmentManager();

		((SherlockFragmentActivity) getActivity()).getSupportActionBar().removeAllTabs();
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().addTab(
				((SherlockFragmentActivity) getActivity()).getSupportActionBar().newTab()
	            .setText("News")
	            .setTabListener(this));
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().addTab(
				((SherlockFragmentActivity) getActivity()).getSupportActionBar().newTab()
	            .setText("Photos")
	            .setTabListener(this));
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().addTab(
				((SherlockFragmentActivity) getActivity()).getSupportActionBar().newTab()
	            .setText("Events")
	            .setTabListener(this));
	    
		ActionBar bar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3C7EC9")));
		bar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#313E4D")));
		bar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#313E4D")));
		
		return rootView;
	}
	
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    	if(tab.getText().equals("News")) {
    		ft.replace(R.id.content_frame, newsFragment);
    	}
    	else if(tab.getText().equals("Photos")) {
    		ft.replace(R.id.content_frame, photosFragment);
    	}
    	else if(tab.getText().equals("Events")) {
    		ft.replace(R.id.content_frame, eventsFragment);
    	}
    }
	
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	if(tab.getText().equals("News")) {
    		ft.remove(newsFragment);
    	}
    	else if(tab.getText().equals("Photos")) {
    		ft.remove(photosFragment);
    	}
    	else if(tab.getText().equals("Events")) {
    		ft.remove(eventsFragment);
    	}
    }
}