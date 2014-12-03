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
import com.jjoe64.graphview.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;

import java.util.Random;


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
        seriesStyle.color = Color.parseColor(getString(R.color.danstheme_color));
        seriesStyle.thickness = 5;

        GraphView graphView = new LineGraphView(
                mContext
                , "" // heading
        );
        graphSeries = new GraphViewSeries("My Graph", seriesStyle, new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(0, 0d)
        });

        graphView.setScrollable(true);
        graphView.setViewPort(2, 100);
        graphView.setScalable(true);


        graphView.getGraphViewStyle().setGridColor(Color.parseColor("#444444"));

        graphView.getGraphViewStyle().setNumHorizontalLabels(5);


        graphView.getGraphViewStyle().setGridStyle(GraphViewStyle.GridStyle.BOTH);
        graphView.addSeries(graphSeries); // data



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
                Random rand = new Random();
                int rand_nr = rand.nextInt(20);
                graphSeries.appendData(new GraphView.GraphViewData(graph2LastXValue,rand_nr), true, 150);
                mHandler.postDelayed(this, 100);
            }
        };
        mHandler.postDelayed(mTimer2, 100);
    }


    public float[] lowPass( float[] input, float[] output ) {
        if ( output == null ) return input;

        float ALPHA = 0.15f;

        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }
}




