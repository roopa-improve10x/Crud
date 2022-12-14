package com.example.crud.series;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Series implements Serializable {
    //Todo: change class name to seriesItem
    @SerializedName("_id")
    public String id;
    public String imageUrl;
    public String title;
    public String seriesId;
}
