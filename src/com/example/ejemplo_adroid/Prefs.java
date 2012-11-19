package com.example.ejemplo_adroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;



public class Prefs extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
	}
	
}
