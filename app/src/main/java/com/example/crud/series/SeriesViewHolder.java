package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    ImageView seriesImgView;
    TextView seriesImgTxt;
    ImageButton seriesCancelBtn;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);
        seriesImgTxt = itemView.findViewById(R.id.series_image_txt);
        seriesImgView = itemView.findViewById(R.id.series_img_view);
        seriesCancelBtn = itemView.findViewById(R.id.series_cancel_btn);
    }
}
