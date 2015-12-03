package com.andrei.template.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrei.template.MyBaseActivity;
import com.andrei.template.R;
import com.andrei.template.database.Table_WhatsMyIpPOJO;
import com.andrei.template.database.Table_WhatsMyIpPOJODao;
import com.andrei.template.models.WhatsMyIpPOJO;
import com.squareup.okhttp.ResponseBody;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

@EFragment(R.layout.fragment_apidemo)
public class ApiDemoFragment extends Fragment {

    @ViewById(R.id.textView_response) TextView textView_response;

    private Activity mContext;
    private long start_time;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();

        load_from_db();

        return null;
    }

    @AfterViews void calledAfterViewInjection() {

        start_time = System.currentTimeMillis();

        Call<WhatsMyIpPOJO> call = ((MyBaseActivity) mContext).getMyAPI().getterStuf("a parameter...");

        call.enqueue(new Callback<WhatsMyIpPOJO>() {
            @Override public void onResponse(Response<WhatsMyIpPOJO> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    WhatsMyIpPOJO mPojo = response.body();
                    textView_response.setText(mPojo.toString());
                    textView_response.append("\n done in " + String.valueOf(System.currentTimeMillis() - start_time) + " ms");

                    store_to_db(mPojo);

                } else {
                    int statusCode = response.code();
                    ResponseBody errorBody = response.errorBody();
                    Log.e("request failed", errorBody.toString());
                }
            }

            @Override public void onFailure(Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });


    }

    //------ simple store to DB ------
    private void store_to_db(WhatsMyIpPOJO mPojo) {
        Table_WhatsMyIpPOJODao mWDato = ((MyBaseActivity) mContext).getWhatsMyIpPOJODao();
        try {
            mWDato.insert(new Table_WhatsMyIpPOJO(mPojo.getYourFuckingIPAddress(), mPojo.getYourFuckingLocation(), mPojo.getYourFuckingHostname(), mPojo.getYourFuckingISP(), new Date()));
        } catch (Exception ex) {
            Log.e("db exception", ex.getMessage());
        }
    }


    //---- simple get from DB --------
    private void load_from_db() {
        Table_WhatsMyIpPOJODao mWDato = ((MyBaseActivity) mContext).getWhatsMyIpPOJODao();
        List<Table_WhatsMyIpPOJO> xArrayList = mWDato.queryBuilder().orderDesc(Table_WhatsMyIpPOJODao.Properties.Date).list();
        Log.v("Records in db: ",String.valueOf(xArrayList.size()));
        Log.i(">",String.valueOf(xArrayList.toString()));
    }

}
