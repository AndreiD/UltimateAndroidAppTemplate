package com.ultimatetemplateandroidapp.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.*;


public class SettingsActivity extends Activity {


    private TextView textView_font_size;
    private SeekBar seekBar_font_size;
    private SharedPreferences prefs;
    private LinearLayout linearLayout_settings;
    private EditText editText_font_color;
    private EditText editText_background_color;
    private CheckBox checkBox_big_controls;

    protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_settings);

		Button btn_close = (Button) findViewById(R.id.button_info_back_to_main);
        Button button_reset_defaults = (Button) findViewById(R.id.button_reset_defaults);
        textView_font_size = (TextView) findViewById(R.id.textView_font_size);
        seekBar_font_size = (SeekBar) findViewById(R.id.seekBar_font_size);
        linearLayout_settings = (LinearLayout) findViewById(R.id.linearLayout_settings);
        editText_font_color = (EditText) findViewById(R.id.editText_font_color);
        editText_background_color = (EditText) findViewById(R.id.editText_background_color);
        checkBox_big_controls = (CheckBox) findViewById(R.id.checkBox_big_controls);


        prefs = getSharedPreferences("PREFS",0);
        int old_progress = prefs.getInt("font_size", 24);
        String old_font_color = prefs.getString("font_color","#FFFFFF");
        String old_background_color = prefs.getString("background_color","#000000");
        Boolean old_big_contorls_mode = prefs.getBoolean("big_controls", false);

        checkBox_big_controls.setChecked(old_big_contorls_mode);

        textView_font_size.setTextColor(Color.parseColor(old_font_color));
        linearLayout_settings.setBackgroundColor(Color.parseColor(old_background_color));
        seekBar_font_size.setProgress(old_progress);
        textView_font_size.setTextSize(TypedValue.COMPLEX_UNIT_DIP,old_progress);
        textView_font_size.setText("Font size "+String.valueOf(old_progress));



        button_reset_defaults.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();

                editor.putInt("font_size", 24);
                editor.putString("font_color", "#FFFFFF");
                editor.putString("background_color", "#000000");
                editor.putBoolean("big_controls",false);
                editor.commit();

                checkBox_big_controls.setChecked(false);
                textView_font_size.setTextColor(Color.parseColor("#FFFFFF"));
                linearLayout_settings.setBackgroundColor(Color.parseColor("#000000"));
                seekBar_font_size.setProgress(24);
                textView_font_size.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);
                textView_font_size.setText("Font size "+String.valueOf(24));

                editText_font_color.setText("#FFFFFF");
                editText_background_color.setText("#000000");


            }
        });

        seekBar_font_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView_font_size.setTextSize(TypedValue.COMPLEX_UNIT_DIP,progress);
                textView_font_size.setText("Font size "+String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        editText_font_color.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    try{
                        textView_font_size.setTextColor(Color.parseColor(editText_font_color.getText().toString().trim()));
                    }catch(Exception ex){
                        textView_font_size.setTextColor(Color.parseColor("#FFFFFF"));
                        editText_font_color.setText("#FFFFFF");
                        Toast.makeText(SettingsActivity.this,"Invalid color code",Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });


        editText_background_color.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    try{
                        linearLayout_settings.setBackgroundColor(Color.parseColor(editText_background_color.getText().toString().trim()));
                    }catch(Exception ex){
                        linearLayout_settings.setBackgroundColor(Color.parseColor("#000000"));
                        editText_background_color.setText("#000000");
                        Toast.makeText(SettingsActivity.this,"Invalid color code",Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });

		btn_close.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {

                SharedPreferences.Editor editor = prefs.edit();

                editor.putInt("font_size", seekBar_font_size.getProgress());
                editor.putString("font_color", String.valueOf(editText_font_color.getText().toString().trim()));
                editor.putString("background_color", String.valueOf(editText_background_color.getText().toString().trim()));
                editor.putBoolean("big_controls",checkBox_big_controls.isChecked());
                editor.commit();

                Toast.makeText(SettingsActivity.this,"settings saved",Toast.LENGTH_LONG).show();

                Intent i_main = new Intent(SettingsActivity.this,MainActivity.class);
                i_main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i_main);

			}
		});



	}

	
}
