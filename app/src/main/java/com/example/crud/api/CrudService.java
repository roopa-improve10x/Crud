package com.example.crud.api;

import com.example.crud.Constants;
import com.example.crud.messages.Message;
import com.example.crud.movies.Movie;
import com.example.crud.series.SeriesItem;
import com.example.crud.templates.Template;

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
    Call<List<Message>> fetchMessages();

    //Todo : change all the method names into singular words.

    @POST(Constants.MESSAGE_END_POINT)
    Call<Message> addMessages(@Body Message messages);

    @DELETE(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> deleteMessages(@Path("id") String id);

    @PUT(Constants.MESSAGE_END_POINT + "/{id}")
    Call<Void> updateMessages(@Path("id") String id, @Body Message messages);

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Template>> fetchTemplates();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Template> addTemplate(@Body Template templates);

    @DELETE(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> updateTemplates(@Path("id") String id, @Body Template templates);

    @GET(Constants.SERIES_END_POINT)
    Call<List<SeriesItem>> fetchSeries();
    //rename to seriesList

    @POST(Constants.SERIES_END_POINT)
    Call<SeriesItem> addSeries(@Body SeriesItem series);

    @DELETE(Constants.SERIES_END_POINT + "{/id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT(Constants.SERIES_END_POINT + "{/id}")
    Call<Void> updateSeries(@Path("id") String id, @Body SeriesItem series);

    @GET(Constants.MOVIES_END_POINT)
    Call<List<Movie>> fetchMovies();

    @POST(Constants.MOVIES_END_POINT)
    Call<Movie> addMovie(@Body Movie movie);

    @PUT(Constants.MOVIES_END_POINT + "{/id}")
    Call<Void> updateMovie(@Path("id") String id, @Body Movie movie);

    @DELETE(Constants.MOVIES_END_POINT + "{/id}")
    Call<Void> deleteMovie(@Path("id") String id);



}
