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
    TextView guides;
    TextView places;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guides = findViewById(R.id.guideBtn);

        Hotel = findViewById(R.id.hotels);
        places = findViewById(R.id.places_btn);
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


        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
                startActivity(intent);
           }
        });


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


//        }
//    }
//
//
//                startActivity(intent);
//            }
//        });


        guides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GuideHome.class);

                startActivity(intent);
            }
        });
    }
}



