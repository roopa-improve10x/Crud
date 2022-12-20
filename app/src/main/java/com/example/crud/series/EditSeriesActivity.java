package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesActivity extends BaseAddEditSeriesActivity{

    private SeriesItem series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Series");
        if(getIntent().hasExtra(Constants.KEY_SERIES)){
            series = (SeriesItem) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        }
    }

    private void showData(){
        seriesIdTxt.getText().toString();
        seriesNameTxt.getText().toString();
        imageUrlTxt.getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add) {
            String seriesId = seriesIdTxt.getText().toString();
            String name = seriesNameTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            updateSeries(series.id, seriesId, name, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateSeries(String id, String seriesId, String name, String imagesUrl) {
        // Todo: change the method name as updateSeriesItem.
        SeriesItem series = new SeriesItem();
        series.seriesId = seriesId;
        series.title = name;
        series.imageUrl = imagesUrl;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.updateSeries(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated the data");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to updated data");
            }
        });
    }
}
