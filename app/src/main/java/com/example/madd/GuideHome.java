package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.madd.adapter.GuideRecentsAdapter;
import com.example.madd.adapter.GuideTopAdapter;
import com.example.madd.model.GuideRecentsData;
import com.example.madd.model.GuidesTopData;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class GuideHome extends AppCompatActivity {

    FirebaseFirestore myDB;
    RecyclerView guideRecentRecycler, guideTopRecycler;
    GuideRecentsAdapter guideRecentsAdapter;
    GuideTopAdapter guideTopAdapter;
    TextView SeeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_home);

        SeeAll  = findViewById(R.id.guide_see_all);

        SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideHome.this, GuideSeeAll.class);
                startActivity(intent);
            }
        });



        List<GuideRecentsData> guideRecentsDataList =  new ArrayList<>();
        guideRecentsDataList.add(new GuideRecentsData("John Cena","Colombo","500","5",R.drawable.recentimage1));
        guideRecentsDataList.add(new GuideRecentsData("Michel Jordan","Kandy","6090","4",R.drawable.recentimage2));
        guideRecentsDataList.add(new GuideRecentsData("John sd","Colombo","580","5",R.drawable.recentimage1));
        guideRecentsDataList.add(new GuideRecentsData("sd Jordan","Kandy","650","4",R.drawable.recentimage2));
        guideRecentsDataList.add(new GuideRecentsData("ds Cena","Colombo","600","5",R.drawable.recentimage1));
        guideRecentsDataList.add(new GuideRecentsData("sd sd","Kandy","650","4",R.drawable.recentimage2));

        setGuideRecentRecycler(guideRecentsDataList);

        List<GuidesTopData> guidesTopDataList =  new ArrayList<>();
        guidesTopDataList.add(new GuidesTopData("John Cena","Colombo","500","5",R.drawable.recentimage1));
        guidesTopDataList.add(new GuidesTopData("Michel Jordan","Kandy","6090","4",R.drawable.recentimage2));
        guidesTopDataList.add(new GuidesTopData("John sd","Colombo","580","5",R.drawable.recentimage1));
        guidesTopDataList.add(new GuidesTopData("sd Jordan","Kandy","650","4",R.drawable.recentimage2));
        guidesTopDataList.add(new GuidesTopData("ds Cena","Colombo","600","5",R.drawable.recentimage1));
        guidesTopDataList.add(new GuidesTopData("sd sd","Kandy","650","4",R.drawable.recentimage2));

        setGuideTopRecycler(guidesTopDataList);


    }

    private void setGuideRecentRecycler(List<GuideRecentsData> guideRecentsDataList){

        guideRecentRecycler = findViewById(R.id.guide_recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        guideRecentRecycler.setLayoutManager(layoutManager);
        guideRecentsAdapter = new GuideRecentsAdapter(this, guideRecentsDataList);
        guideRecentRecycler.setAdapter(guideRecentsAdapter);
    }
    private void setGuideTopRecycler(List<GuidesTopData> guidesTopDataList){

        guideTopRecycler = findViewById(R.id.top_guides_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        guideTopRecycler.setLayoutManager(layoutManager);
        guideTopAdapter = new GuideTopAdapter(this, guidesTopDataList);
        guideTopRecycler.setAdapter(guideTopAdapter);
    }
}