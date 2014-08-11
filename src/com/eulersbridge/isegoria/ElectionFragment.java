package com.eulersbridge.isegoria;

import java.util.List;
import java.util.Vector;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class ElectionFragment extends Fragment {
	private View rootView;
	private boolean loaded = false;
	private ElectionPagerAdapter electionPagerAdapter;
	
	public ElectionFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		rootView = inflater.inflate(R.layout.election_fragment, container, false);
		getActivity().setTitle("Isegoria");
		
		List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(getActivity(), ElectionOverviewFragment.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(), ElectionProcessFragment.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(), ElectionPositionsFragment.class.getName()));

		ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.electionViewPager);
		electionPagerAdapter = new ElectionPagerAdapter(getChildFragmentManager(), fragments);
		mViewPager.setAdapter(electionPagerAdapter);
		
		TabPageIndicator tabPageIndicator = (TabPageIndicator) rootView.findViewById(R.id.tabPageIndicator);
		tabPageIndicator.setViewPager(mViewPager);
		
		tabPageIndicator.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313E4D")));
		
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
		    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		    	if(tab.getText().equals("Election")) {
		  
		    	}
		    	else if(tab.getText().equals("Candidates")) {
		  
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
		
	}
	
	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	
	public void getElectionTabs() {
	
	}
	
	public void getCandidatesTabs() {
	
	}
}