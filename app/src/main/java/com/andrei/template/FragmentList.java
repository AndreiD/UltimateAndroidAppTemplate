package com.andrei.template;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.andrei.template.adapters.TheListAdapter;
import com.andrei.template.utils.AsyncHttp.RequestHandler;
import com.andrei.template.utils.AsyncHttp.RequestListener;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;


public class FragmentList extends Fragment implements AdapterView.OnItemClickListener {


    private FragmentActivity mContext;
    private SharedPreferences prefs;
    private long last_update;
    private String offline_data;
    private TheListAdapter adapter;
    private ListView listview_dan;
    private MyCountdown myCountdown = new MyCountdown(500,500);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, containter, false);

        mContext = getActivity();

        listview_dan = (ListView) view.findViewById(R.id.listview_dan);


        prefs = mContext.getSharedPreferences("PREFS", 0);
        last_update = prefs.getLong("last_update", System.currentTimeMillis() / 1000L);
        offline_data = prefs.getString("offline_data", "0");
        if (offline_data.equals("0")) {
            Toast.makeText(mContext, "no data available. please connect to the internet", Toast.LENGTH_LONG).show();
            mContext.finish();
        } else {
            update_ui(offline_data);
        }
        return view;
    }

    private void update_ui(String offline_data) {

        Log.v("update_ui called with", offline_data);

        //cleaup the () from the...json
        offline_data = offline_data.replaceAll("\\(", "").replaceAll("\\(", "");


            try {
                adapter = new TheListAdapter(getActivity(), new JSONArray(offline_data));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listview_dan.setAdapter(adapter);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onResume() {
        super.onResume();
        Log.v("on resume called", "on fragment profile!");
        try{
            myCountdown.cancel();
            myCountdown.start();
        }catch (Exception ex){
            Log.e("exception ",ex.getLocalizedMessage());
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public class MyCountdown extends CountDownTimer {
        public MyCountdown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            update_data();

        }


        @Override
        public void onTick(long millisUntilFinished) {


        }
    }

    private void update_data() {


        RequestHandler handler = RequestHandler.getInstance();
        handler.make_get_Request(mContext, "http://eu.tradenetworks.com/QuoteBox/quotes/GetQuotesBySymbols?languageCode=en-US&symbols=EURUSD,GBPUSD,USDCHF,USDJPY,AUDUSD,USDCAD,GBPJPY,EURGBP,EURJPY,AUDCAD", new RequestListener() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                try {
                    final String server_output = new String(response, "UTF-8");
                    Log.v("autoupdate triggered ok","autoupdate triggered ok");

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("last_update", System.currentTimeMillis() / 1000L);
                    editor.putString("offline_data", String.valueOf(server_output));
                    editor.commit();

                    update_ui(server_output);

                    myCountdown.start();




                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();

                    myCountdown.start(); //strategy ? retrying...
                }
            }
        });


    }

}




