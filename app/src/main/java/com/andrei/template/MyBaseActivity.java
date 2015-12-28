package com.andrei.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import timber.log.Timber;

public class MyBaseActivity extends AppCompatActivity {

  @Override public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    //----- Logging -----
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
