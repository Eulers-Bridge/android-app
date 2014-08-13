package com.eulersbridge.isegoria;

import java.lang.reflect.Field;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ElectionMasterFragment extends SherlockFragment implements TabListener {
	private View rootView;
	private FragmentManager fragmentManager = null;
	private ViewGroup container = null;
	
	private ElectionFragment electionFragment;
	private CandidateFragment candidateFragment;
	
	public ElectionMasterFragment() {
		electionFragment = new ElectionFragment();
		candidateFragment = new CandidateFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		((SherlockFragmentActivity) getActivity()).setTitle("Isegoria");
		this.container = container;
		
		fragmentManager = getChildFragmentManager();

		((SherlockFragmentActivity) getActivity()).getSupportActionBar().removeAllTabs();
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().addTab(
				((SherlockFragmentActivity) getActivity()).getSupportActionBar().newTab()
	            .setText("Election")
	            .setTabListener(this));
		((SherlockFragmentActivity) getActivity()).getSupportActionBar().addTab(
				((SherlockFragmentActivity) getActivity()).getSupportActionBar().newTab()
	            .setText("Candidates")
	            .setTabListener(this));
	    
		ActionBar bar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3C7EC9")));
		bar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#313E4D")));
		bar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#313E4D")));
		
		return rootView;
	}
	
	@Override
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
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    	if(tab.getText().equals("Election")) {
    		ft.replace(R.id.content_frame, electionFragment);
    	}
    	else if(tab.getText().equals("Candidates")) {
    		ft.replace(R.id.content_frame, candidateFragment);
    	}
    }
	
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	if(tab.getText().equals("Election")) {
    		//ft.remove(electionFragment);
    	}
    	else if(tab.getText().equals("Candidates")) {
    		//ft.remove(candidateFragment);
    	}
    }

}
