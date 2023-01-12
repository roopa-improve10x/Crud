package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.SeriesLayoutItemBinding;
import com.squareup.picasso.Picasso;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    SeriesLayoutItemBinding binding;

    public SeriesViewHolder(SeriesLayoutItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
