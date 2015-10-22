package com.andrei.template.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;


import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrei.template.R;
import com.andrei.template.adapters.RecyclerAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;

@EFragment(R.layout.fragment_recyclerdemo)
public class RecyclerDemoFragment extends Fragment {

    @ViewById(R.id.x_recycler) RecyclerView x_recycler;
    @ViewById SwipeRefreshLayout x_swipe_refresh_layout;
    private ArrayList<String> photos_urls;
    private Activity mContext;
    private LinearLayoutManager mLayoutManager;
    private RecyclerAdapter fadapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();

        photos_urls = new ArrayList<>();
        photos_urls.add("https://drscdn.500px.org/photo/125977549/m%3D900_k%3D1_a%3D1/976e00af32b2dfa5dbcc996f5fd6a258");
        photos_urls.add("https://drscdn.500px.org/photo/125986105/m%3D900_k%3D1_a%3D1/5213b4b11d82c67b2a37dac95b9a4f5e");
        photos_urls.add("https://drscdn.500px.org/photo/125993507/m%3D900_k%3D1_a%3D1/1da719d564745908ee9e811840edab8f");
        photos_urls.add("https://drscdn.500px.org/photo/125997837/m%3D900_k%3D1_a%3D1/0fa552126d3710cd3bd6af9bd3d70c07");
        photos_urls.add("https://drscdn.500px.org/photo/125976225/m%3D900_k%3D1_a%3D1/1d08cae9b2d1da71e497975b94adca36");
        photos_urls.add("https://drscdn.500px.org/photo/125981443/m%3D900_k%3D1_a%3D1/6abe8575895e9e8a766b5a5b515a15d6");
        photos_urls.add("https://drscdn.500px.org/photo/125983249/m%3D900_k%3D1_a%3D1/194050ed566933d11058c3bf1d5a0cb4");
        photos_urls.add("https://drscdn.500px.org/photo/125990067/m%3D900_k%3D1_a%3D1/46abe3f9e7209d1eb312543058ea0dad");
        photos_urls.add("https://drscdn.500px.org/photo/125991057/m%3D900_k%3D1_a%3D1/e81f809b2555b0379e5cdf261bce25b2");
        photos_urls.add("https://drscdn.500px.org/photo/125995211/m%3D900_k%3D1_a%3D1/1305c9d98e4c78241d0e03ee1770fb5b");
        photos_urls.add("https://drscdn.500px.org/photo/125996271/m%3D900_k%3D1_a%3D1/2b7f41d41292984b9d6c4c395f55b387");
        photos_urls.add("https://drscdn.500px.org/photo/125998287/m%3D900_k%3D1_a%3D1/9d9074fb5404666277a29ce179da830b");
        photos_urls.add("https://drscdn.500px.org/photo/126001591/m%3D900_k%3D1_a%3D1/d6aa0f3fb5528e4214730b8b883c3d5e");
        photos_urls.add("https://ERROR_URL.jpg");


        return null;
    }

    @AfterViews
    void calledAfterViewInjection() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        x_recycler.setLayoutManager(layoutManager);

        fadapter = new RecyclerAdapter(mContext, photos_urls);
        x_recycler.swapAdapter(fadapter, false);

        x_swipe_refresh_layout.setColorSchemeResources(R.color.green_light, R.color.orange_light, R.color.red);

        x_swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                x_swipe_refresh_layout.setEnabled(false);
                do_a_refresh();
            }
        });
    }

    private void do_a_refresh() {
        x_swipe_refresh_layout.setEnabled(false);
        Collections.shuffle(photos_urls);
        fadapter.notifyDataSetChanged();

        x_swipe_refresh_layout.setEnabled(true);

        new CountDownTimer(3000,3000){
            @Override public void onTick(long millisUntilFinished) {
            }
            @Override public void onFinish() {
                x_swipe_refresh_layout.setRefreshing(false);
            }
        }.start();
    }

}
