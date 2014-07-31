package com.eulersbridge.isegoria;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabsFragment extends Fragment {
    View rootView;

public TabsFragment () {

}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   

	getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	rootView = inflater.inflate(R.layout.news, container, false);
	getActivity().setTitle("Isegoria");
	
	ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	        // show the given tab
	    }
	
	    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	        // hide the given tab
	    }
	
	    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	        // probably ignore this event
	    }
	};

	/*for (int i1 = 0; i1 < 3; i1++) {
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("Tab " + (i1 + 1))
	            .setTabListener(tabListener));
	}*/
	
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