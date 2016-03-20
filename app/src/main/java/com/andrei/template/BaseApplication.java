package com.andrei.template;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import com.socks.library.KLog;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class BaseApplication extends Application {

  private Scheduler defaultSubscribeScheduler;
  private boolean isDebuggable;

  @Override public void onCreate() {
    super.onCreate();

    isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));

    if (isDebuggable) {
      KLog.init(true);
    } else {
      KLog.init(false);
    }

  }

  public Scheduler defaultSubscribeScheduler() {
    if (defaultSubscribeScheduler == null) {
      defaultSubscribeScheduler = Schedulers.io();
    }
    return defaultSubscribeScheduler;
  }

  public boolean isDebuggable() {
    return isDebuggable;
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    KLog.e("##### onLowMemory #####");
  }

  public static BaseApplication get(Context context) {
    return (BaseApplication) context.getApplicationContext();
  }
}
