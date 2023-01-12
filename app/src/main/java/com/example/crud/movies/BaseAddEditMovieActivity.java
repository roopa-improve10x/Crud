package com.example.crud.movies;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityAddEditMovieBinding;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected CrudService crudService;
    protected CustomSeriesAdapter customSeriesAdapter;
    private ArrayList<Series> seriesItems = new ArrayList<>();
    protected Movie movie;
   protected ActivityAddEditMovieBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupCrudApiService();
        fetchSeriesItems();
        setupSeriesItemsSp();
    }

    private void setupSeriesItemsSp() {
        customSeriesAdapter = new CustomSeriesAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
        binding.seriesItemsSp.setAdapter(customSeriesAdapter);
    }

    private void setupCrudApiService() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    private void fetchSeriesItems() {
        Call<List<Series>> call = crudService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
            List<Series> seriesItems = response.body();
            customSeriesAdapter.addAll(seriesItems);
            if(movie != null) {
                showData();
            }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });
    }

    public void showData() {
       binding.setMovie(movie);
        for(int i = 0; i < customSeriesAdapter.getCount(); i++) {
            Series series = customSeriesAdapter.getItem(i);
            if(movie.seriesId.equals(series.seriesId)) {
                binding.seriesItemsSp.setSelection(i);
            }
        }
    }
}