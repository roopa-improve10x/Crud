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
            String name = nameTxt.getText().toString();
            String number = phoneNumberTxt.getText().toString();
            String message = messageTxt.getText().toString();
            addMessage(name, number, message);
            return true;
        } else{
            return super.onOptionsItemSelected(item);
        }
    }

    //Todo: create the separate method setupCrudApiService()

    private void addMessage(String name, String phoneNumber, String messageText) {
        Message messages = new Message();
        messages.name = name;
        messages.mobileNo = phoneNumber;
        messages.message = messageText;

        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<Message> call = service.addMessages(messages);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("Successfully added the data");
                //Todo: change data as message
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("Failed to add data");
            }
        });
    }
}
