package com.nestor.globalLog;

import android.content.ClipData;
import android.content.Context;

import com.nestor.apimanager.ApiListener;
import com.nestor.apimanager.ApiManager;
import com.nestor.apimanager.Clases.Item;
import com.nestor.globalLog.Model.PresenterListener;

import java.util.ArrayList;

public class MainPresenter {
    ApiManager apiManager;

    public MainPresenter(Context context){
        apiManager = ApiManager.getInstance(context);
    }

    public void getList(PresenterListener listener){
        apiManager.getList(new ApiListener<ArrayList<Item>>(){
            @Override
            public void onResponse(ArrayList<Item> response) {
                listener.dataReady(response);
            }

            @Override
            public void onError(int status) {
                super.onError(status);
            }
        },"/list");

    }


}
