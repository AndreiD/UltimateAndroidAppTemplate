package com.andrei.template.activities;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.andrei.template.R;
import com.andrei.template.utils.CroutonStyles;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SettingsActivity mContext;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        prefs = getSharedPreferences("PREFS", 0);


    }

    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Log.d("shared pref changed", "shared pref changed!");

        if (key.equals("prefEmail")) {
            String newValue = sharedPreferences.getString(key, "");
            EditTextPreference email_pref = (EditTextPreference) findPreference("prefEmail");
            email_pref.setSummary(newValue);

        }


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
