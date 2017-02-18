package com.andrei.template;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.andrei.template.data.models.MyObjectBox;
import com.socks.library.KLog;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import io.objectbox.BoxStore;
public class BaseApplication extends Application {

  private Scheduler defaultSubscribeScheduler;
  private boolean isDebuggable;
  private static BoxStore boxStore;

  @Override public void onCreate() {
    super.onCreate();

    isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));

    if (isDebuggable) {
      KLog.init(true);
    } else {
      KLog.init(false);
    }

    //database init
    boxStore = MyObjectBox.builder().androidContext(BaseApplication.this).build();

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

  public BoxStore getBoxStore() {
    return boxStore;
  }

  public static BaseApplication get(Context context) {
    return (BaseApplication) context.getApplicationContext();
  }
}
