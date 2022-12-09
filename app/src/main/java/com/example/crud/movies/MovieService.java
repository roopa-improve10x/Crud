package com.example.crud.movies;

import com.example.crud.movies.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("movies")
    Call<List<Movie>> fetchMovies();
}
