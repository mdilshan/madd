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
import android.widget.TextView;
import android.widget.Toast;

import com.example.madd.adapter.RecentHotelAdapter;
import com.example.madd.adapter.TopHotelsAdapter;
import com.example.madd.model.RecentHotelData;
import com.example.madd.model.TopHotelsData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    TextView SeeAll;
    ImageButton home,place,guide,hotel;
    RecyclerView hotel_recentRecycler, topHotelsRecycler;
    RecentHotelAdapter recentHotelAdapter;
    TopHotelsAdapter topHotelsAdapters;
    List<RecentHotelData> hotelRecentDataList = new ArrayList<>();
    List<TopHotelsData> hotelTopDataList = new ArrayList<>();
    FloatingActionButton add_hotel ;
    EditText search_hotels_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_main_page);


        SeeAll = findViewById(R.id.btnSeeAll);
        //AddHotel = findViewById(R.id.btnHotelAdd1);
        hotel_recentRecycler = findViewById(R.id.recent_recycler);
        topHotelsRecycler = findViewById(R.id.top_hotels_recycler);

       // home = findViewById(R.id.btnHome);
       // place = findViewById(R.id.btnPlace);
       // guide = findViewById(R.id.btnGuide);
      //  hotel = findViewById(R.id.btnHotel);
        add_hotel = findViewById(R.id.add_hotel);
        search_hotels_home = findViewById(R.id.search_hotel_home);

        search_hotels_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search_hotels_home.getWindowToken(), 0);
                Intent intent = new Intent(HotelMainPage.this, HotelSeeAll.class);
                startActivity(intent);
            }
        });

        bottomnav();


        SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelMainPage.this, HotelSeeAll.class);
                startActivity(intent);
            }
        });
        myDB = FirebaseFirestore.getInstance();
        readHotelRecentsData();
        readHotelTopData();

        add_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelMainPage.this,AddNewHotel.class);
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
                    hotelRecentDataList.clear();
                    for(DocumentSnapshot doc : documentSnapshots){
                        hotelRecentDataList.add(new RecentHotelData(doc.getId(),doc.getString("hotel_name"),doc.getString("location"),doc.getString("image")));
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HotelMainPage.this,RecyclerView.HORIZONTAL,false);
                    hotel_recentRecycler.setLayoutManager(layoutManager);
                    recentHotelAdapter = new RecentHotelAdapter(HotelMainPage.this,hotelRecentDataList);
                    hotel_recentRecycler.setAdapter(recentHotelAdapter);
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
                        hotelTopDataList.add(new TopHotelsData(doc.getId(),doc.getString("hotel_name"),doc.getString("location"),doc.getString("rating"),doc.getString("image")));
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HotelMainPage.this,RecyclerView.VERTICAL,false);
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


    public void bottomnav() {
        final Activity A = HotelMainPage.this;
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