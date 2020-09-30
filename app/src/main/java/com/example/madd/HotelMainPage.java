package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.madd.adapter.RecentHotelAdapter;
import com.example.madd.adapter.TopHotelsAdapter;
import com.example.madd.model.RecentHotelData;
import com.example.madd.model.TopHotelsData;

import java.util.ArrayList;
import java.util.List;

public class HotelMainPage extends AppCompatActivity {
    Button SeeAll;
    ImageButton AddHotel;
    RecyclerView recentRecycler, topHotelsRecycler;
    RecentHotelAdapter recentHotelAdapter;
    TopHotelsAdapter topHotelsAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_main_page);

    // Now here we will add some dummy data in our model class
        SeeAll  = findViewById(R.id.btnSeeAll);
        AddHotel = findViewById(R.id.btnHotelAdd1);

        AddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelMainPage.this,AddNewHotel.class);
                startActivity(intent);
            }
        });

        SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelMainPage.this, HotelSeeAll.class);
                startActivity(intent);
            }
        });

//
     List<RecentHotelData> recentHotelDataList = new ArrayList<>();




        recentHotelDataList.add(new RecentHotelData("New Grand", "Chilaw", "From Rs.12,000", R.drawable.hotel10));
        recentHotelDataList.add(new RecentHotelData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel2));
         recentHotelDataList.add(new RecentHotelData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel1));
        recentHotelDataList.add(new RecentHotelData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
        recentHotelDataList.add(new RecentHotelData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
        recentHotelDataList.add(new RecentHotelData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));

        setRecentRecycler(recentHotelDataList);

        List<TopHotelsData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopHotelsData("The Ballroom","Matale","From Rs.500",R.drawable.hotel5));
        topPlacesDataList.add(new TopHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
        topPlacesDataList.add(new TopHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
        topPlacesDataList.add(new TopHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
         topPlacesDataList.add(new TopHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));

        setTopHotelsRecycler(topPlacesDataList);
}

    private  void setRecentRecycler(List<RecentHotelData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentHotelAdapter = new RecentHotelAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentHotelAdapter);

    }

   private  void setTopHotelsRecycler(List<TopHotelsData> topHotelsDataList){

    topHotelsRecycler = findViewById(R.id.all_hotels_recycler);
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
       topHotelsRecycler.setLayoutManager(layoutManager);
       topHotelsAdapters = new TopHotelsAdapter(this, topHotelsDataList);
       topHotelsRecycler.setAdapter(topHotelsAdapters);

   }
}