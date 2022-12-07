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

    public void updateMessage(String id, String name, String phoneNumber, String messageText) {
        Messages messages = new Messages();
        messages.name = name;
        messages.id= id;
        messages.mobileNo = phoneNumber;
        messages.message = messageText;
        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<Void> call = messagesService.updateMessages(id, messages);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditMessageActivity.this, "Successfully updated data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditMessageActivity.this, "Failed to update the data", Toast.LENGTH_SHORT).show();
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
            addMessage(name, number, message);
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

        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<Messages> call = messagesService.addMessages(messages);
        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                Toast.makeText(AddEditMessageActivity.this, "Successfully added the data", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                Toast.makeText(AddEditMessageActivity.this, "Failed to add the data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}