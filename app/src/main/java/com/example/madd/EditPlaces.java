package com.example.madd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditPlaces extends AppCompatActivity {

    Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_places);

        updatebtn= findViewById(R.id.updateSubmit);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPlaces.this, DetailsActivity.class);
                startActivity(intent);
            }

        });
    }
}
