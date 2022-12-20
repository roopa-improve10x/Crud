package com.example.crud.movies;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.series.SeriesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMovieActivity extends BaseAddEditMovieActivity{

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit movie");
        if(getIntent().hasExtra(Constants.KEY_MOVIE)){
            movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
            showData();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.done) {
            String movieId = movieIdTxt.getText().toString();
            SeriesItem series = (SeriesItem) seriesItemsSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imageUrl = imageUrlTxt.getText().toString();
            String movieName = movieNameTxt.getText().toString();
            String description = movieDescriptionTxt.getText().toString();
            updateMovie( movie.id, movieId, seriesId, movieName, imageUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMovie(String id, String movieId, String seriesId, String movieName, String imageUrl, String description) {
        Movie movie = new Movie(movieId, seriesId, movieName, imageUrl, description);
        Call<Void> call = crudService.updateMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated movie");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update movie");
            }
        });
    }
}
