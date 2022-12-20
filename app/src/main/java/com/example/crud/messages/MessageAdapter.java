package com.example.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    //Todo: change the class name as MessagesAdapter

    private List<Messages> messagesList;

    private OnItemActionListener onItemActionListener;

    public void setData(List<Messages> messagesList){
        this.messagesList = messagesList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener actionListener){
        //Todo: use default modifier
        onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout_item, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Messages messages = messagesList.get(position);
        holder.nameTxt.setText(messages.name);
        holder.mobileNoTxt.setText(messages.mobileNo);
        holder.messageTxt.setText(messages.message);
        holder.cancelImgBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(messages.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(messages);
        });
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
