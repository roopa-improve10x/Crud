package com.example.crud.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.MovieItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    public List<Movie> movies;

    public OnItemActionListener onItemActionListener;

    void setData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(binding);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.setMovie(movie);
        if(movie.movieImageUrl != null && movie.movieImageUrl.isEmpty() == false) {
            Picasso.get().load(movie.movieImageUrl).into(holder.binding.movieImgView);
        }
        holder.binding.movieCancelBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(movie.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
