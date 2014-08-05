package com.eulersbridge.isegoria;

import java.util.List;
import java.util.Vector;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PollFragment extends Fragment {
	private View rootView;
	private PagerAdapter pollPagerAdapter;
	
	public PollFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		rootView = inflater.inflate(R.layout.poll_fragment, container, false);
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
		List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(getActivity(), PollVoteFragment.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(), PollVoteFragment.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(), PollVoteFragment.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(), PollVoteFragment.class.getName()));
		
		ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.pollViewPager);
		pollPagerAdapter = new PollPagerAdapter(getChildFragmentManager(), fragments);
		mViewPager.setAdapter(pollPagerAdapter);
		
		return rootView;
	}
}