package com.example.crud.templates;

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

public class EditTemplateActivity extends BaseAddEditTemplateActivity{

    private Templates templates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Template");
        if(getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            templates = (Templates) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
        }
    }

    private void showData() {
       binding.setTemplate(templates);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            String messageText = binding.templateMessageTxt.getText().toString();
            updateTemplates(templates.id, messageText);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateTemplates(String id, String messageText) {

        //Todo: rename to updateTemplate()

        templates = new Templates();
        templates.messageText = messageText;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.updateTemplates(id, templates);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated data");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to updated the data");
            }
        });
    }
}
