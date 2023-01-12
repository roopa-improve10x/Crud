package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.databinding.SeriesLayoutItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    public List<Series> seriesList;

    public OnItemActionListener onItemActionListener;

    public void setData(List<Series> seriesList){
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListeners) {
        this.onItemActionListener = onItemActionListeners;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeriesLayoutItemBinding binding = SeriesLayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(binding);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
       Series series = seriesList.get(position);
       holder.binding.seriesImageTxt.setText(series.title);
       if(series.imageUrl != null && series.imageUrl.isEmpty() == false) {
           Picasso.get().load(series.imageUrl).into(holder.binding.seriesImgView);
       }
       holder.binding.seriesCancelBtn.setOnClickListener(view -> {
           onItemActionListener.onDelete(series.id);
       });
       holder.itemView.setOnClickListener(view -> {
           onItemActionListener.onEdit(series);
       });
    }
    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
