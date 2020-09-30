package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

Button Hotel,addHotel;


    //Button home;
    Button GuideBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Hotel = findViewById(R.id.hotels);
        addHotel = findViewById(R.id.btnAddHotelm);

        Hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HotelMainPage.class);
                startActivity(intent);
            }
        });

        addHotel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(MainActivity.this, AddNewHotel.class);
                                        }
                                    });
    /* SwaythaView
        home=findViewById(R.id.button2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
*/
        GuideBtn = findViewById(R.id.guideBtn);

        GuideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GuideHome.class);

                startActivity(intent);
            }
        });
    }
}


