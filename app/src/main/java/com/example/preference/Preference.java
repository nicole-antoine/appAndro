package com.example.preference;

import com.exemple.sqlitedao.R;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Preference extends Activity {
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit(); // validation des modifications apportées
		
	}
	
	public static class MyPreferenceFragment extends PreferenceFragment
	{
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}

}
