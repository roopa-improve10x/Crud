package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    @SerializedName("_id")
    public String id;
    @SerializedName("name")
    public String movieName;
    @SerializedName("imageUrl")
    public String movieImageUrl;
    public String movieId;
    public String description;
    public String seriesId;

    public Movie() {
    }

    public Movie(String movieId, String seriesId, String title, String imageUrl, String description) {
        this.movieId = movieId;
        this.seriesId = seriesId;
        this.movieName = title;
        this.movieImageUrl = imageUrl;
        this.description = description;
    }
}
