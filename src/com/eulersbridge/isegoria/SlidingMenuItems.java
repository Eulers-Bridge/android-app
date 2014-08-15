package com.eulersbridge.isegoria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

public class SlidingMenuItems extends ListFragment {
	
	Fragment feedFragment = new FeedFragment();
	Fragment electionFragment = new ElectionMasterFragment();
	Fragment pollFragment = new PollFragment();
	Fragment voteFragment = new VoteFragment();
	Fragment profileFragment = new ProfileFragment();
	Fragment userSettingsFragment = new UserSettingsFragment();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] items = getResources().getStringArray(R.array.menu_array);
		SlidingArrayAdapter itemAdapter = new SlidingArrayAdapter(getActivity(), 
				R.layout.sliding_menu_item, android.R.id.text1, items);
		setListAdapter(itemAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = feedFragment;
			break;
		case 1:
			newContent = electionFragment;
			break;
		case 2:
			newContent = pollFragment;
			break;
		case 3:
			newContent = voteFragment;
			break;
		case 4:
			newContent = profileFragment;
			break;
		case 5:
			newContent = userSettingsFragment;
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		}
	}
}