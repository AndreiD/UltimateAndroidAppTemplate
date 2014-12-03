package com.andrei.template;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.andrei.template.utils.AsyncHttp.RequestHandler;
import com.andrei.template.utils.AsyncHttp.RequestListener;
import com.andrei.template.utils.DUtils;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;

public class Splash extends Activity {

    private ImageView imageView_logo;

    private Splash mContext;
    private SharedPreferences prefs;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        imageView_logo = (ImageView) findViewById(R.id.imageView_logo);

        mContext = Splash.this;
        prefs = getSharedPreferences("PREFS", 0);


        if (DUtils.isOnline(mContext)) {
            // Application has internet
            update_offline_data();
        } else {
            // Offline mode!
            Intent intent = new Intent(mContext, MainActivity.class);
            String offline_data = prefs.getString("offline_data", "0");
            if (!offline_data.equals("0")) {
                intent.putExtra("offline_data", offline_data);
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage(R.string.first_time_requires_internet)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle(R.string.app_name);
                alert.show();

            }
        }
    }


    private void update_offline_data() {


        RequestHandler handler = RequestHandler.getInstance();
        handler.make_get_Request(mContext, "http://eu.tradenetworks.com/QuoteBox/quotes/GetQuotesBySymbols?languageCode=en-US&symbols=EURUSD,GBPUSD,USDCHF,USDJPY,AUDUSD,USDCAD,GBPJPY,EURGBP,EURJPY,AUDCAD", new RequestListener() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                try {
                    final String server_output = new String(response, "UTF-8");

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putLong("last_update", System.currentTimeMillis() / 1000L);
                    editor.putString("offline_data", String.valueOf(server_output));
                    editor.commit();


                    Animation fadeOut = new AlphaAnimation(1, 0);
                    fadeOut.setInterpolator(new AccelerateInterpolator());
                    fadeOut.setDuration(300);
                    imageView_logo.startAnimation(fadeOut);
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {}

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imageView_logo.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.putExtra("offline_data", String.valueOf(server_output));
                            startActivity(intent);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });




                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });


    }


}























