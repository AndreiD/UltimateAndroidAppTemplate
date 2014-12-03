package com.andrei.template.utils.AsyncHttp;
import org.apache.http.Header;
public interface RequestListener {
    public void onSuccess(int statusCode, Header[] headers, byte[] response);
}