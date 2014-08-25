package com.ultimatetemplateandroidapp.app;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;


public class MainActivity extends ListActivity {
    private MainActivity mContext;

    private ListView list;
    private MainArrayAdapter main_adapter;
    private EditText editText_main_search;
    private ArrayList<Track> mArrayAdapter = new ArrayList<Track>();
    private SharedPreferences prefs;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);



        mContext = MainActivity.this;
        editText_main_search = (EditText) findViewById(R.id.editText_main_search);
        prefs = getSharedPreferences("PREFS", 0);


        list = getListView();

        //------ dummy data ------
        Track mTrak = new Track();
        mTrak.setTitle("Title #1");
        mTrak.setLocation("Location One");
        mTrak.setGenre("Dummy data 1");
        mTrak.setArtist("Dummy data 1");
        mTrak.setBitrate("Dummy data 1");
        mTrak.setDuration("12345");
        mTrak.setFilename("Dummy data 1");
        mArrayAdapter.add(mTrak);

        mTrak = new Track();
        mTrak.setTitle("Title #2");
        mTrak.setLocation("Location One");
        mTrak.setGenre("Dummy data 2");
        mTrak.setArtist("Dummy data 2");
        mTrak.setBitrate("Dummy data 2");
        mTrak.setDuration("123456");
        mTrak.setFilename("Dummy data 2");
        mArrayAdapter.add(mTrak);

        mTrak = new Track();
        mTrak.setTitle("Title #3");
        mTrak.setLocation("Location One");
        mTrak.setGenre("Dummy data 3");
        mTrak.setArtist("Dummy data 3");
        mTrak.setBitrate("Dummy data 3");
        mTrak.setDuration("1234567");
        mTrak.setFilename("Dummy data 3");
        mArrayAdapter.add(mTrak);


        main_adapter = new MainArrayAdapter(mContext, mArrayAdapter);

        setListAdapter(main_adapter);


        editText_main_search.addTextChangedListener(new TextWatcher() {
                                                        @Override
                                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                        }

                                                        @Override
                                                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                            String search_txt = s.toString().trim().toLowerCase();

                                                            ArrayList<Track> tempArrayAdapter = new ArrayList<Track>();
                                                            for (int i = 0; i < mArrayAdapter.size(); i++) {

                                                                if (mArrayAdapter.get(i).getFilename().toLowerCase().contains(search_txt)) {
                                                                    tempArrayAdapter.add(mArrayAdapter.get(i));
                                                                }
                                                            }

                                                            if (tempArrayAdapter.size() > 0) {
                                                                main_adapter = new MainArrayAdapter(mContext, tempArrayAdapter);
                                                                setListAdapter(main_adapter);
                                                                main_adapter.notifyDataSetChanged();

                                                            }
                                                        }


                                                        @Override
                                                        public void afterTextChanged(Editable s) {

                                                        }
                                                    }
        );


        list.setOnItemClickListener(new

                                            AdapterView.OnItemClickListener() {

                                                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {


                                                    Object item = getListView().getItemAtPosition(arg2);
                                                    Track tempTrack = (Track) item;

                                                    Log.v("You clicked", tempTrack.artist);


                                                }
                                            }
        );


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        boolean has_rated = false;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_close:

                SharedPreferences prefs = mContext.getSharedPreferences("HasRated", 0);
                has_rated = prefs.getBoolean("rated", false);

                if (!has_rated) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:

                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("https://play.google.com/store/apps/details?id=" + Constants.package_name));
                                    startActivity(browserIntent);

                                    SharedPreferences prefs = mContext.getSharedPreferences("HasRated", 0);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean("rated", true).commit();

                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    finish();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Are you happy with the app ?").setPositiveButton("Yes and give rating", dialogClickListener)
                            .setNegativeButton("Later", dialogClickListener).show();

                } else {
                    finish();
                }

                break;
            case R.id.menu_info:

                Intent info_activity = new Intent(mContext, InfoActivity.class);
                startActivity(info_activity);

                break;


            case R.id.menu_settings:

                Intent lib_set = new Intent(mContext, SettingsActivity.class);
                startActivity(lib_set);

                break;


            case R.id.menu_more:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=" + Constants.dev_name));
                startActivity(browserIntent);
                break;

            case R.id.menu_share:

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = getString(R.string.app_name) + " https://play.google.com/store/apps/details?id=" + Constants.package_name;
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Choose sharing method"));

                break;

        }
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onBackPressed() {


        SharedPreferences prefs = mContext.getSharedPreferences("HasRated",
                0);
        boolean has_rated = prefs.getBoolean("rated", false);

        if (!has_rated) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:

                            Intent browserIntent = new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=" + Constants.package_name));
                            startActivity(browserIntent);

                            SharedPreferences prefs = mContext
                                    .getSharedPreferences("HasRated", 0);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("rated", true).commit();

                            break;

                        case DialogInterface.BUTTON_NEGATIVE:


                            finish();
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(
                    "Are you happy with the app ?")
                    .setPositiveButton("Yes, I'll give you",
                            dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        } else {


            finish();


        }


    }


}


