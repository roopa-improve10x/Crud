package com.example.crud.messages;

import com.example.crud.messages.Messages;

public interface OnItemActionListener {

    void onEdit(Messages messages);

    void onDelete(String id);
}
