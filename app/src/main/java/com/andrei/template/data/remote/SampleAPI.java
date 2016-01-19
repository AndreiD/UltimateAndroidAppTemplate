package com.andrei.template.data.remote;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.util.concurrent.TimeUnit;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public interface SampleAPI {

  String BASE_URL = "https://query.yahooapis.com/v1/public/";

  //@GET("your_endpoint") Call<YOUR_POJO> getWeather(@Query("from") String from);

  class Factory {
    private static SampleAPI service;

    public static SampleAPI getIstance() {
      if (service == null) {

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.SECONDS);
        client.setReadTimeout(10, TimeUnit.SECONDS);

        //retrofit debug
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.interceptors().add(interceptor);

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        service = retrofit.create(SampleAPI.class);
        return service;

      } else {
        return service;
      }
    }
  }
}
