package com.example.crud.templates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Templates implements Serializable{
    @SerializedName("_id")
    public String id;
    @SerializedName("messageText")
    public String messageText;
}
