package com.example.madd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.adapter.GuideRecentsAdapter;
import com.example.madd.adapter.GuideTopAdapter;
import com.example.madd.model.GuideRecentsData;
import com.example.madd.model.GuidesTopData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Guide Home Page
 * @Author - https://github.com/MhmdAsq
 */
public class GuideHome extends AppCompatActivity {

    FirebaseFirestore myDB;
    GuideRecentsAdapter guideRecentsAdapter;
    GuideTopAdapter guideTopAdapter;
    TextView SeeAll;
    EditText search_guides_home;
    List<GuideRecentsData> guideRecentsDataList =  new ArrayList<>();
    List<GuidesTopData> guidesTopDataList =  new ArrayList<>();
    RecyclerView guideRecentRecycler, guideTopRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_home);

        guideRecentRecycler = findViewById(R.id.guide_recent_recycler);
        guideTopRecycler = findViewById(R.id.top_guides_recycler);
        SeeAll  = findViewById(R.id.guide_see_all);
        SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideHome.this, GuideSeeAll.class);
                startActivity(intent);
            }
        });

        search_guides_home  = findViewById(R.id.search_guides_home);

        search_guides_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search_guides_home.getWindowToken(), 0);
                Intent intent = new Intent(GuideHome.this, GuideSeeAll.class);
                startActivity(intent);
            }
        });

        myDB = FirebaseFirestore.getInstance();
        readGuideRecentsData();
        readGuideTopData();
        bottomnav();
        FloatingActionButton join_guide = findViewById(R.id.join_guide);
        join_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideHome.this, GuideJoin.class);
                startActivity(intent);
            }
        });
    }
    void readGuideRecentsData() {
        myDB.collection("guides").orderBy("joined_on", Query.Direction.DESCENDING).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                guideRecentsDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    guideRecentsDataList.add(new GuideRecentsData(doc.getId(),doc.getString("guide_name"),doc.getString("place"),doc.getString("imageUrl")));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GuideHome.this, RecyclerView.HORIZONTAL, false);
                guideRecentRecycler.setLayoutManager(layoutManager);
                guideRecentsAdapter = new GuideRecentsAdapter(GuideHome.this, guideRecentsDataList);
                guideRecentRecycler.setAdapter(guideRecentsAdapter);
            }
        });
    }
    void readGuideTopData() {
        myDB.collection("guides").orderBy("rating", Query.Direction.DESCENDING).limit(30).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                guidesTopDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    guidesTopDataList.add(new GuidesTopData(doc.getId(),doc.getString("guide_name"),doc.getString("place"),doc.getString("rating"),doc.getString("imageUrl")));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GuideHome.this, RecyclerView.VERTICAL, false);
                guideTopRecycler.setLayoutManager(layoutManager);
                guideTopAdapter = new GuideTopAdapter(GuideHome.this, guidesTopDataList);
                guideTopRecycler.setAdapter(guideTopAdapter);
            }
        });
    }
    public void bottomnav() {
        final Activity A = GuideHome.this;
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