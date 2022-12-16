package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.movies.MovieListActivity;
import com.example.crud.series.SeriesListActivity;
import com.example.crud.templates.TemplatesActivity;
import com.example.crud.messages.MessagesActivity;
import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardViewHolder>{
    // Todo: change the class name as DashboardItemsAdapter
    // Todo: change the class viewHolder name as DashboardItemViewHolder

    public List<DashboardItem> dashboardArrayList;
    // Todo: change the dashboardArrayList as dashboardItemList

    public void setData(List<DashboardItem> dashboards){
        // Todo: use this keyword
        dashboardArrayList = dashboards;
        // Todo: change dashboards as dashboardItemList
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_layout_item, parent, false);
        DashboardViewHolder dashboardViewHolder = new DashboardViewHolder(view);
        return dashboardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        DashboardItem dashboard = dashboardArrayList.get(position);
        holder.dashboardTxt.setText(dashboard.dashboardName);
        Picasso.get().load(dashboard.dashboardImageUrl).into(holder.dashboardImg);
        holder.itemLayout.setOnClickListener(view -> {
           if(holder.dashboardTxt.getText().toString().equalsIgnoreCase("Messages")) {
               Intent intent = new Intent(holder.itemView.getContext(), MessagesActivity.class);
               holder.itemView.getContext().startActivity(intent);
           } else if(holder.dashboardTxt.getText().toString().equalsIgnoreCase("Templates")) {
               Intent intent = new Intent(holder.itemView.getContext(), TemplatesActivity.class);
               holder.itemView.getContext().startActivity(intent);
           } else if(holder.dashboardTxt.getText().toString().equalsIgnoreCase("Series")){
               Intent intent = new Intent(holder.itemView.getContext(), SeriesListActivity.class);
                holder.itemView.getContext().startActivity(intent);
           } else {
               holder.dashboardTxt.getText().toString().equalsIgnoreCase("Movies");
               Intent intent = new Intent(holder.itemView.getContext(), MovieListActivity.class);
               holder.itemView.getContext().startActivity(intent);
           }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardArrayList.size();
    }
}
