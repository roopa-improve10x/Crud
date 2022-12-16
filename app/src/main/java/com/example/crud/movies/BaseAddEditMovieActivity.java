package com.example.crud.movies;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {
    protected CrudService crudService;
    protected CustomSeriesAdapter customSeriesAdapter;
    protected ArrayList<Series> seriesList = new ArrayList<>();
    protected Spinner spinnerSp;
    protected Movie movie;
    protected EditText movieIdTxt;
    protected EditText movieNameTxt;
    protected EditText imageUrlTxt;
    protected EditText movieDescriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        setupSeriesSp();
    }

    private void setupSeriesSp() {
        CrudApi api = new CrudApi();
        crudService = api.createCrudService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    private void fetchSeries() {
        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<List<Series>> call = service.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {

            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {

            }
        });
    }
}