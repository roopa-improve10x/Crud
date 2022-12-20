package com.example.crud.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crud.R;
import com.example.crud.series.SeriesItem;

import java.util.List;

public class CustomSeriesAdapter extends ArrayAdapter<SeriesItem> {

    public CustomSeriesAdapter(@NonNull Context context, int resource, @NonNull List<SeriesItem> objects) {
        super(context, resource, objects);
    }

   @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.series_drop_down, parent, false);
        SeriesItem series = getItem(position);
        TextView titleTxt = view.findViewById(R.id.title_txt);
        titleTxt.setText(series.seriesId + " - " + series.title);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.series_drop_down, parent, false);
        SeriesItem series = getItem(position);
        TextView titleTxt = view.findViewById(R.id.title_txt);
        titleTxt.setText(series.seriesId + " - " + series.title);
        return view;
    }
}
