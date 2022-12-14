package com.example.crud.templates;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.api.CrudApi;
import com.example.crud.api.CrudService;
import com.example.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected EditText templateMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        log("onCreate");
        inItViews();
    }

    private void inItViews() {
        templateMessageTxt = findViewById(R.id.template_message_txt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }
}