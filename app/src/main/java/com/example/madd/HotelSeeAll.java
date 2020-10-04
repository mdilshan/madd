package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.madd.adapter.AllHotelsAdapter;
import com.example.madd.model.AllHotelsData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class HotelSeeAll extends AppCompatActivity {
    FirebaseFirestore myDB;
    RecyclerView allHotelRecycler;
    AllHotelsAdapter allHotelsAdapter;
    ImageButton home,place,guide,hotel,backbtn;
    List<AllHotelsData> allHotelDataList = new ArrayList<>();
    SearchView search_hotels_seeall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_see_all);

        allHotelRecycler = findViewById(R.id.all_hotels_recycler);

        search_hotels_seeall = findViewById(R.id.search_hotel_seeall);
        backbtn = findViewById(R.id.back_btn);
        myDB = FirebaseFirestore.getInstance();
        readData();
        bottomnav(); 
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelSeeAll.this,HotelMainPage.class);
                startActivity(intent);
            }
        });
    }

    void readData(){
        myDB.collection("hotels").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(QuerySnapshot documentSnapshots,FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                allHotelDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots)
                    allHotelDataList.add(new AllHotelsData(doc.getId(), doc.getString("hotel_name"), doc.getString("location"), doc.getString("rating"), doc.getString("image")));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HotelSeeAll.this, RecyclerView.VERTICAL, false);
                 allHotelRecycler.setLayoutManager(layoutManager);
                 allHotelsAdapter = new AllHotelsAdapter(HotelSeeAll.this, allHotelDataList);
                 allHotelRecycler.setAdapter(allHotelsAdapter);
            }
        });

        if(search_hotels_seeall != null){
            search_hotels_seeall.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return true;
            }
        });
    }
}
    public void search(String str){
        List<AllHotelsData> searchHotelDataList = new ArrayList<>();
        for (AllHotelsData object : allHotelDataList){
            if(object.getLocation().toLowerCase().contains(str.toLowerCase())){
                searchHotelDataList.add(object);
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HotelSeeAll.this, RecyclerView.VERTICAL, false);
            allHotelRecycler.setLayoutManager(layoutManager);
            allHotelsAdapter = new AllHotelsAdapter(HotelSeeAll.this, searchHotelDataList);
            allHotelRecycler.setAdapter(allHotelsAdapter);
        }
    }

    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    public void bottomnav() {
        final Activity A = HotelSeeAll.this;
        ImageView home_btn_nav1 =  (ImageView)findViewById(R.id.home_btn_nav);
        ImageView guide_btn_nav1 =(ImageView)findViewById(R.id.guide_btn_nav);
        ImageView places_btn_nav1 =(ImageView)findViewById(R.id.places_btn_nav);
        ImageView hotel_btn_nav1 = (ImageView)findViewById(R.id.hotel_btn_nav);

        home_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A,MainActivity.class);
                startActivity(intent);
            }
        });
        guide_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A,GuideHome.class);
                startActivity(intent);
            }
        });
        places_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A, PlaceActivity.class);
                startActivity(intent);
            }
        });
        hotel_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A, HotelMainPage.class);
                startActivity(intent);
            }
        });
    }

}





//        List<AllHotelsData> allHotelDataList = new ArrayList<>();
//        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel2));
//        allHotelDataList.add(new AllHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel1));
//        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
//        allHotelDataList.add(new AllHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
//        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
//        allHotelDataList.add(new AllHotelsData("The Ballroom","Matale","From Rs.500",R.drawable.hotel5));
//        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
//        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
//        allHotelDataList.add(new AllHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
//        allHotelDataList.add(new AllHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
//        allHotelDataList.add(new AllHotelsData("New Grand", "Chilaw", "From Rs.12,000", R.drawable.hotel10));
//
//
//        setAllHotelsRecycler(allHotelDataList);
//    }

//    private  void setAllHotelsRecycler(List<AllHotelsData> allHotelsDataList){
//
//        allHotelRecycler = findViewById(R.id.all_hotels_recycler);
//
//
//    }
//}