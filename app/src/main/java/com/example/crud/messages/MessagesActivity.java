package com.example.crud.messages;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {

    private ArrayList<Messages> messages = new ArrayList<>();
    private RecyclerView messagesRv;
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        log("onCreate method started");
        getSupportActionBar().setTitle("Messages");
        setupMessageItemsRv();
    }

    @Override
    protected void onResume() {
        log("onResume");
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        log("fetchMessages API started");
        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<List<Messages>> call = service.fetchMessages();
        call.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                log("fetchMessages called");
                List<Messages> messages = response.body();
                messagesAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {
                showToast("Failed to load the data");
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
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return  super.onOptionsItemSelected(item);
        }
    }

    private void setupMessageItemsRv() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messages);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onEdit(Messages messages) {
                updateMessage(messages);
            }

            @Override
            public void onDelete(String id) {
               deleteMessage(id);
            }
        });
        messagesRv.setAdapter(messagesAdapter);
    }

    private void deleteMessage(String id) {
        CrudApi api = new CrudApi();
        CrudService service = api.createCrudService();
        Call<Void> call = service.deleteMessages(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showLongToast("Successfully deleted the message");
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to load the data");
            }
        });
    }

    private void updateMessage(Messages messages) {
        Intent intent = new Intent(this, EditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, messages);
        startActivity(intent);
    }
}