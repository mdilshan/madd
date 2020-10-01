package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.madd.model.PlaceDto;
import com.example.madd.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditPlaces extends AppCompatActivity {
    private static final String TAG = "EditPlaces";
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_places);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        updatebtn= findViewById(R.id.updateSubmit);
        final EditText placeName = (EditText) findViewById(R.id.editPlace);
        final EditText placeLocation = (EditText) findViewById(R.id.editPlaceLocation);
        final EditText placeDescription = (EditText) findViewById(R.id.editPlaceLocation);
        final EditText placeURL = (EditText) findViewById(R.id.editURL);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(EditPlaces.this, DetailsActivity.class);
                startActivity(intent);
            }

        });
    }
}
