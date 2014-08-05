package com.eulersbridge.isegoria;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FeedFragment extends android.support.v4.app.Fragment implements ActionBar.TabListener {
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

		getActivity().getActionBar().removeAllTabs();
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("News")
	            .setTabListener(this));
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("Photos")
	            .setTabListener(this));
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("Events")
	            .setTabListener(this));
	    
		return rootView;
	}
	
    @Override
    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
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
    public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
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