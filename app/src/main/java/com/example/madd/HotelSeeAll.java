package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
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
    List<AllHotelsData> allHotelDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_see_all);

        allHotelRecycler = findViewById(R.id.all_hotels_recycler);

        myDB = FirebaseFirestore.getInstance();
        readData();
    }

    void readData(){
        myDB.collection("hotels").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null)
                    toastResult(e.getMessage());
                    allHotelDataList.clear();
                    for (DocumentSnapshot doc : documentSnapshots){
                        allHotelDataList.add(new AllHotelsData(doc.getId(),doc.getString("hotel_name"),doc.getString("about"),doc.getString("location"),doc.getString("rating"),R.drawable.hotel2));
                    }

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HotelSeeAll.this, RecyclerView.VERTICAL, false);
                 allHotelRecycler.setLayoutManager(layoutManager);
                    allHotelsAdapter = new AllHotelsAdapter(HotelSeeAll.this, allHotelDataList);
                allHotelRecycler.setAdapter(allHotelsAdapter);
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