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
import com.example.crud.databinding.ActivityAddEditMessageBinding;
import com.example.crud.databinding.ActivityAddEditTemplateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected ActivityAddEditTemplateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditTemplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }
}