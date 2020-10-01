package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HotelDetails extends AppCompatActivity {

    ImageButton HotelEdit,HotelDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        HotelEdit = findViewById(R.id.btnHotelEdit);
        HotelDelete = findViewById(R.id.btnHotelDelete);

        HotelEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelDetails.this,EditHotel.class);
                startActivity(intent);
            }
        });

    }
}