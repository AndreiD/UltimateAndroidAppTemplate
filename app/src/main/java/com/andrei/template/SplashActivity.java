package com.andrei.template;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.andrei.template.utils.DUtils;


public class SplashActivity extends Activity {

    private ImageView imageView_logo;
    private SplashActivity mContext;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        imageView_logo = (ImageView) findViewById(R.id.imageView_logo);

        mContext = SplashActivity.this;
        app_requires_internet_checker();

    }


    private void app_requires_internet_checker() {

        if (!DUtils.isOnline(mContext)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(R.string.internet_is_required_to_run_this_app)
                    .setCancelable(false)
                    .setNegativeButton(getString(R.string.action_exit), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            app_requires_internet_checker();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.setTitle(R.string.app_name);
            alert.show();
        } else {

            //------ nice animation. Here you should load something from a webservice etc. etc. or something else time taking...
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator());
            fadeOut.setDuration(300);
            imageView_logo.startAnimation(fadeOut);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imageView_logo.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }

    }


}























