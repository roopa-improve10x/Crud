package com.example.crud.templates;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

    TextView templateTxt;
    ImageButton templateCancelBtn;

    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        templateTxt = itemView.findViewById(R.id.template_txt);
        templateCancelBtn = itemView.findViewById(R.id.template_cancel_btn);
    }
}
