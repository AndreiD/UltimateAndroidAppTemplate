package com.andrei.template;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.andrei.template.api.MyApi;
import com.andrei.template.database.DaoMaster;
import com.andrei.template.database.DaoSession;
import com.andrei.template.database.Table_WhatsMyIpPOJODao;
import com.karumi.dexter.Dexter;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MyBaseActivity extends AppCompatActivity {

    private MyApi myAPI;
    private Table_WhatsMyIpPOJODao xWhatsMyIpPOJODao;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //----- app fonts -----
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/AdelleSans-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        //----- retrofit -----
        Retrofit mretrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPI = mretrofit.create(MyApi.class);

        //----- permission system ------
        Dexter.initialize(this);

        //------- database stuff -------
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "m_database", null);
        SQLiteDatabase ex_db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(ex_db);
        DaoSession daoSession = daoMaster.newSession();
        xWhatsMyIpPOJODao = daoSession.getTable_WhatsMyIpPOJODao();

    }

    public MyApi getMyAPI() {
        return myAPI;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public Table_WhatsMyIpPOJODao getWhatsMyIpPOJODao() {
        return xWhatsMyIpPOJODao;
    }
}
