package com.eulersbridge.isegoria;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserSignupFragment extends SherlockFragment implements OnItemSelectedListener {
	private View rootView;
	private ArrayList<String> countries;
	private ArrayList<CountryInfo> countryObjects;
	private ArrayList<String> institutions;
	private ArrayAdapter<String> spinnerArrayAdapter;
	private ArrayAdapter<String> spinnerInstitutionArrayAdapter;
	private ArrayAdapter<String> spinnerGenderArrayAdapter;
	private ArrayAdapter<String> spinnerYearOfBirthArrayAdapter;
	private Isegoria isegoria;
	
	public UserSignupFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   
		rootView = inflater.inflate(R.layout.user_signup_fragment, container, false);
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		countries = new ArrayList<String>();
		countryObjects = new ArrayList<CountryInfo>();
		
		isegoria = (Isegoria) getActivity().getApplication();
		isegoria.setCountryObjects(countryObjects);
        Network network = new Network(isegoria);
        isegoria.setNetwork(network);
        network.getGeneralInfo(this);
        
        Spinner spinner = (Spinner) rootView.findViewById(R.id.country);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
 
        Spinner spinnerInstitution = (Spinner) rootView.findViewById(R.id.institution);
        spinnerInstitutionArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        spinnerInstitutionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstitution.setAdapter(spinnerInstitutionArrayAdapter);
        
        Spinner spinnerGender = (Spinner) rootView.findViewById(R.id.gender);
        spinnerGenderArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        spinnerGenderArrayAdapter.add("Male");
        spinnerGenderArrayAdapter.add("Female");
        spinnerGenderArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(spinnerGenderArrayAdapter);
        
        Spinner spinnerYearOfBirth = (Spinner) rootView.findViewById(R.id.yearOfBirth);
        spinnerYearOfBirthArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item);
        for(int i=1900; i<=2014; i++) {
        	spinnerYearOfBirthArrayAdapter.add(String.valueOf(i));
        }
        spinnerYearOfBirthArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYearOfBirth.setAdapter(spinnerYearOfBirthArrayAdapter);
        
        CountryInfo countryInfo = new CountryInfo("Select Country"); 
        addCountry(countryInfo);
        spinnerInstitutionArrayAdapter.add("Select Institution");
		
		return rootView;
	}
	
	public void addCountry(final CountryInfo countryInfo) {
		getActivity().runOnUiThread(new Runnable() {
		     @Override
		     public void run() {
		    	 spinnerArrayAdapter.add(countryInfo.getCountry());
		    	 countries.add(countryInfo.getCountry());
		    	 countryObjects.add(countryInfo);
			 }
		});
	}
	
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
    	String selectedCountry = (String) parent.getSelectedItem();
    	spinnerInstitutionArrayAdapter.clear();
    	
    	for(int i=0; i<countryObjects.size(); i++) {
    		CountryInfo countryInfo = (CountryInfo) countryObjects.get(i);
    		if(selectedCountry.equals(countryInfo.getCountry())) {
    			for(int j=0; j<countryInfo.getInstitutions().size(); j++) {
    				InstitutionInfo currentInstitution = countryInfo.getInstitutions().get(j);
    				spinnerInstitutionArrayAdapter.add(currentInstitution.getInstitution());
    			}
    		}
    	}
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}
