package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madd.adapter.RecentsAdapter;
import com.example.madd.adapter.TopPlacesAdapter;
import com.example.madd.model.RecentsData;
import com.example.madd.model.TopPlacesData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity {
    FirebaseFirestore myDB;
    FloatingActionButton join_place;
    Button btn2;
    EditText place_search_home;
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    List<RecentsData> recentsDataList =  new ArrayList<>();
    List<TopPlacesData> topPlacesDataList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        recentRecycler = findViewById(R.id.recent_recycler);
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        join_place = findViewById(R.id.join_place);

        place_search_home  = findViewById(R.id.place_search_home);
        place_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(place_search_home.getWindowToken(), 0);
                Intent intent = new Intent(PlaceActivity.this, PlaceSeeAll.class);
                startActivity(intent);
            }
        });


        btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceActivity.this, PlaceSeeAll.class);
                startActivity(intent);
            }
        });
        myDB = FirebaseFirestore.getInstance();
        readPlaceRecentsData();
        readPlaceTopData();
        bottomnav();

        join_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceActivity.this, AddPlaces.class);
                startActivity(intent);
            }
        });
    }
    void readPlaceRecentsData() {
        myDB.collection("places").orderBy("joined_on", Query.Direction.DESCENDING).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                recentsDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    recentsDataList.add(new RecentsData(doc.getId(),doc.getString("place_name"),doc.getString("place_location"),doc.getString("imageUrl")));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlaceActivity.this, RecyclerView.HORIZONTAL, false);
                recentRecycler.setLayoutManager(layoutManager);
                recentsAdapter = new RecentsAdapter(PlaceActivity.this, recentsDataList);
                recentRecycler.setAdapter(recentsAdapter);
            }
        });
    }

    void readPlaceTopData() {
        myDB.collection("places").orderBy("rating", Query.Direction.DESCENDING).limit(30).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                topPlacesDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    topPlacesDataList.add(new TopPlacesData(doc.getId(),doc.getString("place_name"),doc.getString("place_location"),doc.getString("rating"),doc.getString("imageUrl")));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlaceActivity.this, RecyclerView.VERTICAL, false);
                topPlacesRecycler.setLayoutManager(layoutManager);
                topPlacesAdapter = new TopPlacesAdapter(PlaceActivity.this, topPlacesDataList);
                topPlacesRecycler.setAdapter(topPlacesAdapter);
            }
        });
    }

    public void bottomnav() {
        final Activity A = PlaceActivity.this;
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


}
