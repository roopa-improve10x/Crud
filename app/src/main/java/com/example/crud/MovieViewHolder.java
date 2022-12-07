package com.example.crud;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView movieImgView;
    TextView movieNameTxt;
    ImageButton movieCancelBtn;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImgView = itemView.findViewById(R.id.movie_img_view);
        movieNameTxt = itemView.findViewById(R.id.movie_name_txt);
        movieCancelBtn = itemView.findViewById(R.id.movie_cancel_btn);
    }
}
