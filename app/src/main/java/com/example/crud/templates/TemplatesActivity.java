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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {

    public ArrayList<Templates> templatesList = new ArrayList<>();
    public RecyclerView templatesRv;
    public TemplateAdapter templateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setUpTemplatesRv();
    }

    public void updateTemplates(String templates) {
        Intent intent = new Intent(this, AddEditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, templates);
        startActivity(intent);
    }

    public void deleteTemplate(String id) {
        TemplatesApi templatesApi = new TemplatesApi();
        TemplateService templateService = templatesApi.createTemplateService();
        Call<Void> call = templateService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully deleted the data", Toast.LENGTH_SHORT).show();
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to delete the data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTemplates();
    }

    public void fetchTemplates() {
        TemplatesApi templatesApi = new TemplatesApi();
        TemplateService templateService = templatesApi.createTemplateService();
        Call<List<Templates>> call = templateService.fetchTemplates();
        call.enqueue(new Callback<List<Templates>>() {
            @Override
            public void onResponse(Call<List<Templates>> call, Response<List<Templates>> response) {
                List<Templates> templates = response.body();
                templateAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Templates>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to load the data", Toast.LENGTH_SHORT).show();
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

    public void setUpTemplatesRv() {
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