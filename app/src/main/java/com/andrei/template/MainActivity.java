package com.andrei.template;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.andrei.template.utils.CroutonStyles;
import com.astuetz.PagerSlidingTabStrip;
import com.andrei.template.utils.AsyncHttp.RequestHandler;
import com.andrei.template.utils.AsyncHttp.RequestListener;
import com.andrei.template.utils.DUtils;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class MainActivity extends ActionBarActivity {
    private MainActivity mContext;

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private TabsPagerAdapter adapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);

        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);


    }





    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_new_symbol:
                final AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                final EditText input = new EditText(this);
                alert.setView(input);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString().trim();
                        Crouton.makeText(mContext,"Not done yet...", CroutonStyles.RED).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                alert.setTitle("Add new symbol");
                alert.show();
                break;

            case R.id.action_exit:
                finish();
                break;

            case R.id.action_feedback:

                Crouton.makeText(mContext,"Thank you for the feedback", CroutonStyles.BLUE).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "your_email@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "App Feedback");
                startActivity(intent);

                break;


        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public class TabsPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
        private int[] ICONS = {
                R.drawable.home_ico,
                R.drawable.eye_ico,
                R.drawable.stat_ico,
        };

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new FragmentOne();
            }
            if (position == 1) {
                fragment = new FragmentList();
            }
            if (position == 2) {
                fragment = new FragmentGraph();
            }
            return fragment;
        }

        @Override
        public int getPageIconResId(int position) {
            return ICONS[position];
        }

        @Override
        public int getCount() {
            return ICONS.length;
        }
    }


}


