package com.andrei.template.api;


import com.andrei.template.models.WhatsMyIpPOJO;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;



public interface MyApi {



    @GET("/json")
    public Call<WhatsMyIpPOJO> getterStuf(@Query("a_parameter") String a_parameter);

    //---- post image to server example -------
    @Multipart
    @POST("/pusher")
    public void pusherPicture(@Part("file") RequestBody file, @Part("file_xname") String file_xname, @Part("from_email") String from_email, Callback<Object> response);

}