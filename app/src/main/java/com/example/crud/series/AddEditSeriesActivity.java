package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends AppCompatActivity {

    public Series series;
    public EditText seriesIdTxt;
    public EditText seriesNameTxt;
    public EditText imageUrlTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        inItViews();
        if(getIntent().hasExtra(Constants.KEY_SERIES)){
            getSupportActionBar().setTitle("Edit Task");
            series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        } else {
            getSupportActionBar().setTitle("Add task");
        }
    }

    public void showData(){
        seriesIdTxt.getText().toString();
        seriesNameTxt.getText().toString();
        imageUrlTxt.getText().toString();
    }

    public void inItViews() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add) {
            String seriesId = seriesIdTxt.getText().toString();
            String name = seriesNameTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            if(series == null) {
                addSeries(seriesId, name, imageUrl);
            } else {
                updateSeries(series.id, seriesId, name, imageUrl);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void addSeries(String seriesIds, String name, String imageUrl) {
        Series series = new Series();
        series.seriesId = seriesIds;
        series.title = name;
        series.imageUrl = imageUrl;

        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Series> call = seriesService.addSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully added the data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Failed to add the data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateSeries(String id, String seriesIds, String name, String imagesUrl) {
        Series series = new Series();
        series.seriesId = seriesIds;
        series.title = name;
        series.imageUrl = imagesUrl;

        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.updateSeries(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully updated the data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Failed to update the data", Toast.LENGTH_SHORT).show();

            }
        });
       }
}