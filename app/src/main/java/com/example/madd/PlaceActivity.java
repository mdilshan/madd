package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.madd.adapter.AllHotelsAdapter;
import com.example.madd.adapter.TopPlacesAdapter;
import com.example.madd.model.RecentsData;
import com.example.madd.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity {
    ImageButton btn1;
    Button btn2;
    RecyclerView recentRecycler, topPlacesRecycler;
    AllHotelsAdapter.RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        btn1 = findViewById(R.id.btnAdd);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceActivity.this, AddPlaces.class);
                startActivity(intent);
            }
        });


        btn2=findViewById(R.id.button3);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceActivity.this, PlaceSeeAll.class);
                startActivity(intent);
            }
        });
        // Now here we will add some dummy data in our model class

        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Temple of Tooth Relic","Kandy","From Rs.2000",R.drawable.im3));
        recentsDataList.add(new RecentsData("Angel falls","Nuwareliya","From Rs.3000",R.drawable.im2));
        recentsDataList.add(new RecentsData("Light House","Colombo","From Rs.3000",R.drawable.im1));
        recentsDataList.add(new RecentsData("Sigiriya","Sigiriya","From Rs.1500",R.drawable.im6));
        recentsDataList.add(new RecentsData("Elephant Hall","Pinnawala","From Rs.2500",R.drawable.im5));
        recentsDataList.add(new RecentsData("Thirukoneswaram Temple","Trincomalee","From Rs.4000",R.drawable.im4));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Narigama Beach","Hikkaduwa","Rs.2000 - Rs.3000",R.drawable.slide1));
        topPlacesDataList.add(new TopPlacesData("Adams Peak","Ratnapura","Rs.2500 - Rs.3400",R.drawable.slide2));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.slide1));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.slide2));
        topPlacesDataList.add(new TopPlacesData("Kasimir Hill","India","$200 - $500",R.drawable.slide1));

        setTopPlacesRecycler(topPlacesDataList);
    }

    private  void setRecentRecycler(List<RecentsData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new AllHotelsAdapter.RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private  void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.see_all_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }




}
