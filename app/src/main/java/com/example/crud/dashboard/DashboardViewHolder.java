package com.example.crud.dashboard;

import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

public class DashboardViewHolder extends RecyclerView.ViewHolder {
    ImageView dashboardImg;
    TextView dashboardTxt;
    View itemLayout;
    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        dashboardTxt = itemView.findViewById(R.id.dashboard_Txt);
        dashboardImg = itemView.findViewById(R.id.dashboard_image);
        itemLayout = itemView.findViewById(R.id.item_layout);
    }
}
