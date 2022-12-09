package com.example.crud.api;

import com.example.crud.Constants;
import com.example.crud.messages.Messages;
import com.example.crud.series.Series;
import com.example.crud.templates.Templates;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudService {

    @GET(Constants.MESSAGE_END_POINT)
    Call<List<Messages>> fetchMessages();

    @POST(Constants.MESSAGE_END_POINT)
    Call<Messages> addMessages(@Body Messages messages);

    @DELETE(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> deleteMessages(@Path("id") String id);

    @PUT(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> updateMessages(@Path("id") String id, @Body Messages messages);

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Templates>> fetchTemplates();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Templates> addTemplate(@Body Templates templates);

    @DELETE(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> updateTemplates(@Path("id") String id, @Body Templates templates);

    @GET(Constants.SERIES_END_POINT)
    Call<List<Series>> fetchSeries();

    @POST(Constants.SERIES_END_POINT)
    Call<Series> addSeries(@Body Series series);

    @DELETE(Constants.SERIES_END_POINT + "{/id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT(Constants.SERIES_END_POINT + "{/id}")
    Call<Void> updateSeries(@Path("id") String id, @Body Series series);

}
