package com.nestor.apimanager.Clases;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiData {
    private String id;
    private String name,pictureURL;

    public ApiData(){}

    public ApiData(JSONObject json) throws JSONException {
        id = json.getString("id");
        name = json.getString("name");
        pictureURL = json.getString("secure_thumbnail");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
