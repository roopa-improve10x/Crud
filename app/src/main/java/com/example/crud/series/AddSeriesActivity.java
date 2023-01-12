package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesActivity extends BaseAddEditSeriesActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add) {
            String seriesId = binding.seriesIdTxt.getText().toString();
            String name = binding.seriesNameTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
            addSeries(seriesId, name, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeries(String seriesId, String name, String imageUrl) {
        Series series = new Series();
        series.seriesId = seriesId;
        series.title = name;
        series.imageUrl = imageUrl;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Series> call = crudService.addSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                showToast("Successfully added the data");
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("Failed to add the data");
            }
        });
    }
}
