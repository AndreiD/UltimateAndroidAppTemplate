package com.andrei.template.data.remote;

import android.content.Context;
import com.andrei.template.BuildConfig;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface SampleAPI {

  String BASE_URL = "https://query.yahooapis.com/v1/public/";

  //@GET("your_endpoint") Call<YOUR_POJO> getWeather(@Query("from") String from);

  class Factory {
    private static SampleAPI service;

    public static SampleAPI getIstance(Context context) {
      if (service == null) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        //builder.certificatePinner(new CertificatePinner.Builder().add("*.androidadvance.com", "sha256/RqzElicVPA6LkKm9HblOvNOUqWmD+4zNXcRb+WjcaAE=")
        //    .add("*.xxxxxx.com", "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
        //    .add("*.xxxxxxx.com", "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=")
        //    .add("*.xxxxxxx.com", "sha256/VjLZe/p3W/PJnd6lL8JVNBCGQBZynFLdZSTIqcO0SJ8=")
        //    .build());

        if (BuildConfig.DEBUG) {
          HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
          interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
          builder.addInterceptor(interceptor);
        }

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        builder.cache(cache);

        Retrofit retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        service = retrofit.create(SampleAPI.class);
        return service;
      } else {
        return service;
      }
    }
  }
}
