package com.example.crud.messages;

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

public class EditMessageActivity extends BaseAddEditMessageActivity{

    private Messages messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Message");
            messages = (Messages) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        }
    }

    private void showData(){
        nameTxt.setText(messages.name);
        messageTxt.setText(messages.message);
        phoneNumberTxt.setText(messages.mobileNo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add){
            // Todo: change the add as save
            String name = nameTxt.getText().toString();
            String number = phoneNumberTxt.getText().toString();
            String message = messageTxt.getText().toString();
            updateMessage(messages.id, name, number, message);
            return true;
        } else{
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMessage(String id, String name, String phoneNumber, String messageText) {
        Messages messages = new Messages();
        messages.name = name;
        messages.id= id;
        messages.mobileNo = phoneNumber;
        messages.message = messageText;

        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<Void> call = service.updateMessage(id, messages);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated the data");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update the data");
            }
        });
    }
}
