package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddPlaces extends AppCompatActivity {
    private static final String TAG = "AddPlaces";

    EditText PlaceName,PlaceLocation,PlaceDescription;
    Button addsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);

        addsubmit = findViewById(R.id.addSubmit);

        addsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                PlaceName = view.findViewById(R.id.etPlace);
                PlaceLocation = view.findViewById(R.id.etPlaceLocation);
                PlaceDescription = view.findViewById(R.id.etPlaceAbout);
                Log.d(TAG, "onClick: " + PlaceName);

                Map<String, Object> user = new HashMap<>();
                user.put("Place name", "Temple of Tooth Relic");
                user.put("Place location", "Kandy");
                user.put("Place Description", "Lorem Ipsum");

                // Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Intent intent = new Intent(AddPlaces.this, DetailsActivity.class);
                                startActivity(intent);
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }

        });
    }
}