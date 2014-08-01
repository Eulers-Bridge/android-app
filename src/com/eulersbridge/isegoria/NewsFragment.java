package com.eulersbridge.isegoria;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsFragment extends Fragment {
	private View rootView;
	
	public NewsFragment() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
	
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		rootView = inflater.inflate(R.layout.news_fragment, container, false);
		getActivity().setTitle("Isegoria");
		
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
		    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	
		    }
		
		    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	
		    }
		
		    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	
		    }
		};
		
		getActivity().getActionBar().removeAllTabs();
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("News")
	            .setTabListener(tabListener));
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("Photos")
	            .setTabListener(tabListener));
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("Events")
	            .setTabListener(tabListener));
	    
		return rootView;
		}
	}