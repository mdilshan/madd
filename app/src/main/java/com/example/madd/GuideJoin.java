package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuideJoin extends AppCompatActivity {

    Button joinsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_join);

        joinsubmit = findViewById(R.id.joinsubmit);

        joinsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideJoin.this, GuideDetails.class);
                startActivity(intent);
            }
        });
    }
}