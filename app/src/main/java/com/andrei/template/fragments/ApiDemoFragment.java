package com.andrei.template.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrei.template.MyBaseActivity;
import com.andrei.template.R;
import com.andrei.template.adapters.RecyclerAdapter;
import com.andrei.template.models.WhatsMyIpPOJO;
import com.andrei.template.utils.DUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

        ((MyBaseActivity) mContext).getMyAPI().getterStuf("a_parameter...", new Callback<WhatsMyIpPOJO>() {
            @Override public void success(WhatsMyIpPOJO whatsMyIpPOJO, Response response) {
                textView_response.setText(String.valueOf(whatsMyIpPOJO.toString()));
                textView_response.append(" done in " + String.valueOf(System.currentTimeMillis() - start_time) + " ms");
            }

            @Override public void failure(RetrofitError error) {
                DUtils.inform(mContext,"api call faild :(",R.color.orange_light);
            }
        });

    }


}
