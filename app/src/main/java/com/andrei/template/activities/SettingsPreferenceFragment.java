package com.andrei.template.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrei.template.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.regex.Pattern;


public class SettingsPreferenceFragment extends PreferenceFragment {


    private ProgressDialog ringProgressDialog;
    private File exportFile;
    private String separator;


    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(R.color.white));
        addPreferencesFromResource(R.xml.settings);


        Preference buttonfeedback = (Preference) findPreference(getString(R.string.send_feedback));
        buttonfeedback.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "your_email@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, SettingsPreferenceFragment.this.getString(R.string.app_name) + " Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Write your feedback here...");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));

                return true;
            }
        });


        return view;
    }


}