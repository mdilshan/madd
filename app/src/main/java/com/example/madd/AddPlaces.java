package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madd.model.PlaceDto;
import com.example.madd.model.RecentsData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddPlaces extends AppCompatActivity {
    FirebaseFirestore myDB;
    Button addsubmit;
    TextView place_Name, place_Location, place_Description, place_URL;

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);
        myDB = FirebaseFirestore.getInstance();

        addsubmit = findViewById(R.id.addSubmit);
        place_Name = findViewById(R.id.etPlace);
        place_Location = findViewById(R.id.etPlaceLocation);
        place_Description = findViewById(R.id.etPlaceAbout);
        place_URL = findViewById(R.id.etURL);

        addsubmit = findViewById(R.id.addSubmit);
        addsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddClicked(view);
            }
        });
    }

    public void onAddClicked(View view) {
        hideKeyboard(this);
        if (place_Name.getText().toString().length() > 0
                || place_Location.getText().toString().length() > 0
                || place_Description.getText().toString().length() > 0
                || place_URL.getText().toString().length() > 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("place_name", place_Name.getText().toString());
            data.put("place_location", place_Location.getText().toString());
            data.put("place_description", place_Description.getText().toString());
            data.put("imageUrl", place_URL.getText().toString());
           /* CollectionReference solarSystem = myDB.collection("myData");
            solarSystem.add(data);
            myDB.collection("myData").document("1").set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            toastResult("Data added successfully");
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            toastResult("Data add Completed");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastResult("Error while adding the data : " + e.getMessage());
                        }
                    });*/
            myDB.collection("places")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            toastResult("Data added successfully");
                            Intent intent = new Intent(AddPlaces.this, DetailsActivity.class);
                            intent.putExtra("ids", documentReference.getId());
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastResult("Error while adding the data : " + e.getMessage());
                        }
                    });

        } else {
            place_Name.setError("Value Required");
        }
    }

    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}