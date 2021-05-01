package com.nestor.apimanager;

import android.content.Context;
import android.util.Log;

import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nestor.apimanager.Clases.Item;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiManager {
    private RequestQueue mRequestQueue;
    private Context context;
    private static ApiManager instance;
    private String mSERVER = "https://private-f0eea-mobilegllatam.apiary-mock.com/";

    public static ApiManager getInstance(Context context){
            if(instance == null){
                instance = new ApiManager(context);
            }

            return instance;
    }

    public ApiManager(Context context){
        this.context = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }



    public void getList(final ApiListener apiListener, String uri){
        makeReq(uri, apiListener, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Item> list = new ArrayList<>();
                for(int i=0; i<response.length();i++){
                    try {
                        Item item = new Item(response.getJSONObject(i));
                        list.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                apiListener.onResponse(list);
            }
        });
    }





    public void makeReq (String uri, final ApiListener apiListener, final Response.Listener<JSONArray> responseListener){

        int method = Request.Method.GET;
        JSONArray obj = new JSONArray();
        String url = null;

        if(uri.isEmpty()){
            url = mSERVER;
        }else{
            url = mSERVER+"/"+uri;
        }


        if(BuildConfig.DEBUG){
            System.out.println("url-->"+url);
        }

        JsonArrayRequest request = new JsonArrayRequest(method,url,obj,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                responseListener.onResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null){
                    apiListener.onError(error.networkResponse.statusCode);

                }
            }
        });

        mRequestQueue.add(request);
    }
}
