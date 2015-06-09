package com.andrei.template.utils;

import android.graphics.Color;
import android.view.Gravity;


import com.andrei.template.R;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Style;



public class CroutonStyles {


    // Crouton.makeText(mContext, getString(R.string.password_min_length_invalid), CroutonStyles.RED).show();

    public static Style RED = new Style.Builder()
            .setConfiguration(Configuration.DEFAULT)
            .setBackgroundColor(R.color.red_dark)
            .setGravity(Gravity.LEFT)
            .setTextColorValue(Color.WHITE)
            .setTextSize(14)
            .setImageResource(android.R.drawable.ic_dialog_alert)
            .setPaddingDimensionResId(R.dimen.activity_horizontal_margin)
            .build();

    public static Style GREEN = new Style.Builder()
            .setConfiguration(Configuration.DEFAULT)
            .setBackgroundColor(R.color.green_dark)
            .setTextColorValue(Color.WHITE)
            .setGravity(Gravity.CENTER)
            .setGravity(Gravity.LEFT)
            .setPaddingDimensionResId(R.dimen.activity_horizontal_margin)
            .setTextSize(14)
            .build();

    public static Style BLUE = new Style.Builder()
            .setConfiguration(Configuration.DEFAULT)
            .setBackgroundColor(R.color.blue_dark)
            .setTextColorValue(Color.WHITE)
            .setGravity(Gravity.CENTER)
            .setGravity(Gravity.LEFT)
            .setPaddingDimensionResId(R.dimen.activity_horizontal_margin)
            .setTextSize(14)
            .build();

    public static Style ORANGE = new Style.Builder()
            .setConfiguration(Configuration.DEFAULT)
            .setBackgroundColor(R.color.orange_dark)
            .setTextColorValue(Color.WHITE)
            .setGravity(Gravity.CENTER)
            .setGravity(Gravity.LEFT)
            .setPaddingDimensionResId(R.dimen.activity_horizontal_margin)
            .setTextSize(14)
            .build();

}