package com.andrei.template;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.andrei.template.api.MyApi;
import com.karumi.dexter.Dexter;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
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
        Retrofit mretrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPI = mretrofit.create(MyApi.class);

        //----- permission system ------
        Dexter.initialize(this);

    }

    public MyApi getMyAPI() {
        return myAPI;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
