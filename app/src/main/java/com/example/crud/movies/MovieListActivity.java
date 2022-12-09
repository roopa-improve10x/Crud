package com.example.crud.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    public ArrayList<Movie> movies;
    public RecyclerView moviesRv;
    public MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        getSupportActionBar().setTitle("Movies");
        setupData();
        setupMoviesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMovies();
    }

    public void fetchMovies() {
        MovieApi movieApi = new MovieApi();
        MovieService movieService = movieApi.createMovieService();
        Call<List<Movie>> call = movieService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movieList = response.body();
                movieAdapter.setData(movieList);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MovieListActivity.this, "Failed to load the data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setupData() {
        movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.movieId = "1";
        movie.movieName = "Pusshpa";
        movie.movieImageUrl = "https://cdn.siasat.com/wp-content/uploads/2022/11/photo_2022-11-02_11-20-16.jpg";
        movies.add(movie);
    }

    public void setupMoviesRv() {
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        moviesRv.setAdapter(movieAdapter);
        movieAdapter.setData(movies);

    }
}