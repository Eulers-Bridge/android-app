package com.eulersbridge.isegoria;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class ElectionFragment extends Fragment {
	private View rootView;
	private boolean loaded = false;
	
	public ElectionFragment() {
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		rootView = inflater.inflate(R.layout.election_fragment, container, false);
		getActivity().setTitle("Isegoria");
		
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
		    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
		    	if(tab.getText().equals("Election")) {
		    		getElectionTabs();
		    	}
		    	else if(tab.getText().equals("Candidates")) {
		    		getCandidatesTabs();
		    	}		    	
		    }
		
		    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	
		    }
		
		    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	
		    }
		};
		
		getActivity().getActionBar().removeAllTabs();
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("Election")
	            .setTabListener(tabListener));
	    getActivity().getActionBar().addTab(
	            getActivity().getActionBar().newTab()
	            .setText("Candidates")
	            .setTabListener(tabListener));
	    
	    TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.setBackgroundColor(Color.parseColor("#000000"));
		loaded = true;
		getElectionTabs();
		
		return rootView;
		}
	
	public void getElectionTabs() {
		if(!loaded)
			return;
		
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++)  {
			tabHost.getTabWidget().removeAllViews();
		}

		TabSpec spec = tabHost.newTabSpec("tab1");
		spec.setIndicator("Overview");
		spec.setContent(R.id.tab2);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("tab2");
		spec.setIndicator("Process");
		spec.setContent(R.id.tab2);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("tab3");
		spec.setIndicator("Positions");
		spec.setContent(R.id.tab2);
		tabHost.addTab(spec);
		
	    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)  {
	        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
	        tv.setTextColor(Color.parseColor("#FFFFFF"));
	    }
	    
	    tabHost.setCurrentTab(0);
	}
	
	public void getCandidatesTabs() {
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++)  {
			tabHost.getTabWidget().removeAllViews();
		}
		
		TabSpec spec = tabHost.newTabSpec("tab1");
		spec.setIndicator("Position");
		spec.setContent(R.id.tab2);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("Ticket");
		spec.setIndicator("Ticket");
		spec.setContent(R.id.tab2);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("All");
		spec.setIndicator("All");
		spec.setContent(R.id.tab2);
		tabHost.addTab(spec);
		
	    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)  {
	        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
	        tv.setTextColor(Color.parseColor("#FFFFFF"));
	    }
	    
	    tabHost.setCurrentTab(0);
	}
}