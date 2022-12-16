package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardItemsActivity extends AppCompatActivity {
    public ArrayList<DashboardItem> dashboardItems;
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
        dashboardItems = new ArrayList<>();
        DashboardItem messages = new DashboardItem();
        messages.dashboardName = "Messages";
        messages.dashboardImageUrl = "https://images.macrumors.com/t/xhjvyZvcvdWDIi846TArb8MFzzk=/1600x/article-new/2020/07/messagesicon-200x200.png";
        dashboardItems.add(messages);

        DashboardItem templates = new DashboardItem();
        templates.dashboardImageUrl = "https://play-lh.googleusercontent.com/ZspusY01v4jlKuMuVikb0WjA-94plHCBvMJZVd3u59W_Kg1I4TPV3-HgB4VE1exhmfI";
        templates.dashboardName = "Templates";
        dashboardItems.add(templates);

        DashboardItem series = new DashboardItem();
        series.dashboardImageUrl = "https://www.peacocktv.com/dam/growth/assets/seo/HarryPotter/Franchise/hp-ss-min.png";
        series.dashboardName = "Series";
        dashboardItems.add(series);

        DashboardItem movies = new DashboardItem();
        movies.dashboardImageUrl = "https://nbcpalmsprings.com/wp-content/uploads/sites/8/2021/12/BEST-MOVIES-OF-2021.jpeg";
        movies.dashboardName = "Movies";
        dashboardItems.add(movies);
    }

    public void setDashboardRv() {
        // Todo: change the name as setupDashboardItemsRv
        dashboardRv = findViewById(R.id.dashboard_rv);
        // Todo: create a separate method for id's
        dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setData(dashboardItems);
        dashboardRv.setAdapter(dashboardAdapter);
    }
}