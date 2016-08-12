package com.andrei.template.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.andrei.template.BaseActivity;
import com.andrei.template.R;
import com.andrei.template.utils.DialogFactory;
import com.androidadvance.topsnackbar.TSnackbar;

import com.socks.library.KLog;

public class MainActivity extends BaseActivity {

  @BindView(R.id.button_dialog) Button button_dialog;
  @BindView(R.id.button_snackbar) Button button_snackbar;
  @BindView(R.id.button_edialog) Button button_edialog;
  @BindView(R.id.button_esnackbar) Button button_esnackbar;
  @BindView(R.id.relayout_main) RelativeLayout relayout_main;

  private MainActivity mContext;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    getSupportActionBar().setElevation(0);
    mContext = MainActivity.this;

  }

  @OnClick(R.id.button_dialog) public void onClick_button_dialog() {
    DialogFactory.createSimpleOkDialog(mContext, "this is a title", "hello from normal dialog").show();
  }

  @OnClick(R.id.button_snackbar) public void onClick_button_snackbar() {
    TSnackbar.make(relayout_main, "Hello from TSnackbar", TSnackbar.LENGTH_LONG).show();
  }

  @OnClick(R.id.button_edialog) public void onClick_button_edialog() {
    DialogFactory.createGenericErrorDialog(mContext, "hello from error dialog").show();
  }

  @OnClick(R.id.button_esnackbar) public void onClick_button_esnackbar() {
    DialogFactory.showErrorSnackBar(mContext, relayout_main, new Throwable("hello from error tsnackbar")).show();
  }

  //---------- Menu Items ----------
  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.action_settings:
        startActivity(new Intent(mContext, SettingsActivity.class));
        return true;
      case R.id.action_exit:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  //delete me if needed...
  //private void rate_this_app_logic() {
  //  AppRate.with(this).setInstallDays(10).setLaunchTimes(10).setRemindInterval(2).setShowLaterButton(false).setDebug(false).monitor();
  //  AppRate.showRateDialogIfMeetsConditions(this);
  //}
}


