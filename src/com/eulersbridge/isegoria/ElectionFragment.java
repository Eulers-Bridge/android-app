package com.eulersbridge.isegoria;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class ElectionFragment extends Fragment implements TabHost.OnTabChangeListener {
	private View rootView;
	private boolean loaded = false;
	private String overviewText = "";
	private String processText = "";
	
	public ElectionFragment() {
		StringBuffer sb = new StringBuffer();
		sb.append("Welcome to the 2014 University of Melbourne Student Union Elections!");
		sb.append("\n\n");
		sb.append("Here, you'll find a host of information on your current student council, the new candidates and the different parties they represent. You'll be able to find"); 
		sb.append("information on your current activities, volunteer opportunities and major campaign events.");
		sb.append("\n\n");
		sb.append("Every day there will be a new poll question from yours peers to help the candidates better understand their consitituents and increase transparency within our elections.");
		sb.append("\n\n");
		sb.append("Reader, Set, Vote!");
		overviewText = sb.toString();
		
		sb = new StringBuffer();
		sb.append("Using Isegoria is simple. You navigate by pressing the touch screen, as you do, you'll learn about the candidates at the University of Melbourne, their policies");
		sb.append(" and how they aggect you.");
		sb.append("\n\n");
		sb.append("You can join events, get friends invovled and express yourself through polls and questions.");
		sb.append("\n\n");
		sb.append("We'll remind you to vote, and show you how and when.");
		sb.append("\n\n");
		sb.append("Thats it");
		sb.append("\n\n");
		sb.append("Let's do this.");
		
		processText = sb.toString();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		rootView = inflater.inflate(R.layout.election_fragment, container, false);
		getActivity().setTitle("Isegoria");
		
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
		    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		    	if(tab.getText().equals("Election")) {
		    		getElectionTabs();
		    	}
		    	else if(tab.getText().equals("Candidates")) {
		    		getCandidatesTabs();
		    	}		    	
		    }
		
		    public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
	
		    }
		
		    public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
	
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
	    
	    
		
		return rootView;
	}
	
	@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.setBackgroundColor(Color.parseColor("#000000"));
		tabHost.setOnTabChangedListener(this);
		setLoaded(true);
		
		getElectionTabs();
		tabHost.setCurrentTab(1);
		tabHost.setCurrentTab(0);
	}
	
	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	public void getElectionTabs() {
		if(!isLoaded())
			return;
		
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		
		for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++)  {
			tabHost.getTabWidget().removeAllViews();
		}

		TabSpec spec = tabHost.newTabSpec("tab1");
		spec.setIndicator("Overview");
		spec.setContent(R.id.tabTextViewContent);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("tab2");
		spec.setIndicator("Process");
		spec.setContent(R.id.tabTextViewContent);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("tab3");
		spec.setIndicator("Positions");
		spec.setContent(R.id.tabTextViewContent);
		tabHost.addTab(spec);
		
	    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)  {
	        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
	        tv.setTextColor(Color.parseColor("#FFFFFF"));
	    }
	    
		TextView textView = (TextView) rootView.findViewById(R.id.tabTextViewContent);
	    textView.setText(overviewText);
	    
		tabHost.setCurrentTab(1);
		tabHost.setCurrentTab(0);
	}
	
	public void getCandidatesTabs() {
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		for(int i=0; i<tabHost.getTabWidget().getChildCount(); i++)  {
			tabHost.getTabWidget().removeAllViews();
		}
		
		TabSpec spec = tabHost.newTabSpec("tab1");
		spec.setIndicator("Position");
		spec.setContent(R.id.tabTextViewContent);
		tabHost.addTab(spec);
		
		spec = tabHost.newTabSpec("Ticket");
		spec.setIndicator("Ticket");
		spec.setContent(R.id.tabTextViewContent);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("All");
		spec.setIndicator("All");
		spec.setContent(R.id.tabTextViewContent);
		tabHost.addTab(spec);
		
	    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)  {
	        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
	        tv.setTextColor(Color.parseColor("#FFFFFF"));
	    }
	    
	    tabHost.setCurrentTab(0);
	}

	public void onTabChanged(String tabId) {
		TabHost tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
		int currentTabIndex = tabHost.getCurrentTab();
		
		if(currentTabIndex == 0) {
			TextView textView = (TextView) rootView.findViewById(R.id.tabTextViewContent);
		    textView.setText(overviewText);
		}
		if(currentTabIndex == 1) {
			TextView textView = (TextView) rootView.findViewById(R.id.tabTextViewContent);
		    textView.setText(processText);
		}
		if(currentTabIndex == 2) {
		    
		}
	}
}