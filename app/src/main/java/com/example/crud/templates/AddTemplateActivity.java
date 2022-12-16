package com.example.crud.templates;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseAddEditTemplateActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            String messageText = templateMessageTxt.getText().toString();
            addTemplate(messageText);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Todo: create a method for apiService

    private void addTemplate(String messageText) {
        Templates templates = new Templates();
        templates.messageText = messageText;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Templates> call = crudService.addTemplate(templates);
        call.enqueue(new Callback<Templates>() {
            @Override
            public void onResponse(Call<Templates> call, Response<Templates> response) {
                showToast("Successfully added the data");
                finish();
            }

            @Override
            public void onFailure(Call<Templates> call, Throwable t) {
                showToast("Failed to add data");
            }
        });

    }
}
