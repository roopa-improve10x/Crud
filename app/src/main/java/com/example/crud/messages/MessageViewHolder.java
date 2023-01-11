package com.example.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.ActivityMessagesBinding;
import com.example.crud.databinding.MessageLayoutItemBinding;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    MessageLayoutItemBinding binding;

    public MessageViewHolder(MessageLayoutItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
