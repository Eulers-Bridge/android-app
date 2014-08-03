package com.eulersbridge.isegoria;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PhotosFragment extends Fragment {
	private View rootView;
	
	public PhotosFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		rootView = inflater.inflate(R.layout.photos_fragment, container, false);
		getActivity().setTitle("Isegoria");
		
		return rootView;
	}
}
