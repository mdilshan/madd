package com.example.madd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.adapter.AllGuideAdapter;
import com.example.madd.model.AllGuideData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GuideSeeAll extends AppCompatActivity {
    FirebaseFirestore myDB;
    RecyclerView allGuideRecycler;
    AllGuideAdapter allGuidesAdapter;
    List<AllGuideData> allGuideDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_see_all);
        allGuideRecycler = findViewById(R.id.all_guides_recycler);
        myDB = FirebaseFirestore.getInstance();
        readData();

        bottomnav();
    }
    void readData() {
        myDB.collection("guides").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                allGuideDataList.clear();
//                allGuideDataList.add(new AllGuideData("Fresh Up","Hambantota","From Rs.7499","5",R.drawable.hotel2));
                for (DocumentSnapshot doc : documentSnapshots) {
                    allGuideDataList.add(new AllGuideData(doc.getId(),doc.getString("guide_name"),doc.getString("place"),doc.getString("rating"),doc.getString("imageUrl")));
//                    allGuideDataList.add(new AllGuideData(doc.getString("description"),doc.getString("location"),doc.getString("name"),doc.getString("name"),R.drawable.hotel2));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GuideSeeAll.this, RecyclerView.VERTICAL, false);
                allGuideRecycler.setLayoutManager(layoutManager);
                allGuidesAdapter = new AllGuideAdapter(GuideSeeAll.this, allGuideDataList);
                allGuideRecycler.setAdapter(allGuidesAdapter);
            }
        });
    }
    public void bottomnav() {
        Activity A = GuideSeeAll.this;
        ImageView home_btn_nav1 =  (ImageView)findViewById(R.id.home_btn_nav);
        ImageView guide_btn_nav1 =(ImageView)findViewById(R.id.guide_btn_nav);
        ImageView places_btn_nav1 =(ImageView)findViewById(R.id.places_btn_nav);
        ImageView hotel_btn_nav1 = (ImageView)findViewById(R.id.hotel_btn_nav);
        ImageButton Back  = findViewById(R.id.back_btn);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
