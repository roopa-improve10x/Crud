package com.example.crud.messages;

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

public class AddEditMessageActivity extends AppCompatActivity {

    public Messages messages;
    public EditText addNameTxt;
    public EditText phoneNumberTxt;
    public EditText addMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        findIds();
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Task");
            messages = (Messages) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        } else {
            getSupportActionBar().setTitle("Add Task");
        }
    }

    public void setUpToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void updateMessage(String id, String name, String phoneNumber, String messageText) {
        Messages messages = new Messages();
        messages.name = name;
        messages.id= id;
        messages.mobileNo = phoneNumber;
        messages.message = messageText;
        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<Void> call = service.updateMessages(id, messages);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setUpToast("Successfully updated the data");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                setUpToast("Failed to update the data");
            }
        });

    }

    public void findIds() {
        addNameTxt = findViewById(R.id.add_name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        addMessageTxt = findViewById(R.id.add_message_txt);
    }

    public void showData(){
        addNameTxt.setText(messages.name);
        addMessageTxt.setText(messages.message);
        phoneNumberTxt.setText(messages.mobileNo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add){
            String name = addNameTxt.getText().toString();
            String number = phoneNumberTxt.getText().toString();
            String message = addMessageTxt.getText().toString();
            if(messages == null) {
                addMessage(name, number, message);
            } else {
                updateMessage(messages.id, name, number, message);
            }
            return true;
        } else{
            return super.onOptionsItemSelected(item);
        }
    }

    public void addMessage(String name, String phoneNumber, String messageText) {
        Messages messages = new Messages();
        messages.name = name;
        messages.mobileNo = phoneNumber;
        messages.message = messageText;

        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<Messages> call = service.addMessages(messages);
        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                setUpToast("Successfully added the data");
                finish();
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                setUpToast("Failed to add data");
            }
        });
    }
}