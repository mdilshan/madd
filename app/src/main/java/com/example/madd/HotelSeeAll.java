package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.madd.adapter.AllHotelsAdapter;
import com.example.madd.adapter.TopHotelsAdapter;
import com.example.madd.model.RecentData;
import com.example.madd.model.TopHotelsData;
import com.example.madd.model.AllHotelsData;

import java.util.ArrayList;
import java.util.List;

public class HotelSeeAll extends AppCompatActivity {

    RecyclerView allHotelRecycler;
    AllHotelsAdapter allHotelsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_see_all);


        List<AllHotelsData> allHotelDataList = new ArrayList<>();
        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel2));
        allHotelDataList.add(new AllHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel1));
        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
        allHotelDataList.add(new AllHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
        allHotelDataList.add(new AllHotelsData("The Ballroom","Matale","From Rs.500",R.drawable.hotel5));
        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
        allHotelDataList.add(new AllHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
        allHotelDataList.add(new AllHotelsData("New Grand", "Chilaw", "From Rs.12,000", R.drawable.hotel10));


        setAllHotelsRecycler(allHotelDataList);
    }

    private  void setAllHotelsRecycler(List<AllHotelsData> allHotelsDataList){

        allHotelRecycler = findViewById(R.id.all_hotels_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        allHotelRecycler.setLayoutManager(layoutManager);
        allHotelsAdapter = new AllHotelsAdapter(this, allHotelsDataList);
        allHotelRecycler.setAdapter(allHotelsAdapter);

    }
}