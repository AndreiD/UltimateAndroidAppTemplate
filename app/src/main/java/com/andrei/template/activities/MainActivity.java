package com.andrei.template.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrei.template.MyBaseActivity;
import com.andrei.template.R;
import com.andrei.template.utils.CroutonStyles;
import com.squareup.picasso.Picasso;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import roboguice.inject.InjectView;
import roboguice.util.Ln;


public class MainActivity extends MyBaseActivity {

    @InjectView(R.id.textView_main) TextView textView_main;
    @InjectView(R.id.button_main) Button button_main;
    @InjectView(R.id.imageView_main) ImageView imageView_main;


    private MainActivity mContext;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;


        button_main.setOnClickListener(button_main_clicked());


    }

    private View.OnClickListener button_main_clicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ln.i("Button main pressed");
                Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png")
                        .placeholder(R.drawable.ic_launcher)
                        .error(android.R.drawable.ic_dialog_alert)
                        .into(imageView_main);
            }
        };
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                startActivity(new Intent(mContext, SettingsActivity.class));
                break;

            case R.id.action_exit:
                finish();
                break;

            case R.id.action_feedback:

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"your_email@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "App Feedback");
                startActivity(intent);

                break;


        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}


