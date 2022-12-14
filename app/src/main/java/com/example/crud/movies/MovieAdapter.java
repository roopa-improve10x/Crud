package com.example.crud.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    public List<Movie> movieList;

    public OnItemActionListener onItemActionListener;

    public void setData(List<Movie> movieArrayList) {
        movieList = movieArrayList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_layout_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieNameTxt.setText(movie.movieName);
        Picasso.get().load(movie.movieImageUrl).into(holder.movieImgView);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
