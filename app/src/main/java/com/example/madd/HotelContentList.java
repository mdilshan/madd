package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.madd.adapter.RecentsAdapter;
import com.example.madd.model.RecentData;

import java.util.ArrayList;
import java.util.List;

public class HotelContentList extends AppCompatActivity {

    RecyclerView recentRecycler;
    RecentsAdapter recentsAdapter;

//    public HotelContentList(RecyclerView recentRecycler, RecentsAdapter recentsAdapter) {
//        this.recentRecycler = recentRecycler;
//        this.recentsAdapter = recentsAdapter;
//    }

//    public HotelContentList(int contentLayoutId, RecyclerView recentRecycler, RecentsAdapter recentsAdapter) {
//        super(contentLayoutId);
//       // this.recentRecycler = recentRecycler;
//        //this.recentsAdapter = recentsAdapter;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_content_list);

       // List<RecentData> recentDataList = new ArrayList<>();
        //recentDataList.add(new RecentData("New Grand","Chilaw","From Rs.12,000",R.drawable.display_pic_php));
        //recentDataList.add(new RecentData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel2));
       // recentDataList.add(new RecentData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel1));
        //recentDataList.add(new RecentData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel2));
        //recentDataList.add(new RecentData("New Grand","Chilaw","From Rs.12,000",R.drawable.hotel1));
        //recentDataList.add(new RecentData("Fresh Up","Hambantota","From Rs.7499",R.drawable.hotel2));

        //setRecentRecycler(recentDataList);

    }

    //private void setRecentRecycler(List<RecentData> recentDataList){

        //ecentRecycler = findViewById(R.id.recent_);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
      //  recentRecycler.setLayoutManager(layoutManager);
      //recentsAdapter = new RecentsAdapter(this,recentDataList);
        //recentRecycler.setAdapter(recentsAdapter);
    }

//}