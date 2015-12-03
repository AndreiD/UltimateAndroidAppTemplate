package com.andrei.template.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.andrei.template.MyBaseActivity;
import com.andrei.template.R;
import com.andrei.template.fragments.ApiDemoFragment;
import com.andrei.template.fragments.ApiDemoFragment_;
import com.andrei.template.fragments.RecyclerDemoFragment;
import com.andrei.template.fragments.RecyclerDemoFragment_;
import com.andrei.template.permissions.SamplePermissionListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

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
    @ViewById ImageButton imageButton_camera;
    private MainActivity mContext;
    private PermissionListener cameraPermissionListener;
    private View rootView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = MainActivity.this;

        rootView = findViewById(android.R.id.content);

        //------ Permissions ----------
        PermissionListener feedbackViewPermissionListener = new SamplePermissionListener(this);
        cameraPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener);


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
        RecyclerDemoFragment r_fragment = RecyclerDemoFragment_.builder()
                .build();
        getFragmentManager().beginTransaction().replace(android.R.id.content, r_fragment).addToBackStack(null).commit();
    }

    @Click(R.id.imageButton_camera) void camera_clicked() {
        if (Dexter.isRequestOngoing()) {
            return;
        }
        Dexter.checkPermission(cameraPermissionListener, Manifest.permission.CAMERA);
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


    //---------- Android 6.0+ Permissions Management ----------
    public void showPermissionGranted(String permissionName) {
        Log.v("showPermissionGranted", String.valueOf(permissionName));
    }

    public void showPermissionDenied(String permissionName, boolean permanentlyDenied) {
        Log.e("showPermissionDenied", String.valueOf(permissionName) + " permanently: " + String.valueOf(permanentlyDenied));
        if (permanentlyDenied) {
            new AlertDialog.Builder(this).setTitle("Attention")
                    .setMessage("The permission was permanently denied. Please reinstall the app")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showPermissionRationale(final PermissionToken token) {
        new AlertDialog.Builder(this).setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.permission_rationale_message)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .show();
    }

}


