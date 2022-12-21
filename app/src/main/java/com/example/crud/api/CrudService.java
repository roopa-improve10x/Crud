package com.example.crud.api;

import com.example.crud.Constants;
import com.example.crud.messages.Messages;
import com.example.crud.movies.Movie;
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

    //Todo : change all the method names into singular words.

    @POST(Constants.MESSAGE_END_POINT)
    Call<Messages> addMessage(@Body Messages messages);

    @DELETE(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @PUT(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> updateMessage(@Path("id") String id, @Body Messages messages);

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Templates>> fetchTemplates();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Templates> addTemplate(@Body Templates templates);

    @DELETE(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> updateTemplate(@Path("id") String id, @Body Templates templates);

    @GET(Constants.SERIES_END_POINT)
    Call<List<Series>> fetchSeries();
    //rename to seriesList

    @POST(Constants.SERIES_END_POINT)
    Call<Series> addSeriesItem(@Body Series series);

    @DELETE(Constants.SERIES_END_POINT + "{/id}")
    Call<Void> deleteSeriesItem(@Path("id") String id);

    @PUT(Constants.SERIES_END_POINT + "{/id}")
    Call<Void> updateSeriesItem(@Path("id") String id, @Body Series series);

    @GET(Constants.MOVIES_END_POINT)
    Call<List<Movie>> fetchMovies();

    @POST(Constants.MOVIES_END_POINT)
    Call<Movie> addMovie(@Body Movie movie);

    @PUT(Constants.MOVIES_END_POINT + "{/id}")
    Call<Void> updateMovie(@Path("id") String id, @Body Movie movie);

    @DELETE(Constants.MOVIES_END_POINT + "{/id}")
    Call<Void> deleteMovie(@Path("id") String id);



}
