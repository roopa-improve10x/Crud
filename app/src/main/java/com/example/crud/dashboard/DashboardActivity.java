package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    //Todo : change the class name as DashboardItemsActivity
    public ArrayList<Dashboard> dashboardList; //= new ArrayList<>();
    public RecyclerView dashboardRv;
    //Todo : change the recycler view name as dashboardItemsRv
    public DashboardAdapter dashboardAdapter;
    //Todo : change the adapter name as dashboardItemsAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        setUpData();
        setDashboardRv();
    }

    public void setUpData() {
        //Todo: in setupmethod U should be in lower case
        dashboardList = new ArrayList<>();
        Dashboard dashboard1 = new Dashboard();
        dashboard1.dashboardName = "Messages";
        dashboard1.dashboardImageUrl = "https://images.macrumors.com/t/xhjvyZvcvdWDIi846TArb8MFzzk=/1600x/article-new/2020/07/messagesicon-200x200.png";
        dashboardList.add(dashboard1);

        Dashboard dashboard2 = new Dashboard();
        dashboard2.dashboardImageUrl = "https://play-lh.googleusercontent.com/ZspusY01v4jlKuMuVikb0WjA-94plHCBvMJZVd3u59W_Kg1I4TPV3-HgB4VE1exhmfI";
        dashboard2.dashboardName = "Templates";
        dashboardList.add(dashboard2);

        Dashboard dashboard3 = new Dashboard();
        dashboard3.dashboardImageUrl = "https://www.peacocktv.com/dam/growth/assets/seo/HarryPotter/Franchise/hp-ss-min.png";
        dashboard3.dashboardName = "Series";
        dashboardList.add(dashboard3);

        Dashboard dashboard4 = new Dashboard();
        dashboard4.dashboardImageUrl = "https://nbcpalmsprings.com/wp-content/uploads/sites/8/2021/12/BEST-MOVIES-OF-2021.jpeg";
        dashboard4.dashboardName = "Movies";
        dashboardList.add(dashboard4);
    }

    public void setDashboardRv() {
        // Todo: change the name as setupDashboardItemsRv
        dashboardRv = findViewById(R.id.dashboard_rv);
        // Todo: create a separate method for id's
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setData(dashboardList);
        dashboardRv.setAdapter(dashboardAdapter);
    }
}