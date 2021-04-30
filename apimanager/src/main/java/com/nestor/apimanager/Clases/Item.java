package com.nestor.apimanager.Clases;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {
    public String title,desc,image;

    public Item(){

    }


    public Item(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("title"))title=jsonObject.getString("title");
        if(jsonObject.has("description"))desc=jsonObject.getString("description");
        if(jsonObject.has("image"))image=jsonObject.getString("image");

    }
}
