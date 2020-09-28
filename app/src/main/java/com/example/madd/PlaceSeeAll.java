package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.madd.adapter.TopPlacesAdapter;
import com.example.madd.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;

public class PlaceSeeAll extends AppCompatActivity {

    RecyclerView topPlacesRecycler;
    TopPlacesAdapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_see_all);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Narigama Beach","Hikkaduwa","Rs.2000 - Rs.3000",R.drawable.slide1));
        topPlacesDataList.add(new TopPlacesData("Adams Peak","Ratnapura","Rs.2500 - Rs.3400",R.drawable.slide2));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.slide1));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.slide2));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.slide1));

        setTopPlacesRecycler(topPlacesDataList);
    }
    private  void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.see_all_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
}