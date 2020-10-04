package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.madd.adapter.RecentHotelAdapter;
import com.example.madd.adapter.TopHotelsAdapter;
import com.example.madd.model.RecentHotelData;
import com.example.madd.model.TopHotelsData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class HotelMainPage extends AppCompatActivity {
    FirebaseFirestore myDB;
    Button SeeAll;
    ImageButton AddHotel,home,place,guide,hotel;
    RecyclerView recentRecycler, topHotelsRecycler;
    RecentHotelAdapter recentHotelAdapter;
    TopHotelsAdapter topHotelsAdapters;
    List<RecentHotelData> hotelRecentsDataList = new ArrayList<>();
    List<TopHotelsData> hotelTopDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_main_page);


        SeeAll = findViewById(R.id.btnSeeAll);
        AddHotel = findViewById(R.id.btnHotelAdd1);
        recentRecycler = findViewById(R.id.recent_recycler);
        topHotelsRecycler = findViewById(R.id.all_hotels_recycler);

        home = findViewById(R.id.btnHome);
        place = findViewById(R.id.btnPlace);
        guide = findViewById(R.id.btnGuide);
        hotel = findViewById(R.id.btnHotel);


        myDB = FirebaseFirestore.getInstance();
        readHotelRecentsData();
       readHotelTopData();

        AddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelMainPage.this,AddNewHotel.class);
                startActivity(intent);
            }
        });

        SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelMainPage.this, HotelSeeAll.class);
                startActivity(intent);
            }
        });

//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HotelMainPage.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        place.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HotelMainPage.this,PlaceActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        guide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HotelMainPage.this,GuideHome.class);
//                startActivity(intent);
//            }
//        });
//
//        hotel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(HotelMainPage.this,HotelMainPage.class);
//            }
//        });

    }
        void readHotelRecentsData(){
            myDB.collection("hotels").orderBy("joined_on", Query.Direction.DESCENDING).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent( QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                    if(e!=null)
                        toastResult(e.getMessage());
                    hotelRecentsDataList.clear();
                    for(DocumentSnapshot doc : documentSnapshots){
                        hotelRecentsDataList.add(new RecentHotelData(doc.getId(),doc.getString("hotel_name"),doc.getString("location"),R.drawable.hotel2));
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HotelMainPage.this,RecyclerView.HORIZONTAL,false);
                    recentRecycler.setLayoutManager(layoutManager);
                    recentHotelAdapter = new RecentHotelAdapter(HotelMainPage.this,hotelRecentsDataList);
                    recentRecycler.setAdapter(recentHotelAdapter);
                }
            });
        }

        void readHotelTopData(){
            myDB.collection("hotels").orderBy("rating", Query.Direction.DESCENDING).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent( QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                    if(e!=null)
                        toastResult(e.getMessage());
                    hotelTopDataList.clear();
                    for(DocumentSnapshot doc : documentSnapshots){
                        hotelTopDataList.add(new TopHotelsData(doc.getId(),doc.getString("hotel_name"),doc.getString("location"),R.drawable.hotel2));
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HotelMainPage.this,RecyclerView.HORIZONTAL,false);
                    topHotelsRecycler.setLayoutManager(layoutManager);
                    topHotelsAdapters = new TopHotelsAdapter(HotelMainPage.this,hotelTopDataList);
                    topHotelsRecycler.setAdapter(topHotelsAdapters);
                }
            });
        }

//
//     List<RecentHotelData> recentHotelDataList = new ArrayList<>();
//        recentHotelDataList.add(new RecentHotelData("New Grand", "Chilaw", "From Rs.12,000", R.drawable.hotel10));
//        recentHotelDataList.add(new RecentHotelData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel2));
//         recentHotelDataList.add(new RecentHotelData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel1));
//        recentHotelDataList.add(new RecentHotelData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
//        recentHotelDataList.add(new RecentHotelData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
//        recentHotelDataList.add(new RecentHotelData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
//
//        setRecentRecycler(recentHotelDataList);
//
//        List<TopHotelsData> topPlacesDataList = new ArrayList<>();
//        topPlacesDataList.add(new TopHotelsData("The Ballroom","Matale","From Rs.500",R.drawable.hotel5));
//        topPlacesDataList.add(new TopHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
//        topPlacesDataList.add(new TopHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel11));
//        topPlacesDataList.add(new TopHotelsData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel3));
//         topPlacesDataList.add(new TopHotelsData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel4));
//
//        setTopHotelsRecycler(topPlacesDataList);


    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

  //  }
//
//    private  void setRecentRecycler(List<RecentHotelData> recentsDataList){
//
//        recentRecycler = findViewById(R.id.recent_recycler);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        recentRecycler.setLayoutManager(layoutManager);
//        recentHotelAdapter = new RecentHotelAdapter(this, recentsDataList);
//        recentRecycler.setAdapter(recentHotelAdapter);
//
//    }
//
//   private  void setTopHotelsRecycler(List<TopHotelsData> topHotelsDataList){
//
//    topHotelsRecycler = findViewById(R.id.all_hotels_recycler);
//       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//       topHotelsRecycler.setLayoutManager(layoutManager);
//       topHotelsAdapters = new TopHotelsAdapter(this, topHotelsDataList);
//       topHotelsRecycler.setAdapter(topHotelsAdapters);
//
//   }
}