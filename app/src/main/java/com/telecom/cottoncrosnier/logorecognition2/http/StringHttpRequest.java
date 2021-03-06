package com.telecom.cottoncrosnier.logorecognition2.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by matthieu on 19/01/17.
 */

/**
 * Requête Volley pour récupérer un fichier texte.
 */
public class StringHttpRequest extends HttpRequest {

    public static final int STRING_REQUEST = 3333;
    public static final String KEY_STRING = "key_string";


    /**
     * Instancie une {@link StringHttpRequest}.
     * @param context contexte appelant.
     * @param handler classe {@link Handler} décrivant l'action à faire selon le fichier
     *                reçu.
     * @param baseUrl adresse du serveur HTTP sur lequel les requêtes seront envoyées.
     */
    public StringHttpRequest(Context context, Handler handler, String baseUrl) {
        super(context, handler, baseUrl);
    }

    @Override
    public void sendRequest(String request) {
        mQueue.add(new StringRequest(Request.Method.GET, mBaseUrl + request, this, this));
        this.request = request.toLowerCase().replace("classifiers/", "");
    }

    @Override
    protected void sendMessage(Object response) {
        Message message = mHandler.obtainMessage();
        message.arg1 = STRING_REQUEST;

        Bundle bundle = new Bundle();
        bundle.putString(KEY_STRING, response.toString());
        bundle.putString(KEY_REQUEST, request);
        message.setData(bundle);

        mHandler.sendMessage(message);
    }
}
