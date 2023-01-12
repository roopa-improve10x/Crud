package com.example.crud.messages;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.databinding.MessageLayoutItemBinding;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Messages> messagesList;

    private OnItemActionListener onItemActionListener;

    public void setData(List<Messages> messagesList){
        this.messagesList = messagesList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener){
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageLayoutItemBinding binding = MessageLayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(binding);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Messages messages = messagesList.get(position);
        holder.binding.setMessage(messages);
        holder.binding.cancelImgBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(messages.id);
        });
        holder.binding.cancelImgBtn.setOnClickListener(view -> {
            onItemActionListener.onEdit(messages);
        });
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
