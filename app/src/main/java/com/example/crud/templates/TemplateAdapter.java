package com.example.crud.templates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateViewHolder> {
    //Todo: rename class name templatesAdapter

    private List<Templates> templateList;

    private OnItemActionListener onItemActionListener;

    public void setData(List<Templates> templateList){
        this.templateList = templateList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item, parent, false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(view);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Templates templates = templateList.get(position);
        holder.templateTxt.setText(templates.messageText);
        holder.templateCancelBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(templates.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(templates);
        });
    }

    @Override
    public int getItemCount() {
        return templateList.size();
    }
}
