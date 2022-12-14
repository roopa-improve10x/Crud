package com.example.crud.messages;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Messages implements Serializable {
    //Todo: change the name as Message
    @SerializedName("_id")
    public String id;
    public String name;
    @SerializedName("phoneNumber")
    public String mobileNo;
    @SerializedName("messageText")
    public String message;
}
