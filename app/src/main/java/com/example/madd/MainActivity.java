package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Hotel;

    //Button home;
    Button GuideBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Hotel = findViewById(R.id.hotel1);
        //addHotel = findViewById(R.id.btnAddHotel);

        Hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HotelMainPage.class);
                startActivity(intent);
            }
        });

//        addHotel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(MainActivity.this,AddNewHotel.class);

    /* SwaythaView
        home=findViewById(R.id.button2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
*/
        GuideBtn = findViewById(R.id.guide);

        GuideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GuideHome.class);


                startActivity(intent);
            }
        });
    }
}

