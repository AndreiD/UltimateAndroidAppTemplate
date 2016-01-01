package com.andrei.template;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class BaseApplication extends Application {

  private Scheduler defaultSubscribeScheduler;

  @Override public void onCreate() {
    super.onCreate();

    boolean isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));

    if (isDebuggable) {
      Timber.plant(new Timber.DebugTree());
    }

  }

  public Scheduler defaultSubscribeScheduler() {
    if (defaultSubscribeScheduler == null) {
      defaultSubscribeScheduler = Schedulers.io();
    }
    return defaultSubscribeScheduler;
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    Timber.e("##### onLowMemory #####");
  }


  public static BaseApplication get(Context context) {
    return (BaseApplication) context.getApplicationContext();
  }
}
