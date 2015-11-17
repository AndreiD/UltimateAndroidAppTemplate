package com.andrei.template.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrei.template.MyBaseActivity;
import com.andrei.template.R;
import com.andrei.template.adapters.RecyclerAdapter;
import com.andrei.template.models.WhatsMyIpPOJO;
import com.andrei.template.utils.DUtils;
import com.squareup.okhttp.ResponseBody;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

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


}
