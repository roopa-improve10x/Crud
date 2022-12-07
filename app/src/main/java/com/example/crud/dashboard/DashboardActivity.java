package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    public ArrayList<Dashboard> dashboardList; //= new ArrayList<>();
    public RecyclerView dashboardRv;
    public DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        setUpData();
        setDashboardRv();
    }

    public void setUpData() {
        dashboardList = new ArrayList<>();
        Dashboard dashboard1 = new Dashboard();
        dashboard1.dashboardName = "Messages";
        dashboard1.dashboardImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzbpNLiKR_Of5JXkWqQ55sGukYqff5H0FiUQ&usqp=CAU";
        dashboardList.add(dashboard1);

        Dashboard dashboard2 = new Dashboard();
        dashboard2.dashboardImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzbpNLiKR_Of5JXkWqQ55sGukYqff5H0FiUQ&usqp=CAU";
        dashboard2.dashboardName = "Templates";
        dashboardList.add(dashboard2);

        Dashboard dashboard3 = new Dashboard();
        dashboard3.dashboardImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzbpNLiKR_Of5JXkWqQ55sGukYqff5H0FiUQ&usqp=CAU";
        dashboard3.dashboardName = "Series";
        dashboardList.add(dashboard3);

        Dashboard dashboard4 = new Dashboard();
        dashboard4.dashboardImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzbpNLiKR_Of5JXkWqQ55sGukYqff5H0FiUQ&usqp=CAU";
        dashboard4.dashboardName = "Movies";
        dashboardList.add(dashboard4);
    }

    public void setDashboardRv() {
        dashboardRv = findViewById(R.id.dashboard_rv);
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setData(dashboardList);
        dashboardRv.setAdapter(dashboardAdapter);
    }
}