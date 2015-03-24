package com.andrei.template.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andrei.template.R;
import com.andrei.template.api.RESTClient;
import com.andrei.template.api.RestCallback;
import com.andrei.template.model.example_model.MyGeoIp;

import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import retrofit.client.Response;


public class FragmentTwo extends Fragment {


    private FragmentActivity mContext;
    private ListView listView_fragment_two;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, containter, false);

        listView_fragment_two = (ListView) view.findViewById(R.id.listView_fragment_two);

        mContext = getActivity();

        get_headers();


        return view;
    }

    private void get_headers() {
        RESTClient.get().getGeoIp(new RestCallback<MyGeoIp>() {
            @Override
            public void success(MyGeoIp myGeoIp, Response response) {
                process_response(myGeoIp);
            }
        });
    }

    private void process_response(MyGeoIp myGeoIp) {

        ArrayList<String> items = new ArrayList<>();
        items.add("Latitude : " + myGeoIp.getLatitude());
        items.add("Longitude : " + myGeoIp.getLongitude());
        items.add("Asn : " + myGeoIp.getAsn());
        items.add("Offset : " + myGeoIp.getOffset());
        items.add("Ip : " + myGeoIp.getIp());
        items.add("AreaCode : " + myGeoIp.getAreaCode());
        items.add("ContinentCode : " + myGeoIp.getContinentCode());
        items.add("DmaCode : " + myGeoIp.getDmaCode());
        items.add("City : " + myGeoIp.getCity());
        items.add("Timezone : " + myGeoIp.getTimezone());
        items.add("Region : " + myGeoIp.getRegion());
        items.add("CountryCode : " + myGeoIp.getCountryCode());
        items.add("Isp : " + myGeoIp.getIsp());
        items.add("Country : " + myGeoIp.getCountry());
        items.add("CountryCode3 : " + myGeoIp.getCountryCode3());
        items.add("RegionCode : " + myGeoIp.getRegionCode());


        ArrayAdapter<String> itemsAdapter =  new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, items);
        listView_fragment_two.setAdapter(itemsAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onResume() {
        super.onResume();

    }


}




