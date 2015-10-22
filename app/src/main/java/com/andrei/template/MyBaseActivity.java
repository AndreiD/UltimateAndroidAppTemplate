package com.andrei.template;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andrei.template.api.MyApi;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MyBaseActivity extends AppCompatActivity {



    private MyApi myAPI;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        //-------- set app fonts ---------
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/AdelleSans-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );



        //--------- initialize retrofit shit ------
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(2, TimeUnit.SECONDS);
        Client client = new OkClient(httpClient);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setClient(client)
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build();
        myAPI = restAdapter.create(MyApi.class);

    }

    public MyApi getMyAPI() {
        return myAPI;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
