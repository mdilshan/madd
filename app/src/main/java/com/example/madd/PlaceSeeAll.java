package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Button;

import com.example.madd.adapter.AllGuideAdapter;
import com.example.madd.adapter.AllPlaceAdapter;
import com.example.madd.adapter.TopPlacesAdapter;
import com.example.madd.model.AllGuideData;
import com.example.madd.model.AllPlaceData;
import com.example.madd.model.TopPlacesData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class PlaceSeeAll extends AppCompatActivity {
   // private static final String TAG = "PlaceSeeAll";
    RecyclerView allPlaceRecycler;
    AllPlaceAdapter allPlaceAdapter;
    FirebaseFirestore myDB;
    List<AllPlaceData> allPlaceDataList = new ArrayList<>();
    SearchView search_see_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_see_all);

        allPlaceRecycler=findViewById(R.id.see_all_places_recycler);
        search_see_all = findViewById(R.id.search_see_all);

        myDB = FirebaseFirestore.getInstance();
        readData();
        ImageView Bck_bttn = findViewById(R.id.back_btn2);
        Bck_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bottomnav();
    }

    void readData() {
        myDB.collection("places").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                allPlaceDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    allPlaceDataList.add(new AllPlaceData(doc.getId(),doc.getString("place_name"),doc.getString("place_location"),doc.getString("rating"),doc.getString("imageUrl")));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlaceSeeAll.this, RecyclerView.VERTICAL, false);
                allPlaceRecycler.setLayoutManager(layoutManager);
                allPlaceAdapter = new AllPlaceAdapter(PlaceSeeAll.this, allPlaceDataList);
                allPlaceRecycler.setAdapter(allPlaceAdapter);
            }
        });
        if(search_see_all != null){
            search_see_all.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        List<AllPlaceData> searchPlaceDataList = new ArrayList<>();
        for (AllPlaceData object : allPlaceDataList){
            if(object.getCityName().toLowerCase().contains(str.toLowerCase())){
                searchPlaceDataList.add(object);
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlaceSeeAll.this, RecyclerView.VERTICAL, false);
            allPlaceRecycler.setLayoutManager(layoutManager);
            allPlaceAdapter = new AllPlaceAdapter(PlaceSeeAll.this, searchPlaceDataList);
            allPlaceRecycler.setAdapter(allPlaceAdapter);
        }
    }
    public void bottomnav() {
        final Activity A = PlaceSeeAll.this;
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

    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

}