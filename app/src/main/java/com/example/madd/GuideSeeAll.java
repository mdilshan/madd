package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
                    allGuideDataList.add(new AllGuideData(doc.getId(),doc.getString("guide_name"),doc.getString("place"),doc.getString("price"),doc.getString("rating"),R.drawable.hotel2));
//                    allGuideDataList.add(new AllGuideData(doc.getString("description"),doc.getString("location"),doc.getString("name"),doc.getString("name"),R.drawable.hotel2));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GuideSeeAll.this, RecyclerView.VERTICAL, false);
                allGuideRecycler.setLayoutManager(layoutManager);
                allGuidesAdapter = new AllGuideAdapter(GuideSeeAll.this, allGuideDataList);
                allGuideRecycler.setAdapter(allGuidesAdapter);
            }
        });

    }
    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
