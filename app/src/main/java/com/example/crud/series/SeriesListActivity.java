package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends AppCompatActivity {
    private ArrayList<Series> seriesList = new ArrayList<>();
    private RecyclerView seriesRv;
    private SeriesAdapter seriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        getSupportActionBar().setTitle("Series");
        setUpSeriesRv();
    }

    private void setupToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSeries();
    }

    private void updateSeries(Series series) {
        Intent intent = new Intent(this, AddEditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }

    private void deleteSeries(String id) {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setupToast("Successuflly deleted the data");
                fetchSeries();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                setupToast("Failed to delete the data");
            }
        });
    }

    private void fetchSeries() {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<Series>> call = crudService.fetchSeries();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                seriesAdapter.setData(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                setupToast("Failed to load the data");
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
            Intent intent = new Intent(this, AddEditSeriesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setUpSeriesRv() {
        seriesRv = findViewById(R.id.series_rv);
        seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setData(seriesList);
        seriesRv.setAdapter(seriesAdapter);
        seriesAdapter.setOnItemActionListener(new OnItemActionListener() {
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