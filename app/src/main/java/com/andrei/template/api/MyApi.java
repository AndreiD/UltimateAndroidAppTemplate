package com.andrei.template.api;


import com.andrei.template.models.WhatsMyIpPOJO;

import org.json.JSONObject;

import java.util.Objects;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;


public interface MyApi {



    @GET("/json")
    public void getterStuf(@Query("a_parameter") String a_parameter, Callback<WhatsMyIpPOJO> response); //replace Object with a POJO

    //---- post image to server example -------
    @Multipart
    @POST("/pusher")
    public void pusherPicture(@Part("file") TypedFile file, @Part("file_xname") TypedString file_xname, @Part("from_email") TypedString from_email, Callback<Object> response);

}