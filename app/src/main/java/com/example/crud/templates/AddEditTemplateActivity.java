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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {
    public Templates templates;
    public EditText templateMessageTxt;

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

    public void showData() {
        templateMessageTxt.setText(templates.messageText);
    }

    public void updateTemplates(String id, String messageText) {
        Templates templates = new Templates();
        templates.id = id;
        templates.messageText = messageText;

        TemplatesApi templatesApi = new TemplatesApi();
        TemplateService templateService = templatesApi.createTemplateService();
        Call<Void> call = templateService.updateTemplates(id, templates);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully updated the data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failed to update the data", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void inItViews() {
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

    public void addTemplate(String messageText) {
        templates = new Templates();
        templates.messageText = messageText;

       TemplatesApi templatesApi = new TemplatesApi();
       TemplateService templateService = templatesApi.createTemplateService();
       Call<Templates> call = templateService.addTemplate(templates);
       call.enqueue(new Callback<Templates>() {
           @Override
           public void onResponse(Call<Templates> call, Response<Templates> response) {
               Toast.makeText(AddEditTemplateActivity.this, "Successfully add the data", Toast.LENGTH_SHORT).show();
               finish();
           }

           @Override
           public void onFailure(Call<Templates> call, Throwable t) {
               Toast.makeText(AddEditTemplateActivity.this, "Failed to load the data", Toast.LENGTH_SHORT).show();
           }
       });

    }
}