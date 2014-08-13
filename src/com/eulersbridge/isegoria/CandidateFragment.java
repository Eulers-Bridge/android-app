package com.eulersbridge.isegoria;

import java.lang.reflect.Field;
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

public class CandidateFragment extends Fragment {
	private View rootView;
	private boolean loaded = false;
	private CandidatePagerAdapter candidatePagerAdapter;
	
	public CandidateFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		rootView = inflater.inflate(R.layout.candidate_fragment, container, false);
		getActivity().setTitle("Isegoria");
		getActivity().getActionBar().setSelectedNavigationItem(1);
		
		List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(getActivity(), ElectionPositionsFragment.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(), CandidateTicketFragment.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(), CandidateAllFragment.class.getName()));

		ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.candidateViewPager);
		candidatePagerAdapter = new CandidatePagerAdapter(getChildFragmentManager(), fragments);
		mViewPager.setAdapter(candidatePagerAdapter);
		
		TabPageIndicator tabPageIndicator = (TabPageIndicator) rootView.findViewById(R.id.tabPageIndicatorCandidate);
		tabPageIndicator.setViewPager(mViewPager);
		tabPageIndicator.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313E4D")));
	    
		return rootView;
	}
	
	public void onDetach() {
	    super.onDetach();

	    try {
	        Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
	        childFragmentManager.setAccessible(true);
	        childFragmentManager.set(this, null);

	    } catch (NoSuchFieldException e) {
	        throw new RuntimeException(e);
	    } catch (IllegalAccessException e) {
	        throw new RuntimeException(e);
	    }
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