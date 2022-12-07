package com.example.crud.series;

import com.example.crud.series.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SeriesService {

    @GET("roopaSeries")
    Call<List<Series>> fetchSeries();

    @POST("roopaSeries")
    Call<Series> addSeries(@Body Series series);

    @DELETE("series/{id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT("series/{id}")
    Call<Void> updateSeries(@Path("id") String id, @Body Series series);
}
