package com.example.crud.templates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {
    private Templates templates;
    private EditText templateMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        getSupportActionBar().setTitle("Add");
        inItViews();
        if(getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Task");
            templates = (Templates) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
        } else {
            getSupportActionBar().setTitle("Add Task");
        }
    }

    private void setupToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showData() {
        templateMessageTxt.setText(templates.messageText);
    }

    private void updateTemplates(String id, String messageText) {
        Templates templates = new Templates();
        templates.id = id;
        templates.messageText = messageText;

        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();
        Call<Void> call = crudService.updateTemplates(id, templates);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setupToast("Successfully updated data");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                setupToast("Failed to updated the data");
            }
        });
    }

    private void inItViews() {
        templateMessageTxt = findViewById(R.id.template_message_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            String messageText = templateMessageTxt.getText().toString();
            if(templates == null) {
                addTemplate(messageText);
            } else {
                updateTemplates(templates.id, messageText);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addTemplate(String messageText) {
        templates = new Templates();
        templates.messageText = messageText;

      CrudApi crudApi = new CrudApi();
      CrudService crudService = crudApi.createCrudService();
       Call<Templates> call = crudService.addTemplate(templates);
       call.enqueue(new Callback<Templates>() {
           @Override
           public void onResponse(Call<Templates> call, Response<Templates> response) {
               setupToast("Successfuly added the data");
               finish();
           }

           @Override
           public void onFailure(Call<Templates> call, Throwable t) {
               setupToast("Failed to add data");
           }
       });

    }
}