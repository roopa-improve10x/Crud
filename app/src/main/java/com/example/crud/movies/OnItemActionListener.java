package com.example.crud.movies;

public interface OnItemActionListener {
    void onEdit(Movie movie);

    void onDelete(String id);
}
