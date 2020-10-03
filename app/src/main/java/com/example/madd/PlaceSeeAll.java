package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class PlaceSeeAll extends AppCompatActivity {
    private static final String TAG = "PlaceSeeAll";
    RecyclerView allPlaceRecycler;
    AllPlaceAdapter allPlaceAdapter;
    FirebaseFirestore myDB;
    List<AllPlaceData> allPlaceDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_see_all);

        allPlaceRecycler=findViewById(R.id.see_all_places_recycler);

        myDB = FirebaseFirestore.getInstance();
        readData();

    }
    void readData() {
        myDB.collection("places").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                allPlaceDataList.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    Log.d(TAG, "onEvent: DOC Place NAME " + doc.getString("place_name") );
                    Log.d(TAG, "onEvent: ID ====================== " + doc.getId());
                    allPlaceDataList.add(new AllPlaceData(doc.getId(),doc.getString("place_name"),doc.getString("place_location"),"140KM",doc.getString("rating"),R.drawable.hotel2));
//                    allGuideDataList.add(new AllGuideData(doc.getString("description"),doc.getString("location"),doc.getString("name"),doc.getString("name"),R.drawable.hotel2));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PlaceSeeAll.this, RecyclerView.VERTICAL, false);
                allPlaceRecycler.setLayoutManager(layoutManager);
                allPlaceAdapter = new AllPlaceAdapter(PlaceSeeAll.this, allPlaceDataList);
                allPlaceRecycler.setAdapter(allPlaceAdapter);
            }
        });

    }

    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}