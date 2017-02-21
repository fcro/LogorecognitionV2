package com.telecom.cottoncrosnier.logorecognition2.http;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

/**
 * Created by fcro on 19/01/2017.
 */

public abstract class HttpRequest implements Response.Listener, Response.ErrorListener {

    private static final String TAG = HttpRequest.class.getSimpleName();


    static protected RequestQueue mQueue;

    public static final String KEY_REQUEST = "key_request";

    static protected String mBaseUrl;
    static protected Handler mHandler;


    protected String request;


    public HttpRequest(Context context, Handler handler, String baseUrl) {
        mQueue = Volley.newRequestQueue(context);
        mHandler = handler;
        mBaseUrl = baseUrl;
    }

    public abstract void sendRequest(String request);

    protected abstract void sendMessage(Object response);

    @Override
    public  void onResponse(Object response){
        Log.d(TAG, "onResponse() called with: request = [" + request + "]");
        sendMessage(response);
    }

    @Override
    public  void onErrorResponse(VolleyError error){}
}
