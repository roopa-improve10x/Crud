package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesViewHolder> {
    //Todo: seriesListAdapter

    public List<Series> seriesArrayList;
    //Todo: rename to seriesList

    public OnItemActionListener onItemActionListener;

    public void setData(List<Series> series){
        //Todo: rename to SeriesList
        // Todo: use this keyword

        seriesArrayList = series;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListeners) {
        this.onItemActionListener = onItemActionListeners;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_layout_item, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
       Series series = seriesArrayList.get(position);
       holder.seriesImgTxt.setText(series.title);
       if(series.imageUrl != null && series.imageUrl.isEmpty() == false) {
           Picasso.get().load(series.imageUrl).into(holder.seriesImgView);
       }
       holder.seriesCancelBtn.setOnClickListener(view -> {
           onItemActionListener.onDelete(series.id);
       });
       holder.itemView.setOnClickListener(view -> {
           onItemActionListener.onEdit(series);
       });
    }
    @Override
    public int getItemCount() {
        return seriesArrayList.size();
    }
}
