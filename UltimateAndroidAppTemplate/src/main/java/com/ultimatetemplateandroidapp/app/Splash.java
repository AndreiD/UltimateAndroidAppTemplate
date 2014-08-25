package com.ultimatetemplateandroidapp.app;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.apache.http.Header;

public class Splash extends Activity {

    private TextView textView_splash_title;
    private MyTimmer mTimer;
    private TextView textView_splash_subtitle;
    private Splash mContext;
    private SharedPreferences prefs;
    private String the_message;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);

        mContext = Splash.this;

        textView_splash_title = (TextView) findViewById(R.id.textView_splash_title);
        textView_splash_subtitle = (TextView) findViewById(R.id.textView_splash_subtitle);

        Animation slide_left = AnimationUtils.loadAnimation(this, R.anim.wave_scale);
        Animation slide_right = AnimationUtils.loadAnimation(this, R.anim.wave_scale);
        textView_splash_title.startAnimation(slide_left);
        textView_splash_subtitle.startAnimation(slide_right);


        if (DUtils.isOnline(mContext)) {

            Log.d("Application has internet", "-------- INTERNET CONNECTED ------");

            AsyncHttpClient client = new AsyncHttpClient();
            client.setMaxRetriesAndTimeout(1, 500);
            client.get(Constants.news_url, new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    // called before request is started
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    String source = null;


                    try {
                        source = new String(response, "UTF-8");

                        if (source.length() > 3) {

                            String[] aux = source.split(" ### ");
                            String the_message = aux[0];
                            final String the_link = aux[1];

                            builder.setMessage(the_message)
                                    .setCancelable(false)
                                    .setNegativeButton("go to url", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(the_link));
                                            startActivity(browserIntent);

                                        }
                                    })
                                    .setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                            mTimer = new MyTimmer(200, 200);
                                            mTimer.start();
                                        }
                                    });
                        }else{
                            Log.i("no news detected","no news detected");
                            mTimer = new MyTimmer(600, 600);
                            mTimer.start();
                        }

                    } catch (Exception ex) {

                        builder.setMessage(source)
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                        mTimer = new MyTimmer(200, 200);
                                        mTimer.start();
                                    }
                                });
                    }


                    AlertDialog alert = builder.create();
                    alert.show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Log.e("Failed to get the news", "Failed to get the news.");
                    mTimer = new MyTimmer(600, 600);
                    mTimer.start();
                }

                @Override
                public void onRetry(int retryNo) {
                    Log.e("Retrying to get the news", "Retrying to get the news: "+String.valueOf(retryNo));
                }
            });
        } else {
            Log.d("Application offline mode", "-------- NO INTERNET ------");
            mTimer = new MyTimmer(600, 600);
            mTimer.start();
        }


    }


    public class MyTimmer extends CountDownTimer {

        public MyTimmer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }


        public void onFinish() {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivityForResult(intent, 0);

        }


        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

        }

    }





}