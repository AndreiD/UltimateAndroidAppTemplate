package com.andrei.template.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.andrei.template.MyBaseActivity;
import com.andrei.template.R;
import com.andrei.template.fragments.ApiDemoFragment;
import com.andrei.template.fragments.ApiDemoFragment_;
import com.andrei.template.fragments.RecyclerDemoFragment;
import com.andrei.template.fragments.RecyclerDemoFragment_;
import com.andrei.template.utils.DUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends MyBaseActivity {


    @ViewById Button button_recycler;
    @ViewById Button button_api;
    @ViewById IconRoundCornerProgressBar progress_1;
    private MainActivity mContext;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = MainActivity.this;
    }



    @AfterViews void after__views() {
        progress_1.setMax(99);
        new CountDownTimer(10000, 10) {

            @Override public void onTick(long millisUntilFinished) {
                int progress = (int) millisUntilFinished / 100;
                progress_1.setProgress(100 - progress - 1);
            }

            @Override public void onFinish() {

            }
        }.start();

    }

    @Click(R.id.button_api) void api_clicked() {

        ApiDemoFragment r_fragment = ApiDemoFragment_.builder()
                .build();
        getFragmentManager().beginTransaction().replace(android.R.id.content, r_fragment).addToBackStack(null).commit();
    }

    @Click(R.id.button_recycler) void recycler_btn_clicked() {
        Log.i("MainActivity", "Button button_recycler pressed");

        RecyclerDemoFragment r_fragment = RecyclerDemoFragment_.builder()
                .build();
        getFragmentManager().beginTransaction().replace(android.R.id.content, r_fragment).addToBackStack(null).commit();



    }




    //---------- Menu Items ----------

    @OptionsItem(R.id.action_settings) void menu_settings() {
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsPreferenceFragment()).addToBackStack("settings").commit();
    }

    @OptionsItem(R.id.action_exit) void menu_exit() {
        finish();
    }

    @OptionsItem(R.id.action_feedback) void menu_feedback() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"your_email@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "App Feedback");
        startActivity(intent);
    }


    //---------- Lifecycle ----------
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


}


