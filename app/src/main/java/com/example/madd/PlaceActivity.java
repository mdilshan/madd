package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madd.adapter.RecentsAdapter;
import com.example.madd.adapter.TopPlacesAdapter;
import com.example.madd.model.RecentsData;
import com.example.madd.model.TopPlacesData;
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
    ImageButton btn1;
    Button btn2;
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
        topPlacesRecycler = findViewById(R.id.see_all_places_recycler);
        btn1 = findViewById(R.id.btnAdd);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceActivity.this, AddPlaces.class);
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
        Activity A = PlaceActivity.this;
        ImageView home_btn_nav1 =  (ImageView)findViewById(R.id.homee);
        ImageView guide_btn_nav1 =(ImageView)findViewById(R.id.guidee);
        ImageView places_btn_nav1 =(ImageView)findViewById(R.id.placee);
        ImageView hotel_btn_nav1 = (ImageView)findViewById(R.id.hoteel);

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
