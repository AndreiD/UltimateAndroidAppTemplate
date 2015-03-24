package com.andrei.template;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.andrei.template.fragments.FragmentOne;
import com.andrei.template.fragments.FragmentThree;
import com.andrei.template.fragments.FragmentTwo;
import com.andrei.template.utils.CroutonStyles;
import com.astuetz.PagerSlidingTabStrip;

import de.keyboardsurfer.android.widget.crouton.Crouton;


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
                fragment = new FragmentTwo();
            }
            if (position == 2) {
                fragment = new FragmentThree();
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


