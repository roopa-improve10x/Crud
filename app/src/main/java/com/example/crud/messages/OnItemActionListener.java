package com.example.crud.messages;

public interface OnItemActionListener {

    void onEdit(Message messages);

    void onDelete(String id);
}
