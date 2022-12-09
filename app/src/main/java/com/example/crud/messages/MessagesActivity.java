package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {
    private ArrayList<Messages> messages = new ArrayList<>();
    private RecyclerView messagesRv;
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Log.i("MessagesActivity", "onCreate method called");
        getSupportActionBar().setTitle("Messages");
        setUpMessagesRv();
    }

    private void setUpToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateMessages(Messages messages) {
        Intent intent = new Intent(this, AddEditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, messages);
        startActivity(intent);
    }

    private void deleteMessage(String id) {
        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<Void> call = service.deleteMessages(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                setUpToast("Successfully delete the data");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                setUpToast("Failed to load the data");
            }
        });

    }

    @Override
    protected void onResume() {
        Log.i("MessagesActivity", "onResume method called");
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        Log.i("MessagesActivity", "fetch messages API started");
        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<List<Messages>> call = service.fetchMessages();
        call.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                Log.i("MessagesActivity", "fetch messages called");
                List<Messages> messages = response.body();
                messageAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {
                setUpToast("Failed to load the data");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent intent = new Intent(this, AddEditMessageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return  super.onOptionsItemSelected(item);
        }
    }

    private void setUpMessagesRv() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageAdapter.setData(messages);
        messageAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onEdit(Messages messages) {
                updateMessages(messages);
            }

            @Override
            public void onDelete(String id) {
               deleteMessage(id);
            }
        });
        messagesRv.setAdapter(messageAdapter);
    }
}