package com.andrei.template;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;


public class FragmentGraph extends Fragment {


    private FragmentActivity mContext;
    private FrameLayout FrameLayout_Graph;
    private GraphViewSeries graphSeries;
    private SharedPreferences prefs;
    private String offline_data = "";
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private GraphView graphView;
    private GraphViewSeries exampleSeries1;
    private GraphViewSeries exampleSeries2;
    private double graph2LastXValue = 5d;
    private GraphViewSeries exampleSeries3;

    private double getRandom() {
        double high = 3;
        double low = 0.5;
        return Math.random() * (high - low) + low;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph, containter, false);

        FrameLayout_Graph = (FrameLayout) view.findViewById(R.id.FrameLayout_Graph);

        mContext = getActivity();
        prefs = mContext.getSharedPreferences("PREFS", 0);



        GraphViewSeries.GraphViewSeriesStyle seriesStyle = new GraphViewSeries.GraphViewSeriesStyle();
        seriesStyle.color = Color.parseColor("#ade4b5");
        seriesStyle.thickness = 2;

        exampleSeries2 = new GraphViewSeries("Series 1",seriesStyle,new GraphView.GraphViewData[] {

        });



            graphView = new LineGraphView (
                    getActivity()
                    , "Real Time Graph (Random Numbers)"
            );


        graphView.addSeries(exampleSeries2);






        FrameLayout_Graph.addView(graphView);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer2);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();


        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                try {
                    exampleSeries2.appendData(new GraphView.GraphViewData(graph2LastXValue, getRandom()), true, 50);
                }catch (Exception ex) {
                    Log.e("exception", "ex");
                }
                mHandler.postDelayed(this, 800);
            }
        };
        mHandler.postDelayed(mTimer2, 1000);
    }
}




