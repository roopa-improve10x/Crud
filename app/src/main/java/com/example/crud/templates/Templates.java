package com.example.crud.templates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Templates implements Serializable{
    //Todo: rename class name to template
    @SerializedName("_id")
    public String id;
    @SerializedName("messageText")
    public String messageText;
}
