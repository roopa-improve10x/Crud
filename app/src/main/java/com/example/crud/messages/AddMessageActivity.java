package com.example.crud.messages;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseAddEditMessageActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Message");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add){
            String name = binding.addNameTxt.getText().toString();
            String number = binding.phoneNumberTxt.getText().toString();
            String message = binding.addMessageTxt.getText().toString();
            addMessage(name, number, message);
            return true;
        } else{
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMessage(String name, String phoneNumber, String messageText) {
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
                showToast("Successfully added the message");
                finish();
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                showToast("Failed to add data");
            }
        });
    }
}
