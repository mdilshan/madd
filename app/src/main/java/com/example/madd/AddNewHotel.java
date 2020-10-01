package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madd.model.Hoteldto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNewHotel extends AppCompatActivity {

    TextView hotelName,hotelLocation,hotelAbout;
    Button addHotel;
    DatabaseReference dbRef;
    Hoteldto hotel;
    FirebaseFirestore myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        myDB = FirebaseFirestore.getInstance();

        hotelName = findViewById(R.id.etHotelName);
        hotelLocation = findViewById(R.id.etHotelLocation);
        hotelAbout = findViewById(R.id.etHotelAbout);

        addHotel = findViewById(R.id.btnAddHotel);

        hotel = new Hoteldto();

    }
    public void onAddClicked(View view) {


        hideKeyboard(this);
        if (hotelName.getText().toString().length() > 0
                ||hotelLocation.getText().toString().length() > 0
                ||hotelAbout.getText().toString().length() > 0) {
            try {
            Map<String, Object> data = new HashMap<>();
            data.put("hotel_name", hotelName.getText().toString());
            data.put("location", hotelLocation.getText().toString());
            data.put("about", hotelAbout.getText().toString());
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

               myDB.collection("hotels")
                       .add(data)
                       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                           @Override
                           public void onSuccess(DocumentReference documentReference) {
                               toastResult("Data added successfully");
//                               Intent intent = new Intent(AddNewHotel.this, HotelMainPage.class);
//                               startActivity(intent);
                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               toastResult("Error while adding the data : " + e.getMessage());
                           }
                       });
           }catch (Exception e){
               toastResult(e.getMessage());
           }


        } else {
            hotelName.setError("Value Required");
        }
    }
    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}