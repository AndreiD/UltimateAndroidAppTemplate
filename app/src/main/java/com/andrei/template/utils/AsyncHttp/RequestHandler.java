package com.andrei.template.utils.AsyncHttp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;


import com.andrei.template.utils.DUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;


public class RequestHandler {

    private static final boolean SHOW_DEBUG_ALERT_DIALOG = true;
    private static RequestHandler instance;
    private AsyncHttpClient client;

    private RequestHandler() {
        client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(1,500);

    }

    public static RequestHandler getInstance() {
        if (instance == null) {
            instance = new RequestHandler();
        }
        return instance;
    }




    public void make_get_Request(final Context context, final String url, final RequestListener listener) {
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.v("▒▒▒▒▒▒▒ GET ", url);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                listener.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Log.e("▒▒▒▒▒▒▒ GET FAILED ", url);

                if (DUtils.isDebuggable(context) && SHOW_DEBUG_ALERT_DIALOG) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("▒▒▒▒▒ ERROR ▒▒▒▒▒");
                    String error_msg;
                    if (errorResponse != null) {
                        try {
                            error_msg = String.valueOf(new String(errorResponse, "UTF-8")) + " " + e.getLocalizedMessage();
                        } catch (UnsupportedEncodingException e1) {
                            error_msg = e.getLocalizedMessage();
                        }
                    } else {
                        error_msg = e.getLocalizedMessage();
                    }

                    builder.setMessage(context.getClass().getSimpleName() + " -> " + error_msg)
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                Log.e("▒▒▒▒▒▒▒ RETRYING ", "....." + String.valueOf(retryNo));

            }
        });
    }

    public void make_post_Request(final Context context, final StringEntity entity, final String url, final RequestListener listener) {
        client.post(context, url, entity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                Log.v("▒▒▒▒▒▒▒ POST ", url);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                listener.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Log.e("▒▒▒▒▒▒▒ POST FAILED ", url);


                if (DUtils.isDebuggable(context) && SHOW_DEBUG_ALERT_DIALOG) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("▒▒▒▒▒ ERROR ▒▒▒▒▒");
                    String error_msg;
                    if (errorResponse != null) {
                        try {
                            error_msg = String.valueOf(new String(errorResponse, "UTF-8")) + " " + e.getLocalizedMessage();
                        } catch (UnsupportedEncodingException e1) {
                            error_msg = e.getLocalizedMessage();
                        }
                    } else {
                        error_msg = e.getLocalizedMessage();
                    }

                    builder.setMessage(context.getClass().getSimpleName() + " -> " + error_msg)
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

            }

            @Override
            public void onRetry(int retryNo) {
                Log.e("▒▒▒▒▒▒▒ RETRYING ", "....." + String.valueOf(retryNo));
            }
        });
    }

}