package com.example.madd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.madd.adapter.GuideRecentsAdapter;
import com.example.madd.adapter.GuideTopAdapter;
import com.example.madd.model.GuideRecentsData;
import com.example.madd.model.GuidesTopData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class GuideHome extends AppCompatActivity {

    FirebaseFirestore myDB;

    GuideRecentsAdapter guideRecentsAdapter;
    GuideTopAdapter guideTopAdapter;
    TextView SeeAll;
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
        myDB = FirebaseFirestore.getInstance();
        readGuideRecentsData();
        readGuideTopData();
    }

    void readGuideRecentsData() {
        myDB.collection("guides").orderBy("joined_on", Query.Direction.DESCENDING).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                guideRecentsDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    guideRecentsDataList.add(new GuideRecentsData(doc.getId(),doc.getString("guide_name"),doc.getString("place"),R.drawable.hotel2));
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
                    guidesTopDataList.add(new GuidesTopData(doc.getId(),doc.getString("guide_name"),doc.getString("place"),doc.getString("rating"),R.drawable.hotel2));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GuideHome.this, RecyclerView.VERTICAL, false);
                guideTopRecycler.setLayoutManager(layoutManager);
                guideTopAdapter = new GuideTopAdapter(GuideHome.this, guidesTopDataList);
                guideTopRecycler.setAdapter(guideTopAdapter);
            }
        });
    }
    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}