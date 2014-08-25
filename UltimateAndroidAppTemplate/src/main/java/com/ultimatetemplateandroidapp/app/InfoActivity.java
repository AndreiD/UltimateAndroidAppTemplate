package com.ultimatetemplateandroidapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;




public class InfoActivity extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_info);

		Button btn_close = (Button) findViewById(R.id.button_info_back_to_main);
		Button btn_feedback = (Button) findViewById(R.id.button_info_feedback);
		

		btn_close.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				finish();
			}
		});

		btn_feedback.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {

				Toast.makeText(InfoActivity.this, "thank you for the feedback", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("plain/text");
				intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "magvladim@gmail.com" });
				intent.putExtra(Intent.EXTRA_SUBJECT, "Folder Player Feedback");
				startActivity(intent);

			}
		});

	}

	
}
