package com.example.madd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GuideEdit extends AppCompatActivity {

    Button editsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_edit);

        editsubmit = findViewById(R.id.editsubmit);

        editsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideEdit.this, GuideDetails.class);
                startActivity(intent);
            }
        });
    }
}