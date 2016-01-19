package com.andrei.template.data.remote;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public interface SampleAPI {

  String BASE_URL = "https://query.yahooapis.com/v1/public/";

  //@GET("your_endpoint") Call<YOUR_POJO> getWeather(@Query("from") String from);

  class Factory {
    private static SampleAPI service;

    public static SampleAPI getIstance() {
      if (service == null) {

        OkHttpClient.Builder okHttpClient_builder = new OkHttpClient().newBuilder();
        okHttpClient_builder.connectTimeout(5, TimeUnit.SECONDS);
        okHttpClient_builder.readTimeout(10, TimeUnit.SECONDS);

        //retrofit debug
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient_builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient_builder.build()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        service = retrofit.create(SampleAPI.class);
        return service;
      } else {
        return service;
      }
    }
  }
}
