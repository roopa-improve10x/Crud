package com.example.crud.templates;

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

public class TemplatesActivity extends BaseActivity {

    private ArrayList<Template> templateList = new ArrayList<>();
    private RecyclerView templatesRv;
    private TemplatesAdapter templateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setUpTemplatesRv();
        log("onCreate");
    }

    private void updateTemplates(Template templates) {
        //Todo: rename to updateTemplate

        Intent intent = new Intent(this, EditTemplateActivity.class);
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
               showToast("Successfully deleted the data");
               fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete the data");
            }
        });
    }

    @Override
    protected void onResume() {
        log("onResume");
        super.onResume();
        fetchTemplates();
    }

    private void fetchTemplates() {
        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templateAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                showToast("Failed to load the data");
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
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setUpTemplatesRv() {
        //Todo: rename to setupTemplatesRv

        templatesRv = findViewById(R.id.templates_rv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templateAdapter = new TemplatesAdapter();
        templateAdapter.setData(templateList);
        templatesRv.setAdapter(templateAdapter);
        templateAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onEdit(Template templates) {
                updateTemplates(templates);
            }

            @Override
            public void onDelete(String id) {
                deleteTemplate(id);
            }
        });
    }
}
