package com.example.crud.templates;

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

public class TemplatesActivity extends AppCompatActivity {

    private ArrayList<Templates> templatesList = new ArrayList<>();
    private RecyclerView templatesRv;
    private TemplateAdapter templateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setUpTemplatesRv();
    }

    private void setupToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateTemplates(String templates) {
        Intent intent = new Intent(this, AddEditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, templates);
        startActivity(intent);
    }

    private void deleteTemplate(String id) {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
               setupToast("Successfully deleted the data");
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                setupToast("Failed to delete the data");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTemplates();
    }

    private void fetchTemplates() {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<Templates>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Templates>>() {
            @Override
            public void onResponse(Call<List<Templates>> call, Response<List<Templates>> response) {
                List<Templates> templates = response.body();
                templateAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Templates>> call, Throwable t) {
                setupToast("Failed to load the data");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.templates_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddEditTemplateActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setUpTemplatesRv() {
        templatesRv = findViewById(R.id.templates_rv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templateAdapter = new TemplateAdapter();
        templateAdapter.setData(templatesList);
        templatesRv.setAdapter(templateAdapter);
        templateAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onEdit(String templates) {
                updateTemplates(templates);
            }

            @Override
            public void onDelete(String id) {
                deleteTemplate(id);
            }
        });
    }
}