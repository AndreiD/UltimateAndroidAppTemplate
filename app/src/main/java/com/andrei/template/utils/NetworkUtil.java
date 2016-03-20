package com.andrei.template.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import retrofit2.adapter.rxjava.HttpException;

public class NetworkUtil {

  public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
    return throwable instanceof HttpException && ((HttpException) throwable).code() == statusCode;
  }

  public static boolean isNetworkConnected(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
  }
}