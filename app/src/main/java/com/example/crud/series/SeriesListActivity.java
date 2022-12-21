package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends BaseActivity {

    private ArrayList<Series> seriesList = new ArrayList<>();
    private RecyclerView seriesListRv;
    private SeriesListAdapter seriesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        getSupportActionBar().setTitle("Series");
        setupSeriesListRv();
        log("onCreate");
    }

    @Override
    protected void onResume() {
        log("onResume");
        super.onResume();
        fetchSeries();
    }

    private void updateSeries(Series series) {
        //Todo: rename the method as updateSeriesItem
        Intent intent = new Intent(this, EditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }

    private void deleteSeries(String id) {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.deleteSeriesItem(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted the data");
                fetchSeries();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete the data");
            }
        });
    }

    private void fetchSeries() {
        //Todo: rename to fetchSeriesList()

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<Series>> call = crudService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                // rename series to seriesList1

                seriesListAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                showToast("Failed to load the data");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddSeriesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setupSeriesListRv() {
        seriesListRv = findViewById(R.id.series_rv);
        seriesListRv.setLayoutManager(new LinearLayoutManager(this));
        seriesListAdapter = new SeriesListAdapter();
        seriesListAdapter.setData(seriesList);
        seriesListRv.setAdapter(seriesListAdapter);
        seriesListAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onEdit(Series series) {
                updateSeries(series);
            }

            @Override
            public void onDelete(String id) {
                deleteSeries(id);
            }
        });
    }
}