package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

Button Hotel,addHotel;


    //Button home;
    TextView GuideBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GuideBtn = findViewById(R.id.guideBtn);

        Hotel = findViewById(R.id.hotels);
        //addHotel = findViewById(R.id.btnAddHotelm);

        Hotel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HotelMainPage.class); //HotelMainPage.class
                startActivity(intent);
            }
        });


//        addHotel.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            Intent intent = new Intent(MainActivity.this, AddNewHotel.class);
//                                        }
//                                    });
    /* SwaythaView
        home=findViewById(R.id.button2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
<<<<<<< HEAD
*/


       // GuideBtn.setOnClickListener(new View.OnClickListener() {


//        place.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
//
//                startActivity(intent);
//            }
//        });
//
//
//        guides.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, GuideHome.class);
//
//                startActivity(intent);
//            }
//        });
//    }

//}
//}


        }
    }
=======

                startActivity(intent);
            }
        });


        guides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GuideHome.class);

                startActivity(intent);
            }
        });
    }
}


>>>>>>> 4a5653173b4ba4aef4802f4ecc709c027e2727ec
