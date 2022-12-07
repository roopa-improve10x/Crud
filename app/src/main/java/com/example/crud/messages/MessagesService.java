package com.example.crud.messages;

import com.example.crud.Constants;
import com.example.crud.messages.Messages;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MessagesService {

    @GET(Constants.MESSAGE_END_POINT)
    Call<List<Messages>> fetchMessages();

    @POST(Constants.MESSAGE_END_POINT)
    Call<Messages> addMessages(@Body Messages messages);

    @DELETE(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> deleteMessages(@Path("id") String id);

    @PUT(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> updateMessages(@Path("id") String id, @Body Messages messages);
}
