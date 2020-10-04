package com.example.madd;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.mobsandgeeks.saripaar.annotation.Url;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewHotel extends AppCompatActivity implements Validator.ValidationListener {

    //TextView hotelName,hotelLocation,hotelAbout,hotelContact,hotelImage;
    Button addHotel;
    DatabaseReference dbRef;
    Hoteldto hotel;
    FirebaseFirestore myDB;

    @NotEmpty
    private TextView hotelName;

    @NotEmpty
    private TextView hotelLocation;

    @NotEmpty
    @Pattern(regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")
    private TextView hotelContact;

    @NotEmpty
    @Url
    private TextView hotelImage;

    @NotEmpty
    private TextView hotelAbout;

    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);
        myDB = FirebaseFirestore.getInstance();

        ImageButton Back = findViewById(R.id.back_btn);

      Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

      hotelName = findViewById(R.id.etHotelName);
        hotelLocation = findViewById(R.id.etHotelLocation);
        hotelAbout = findViewById(R.id.etHotelAbout);
        hotelContact = findViewById(R.id.etHotelContact);
        hotelImage = findViewById(R.id.etHotelImage);


        addHotel = findViewById(R.id.btnAddHotel);

        hotel = new Hoteldto();
        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    public void onAddClicked(View view) {
        hideKeyboard(this);
        validator.validate();

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


    @Override
    public void onValidationSucceeded() {
        String date = LocalDate.now().toString();
        hideKeyboard(this);
        if (hotelName.getText().toString().length() > 0
                || hotelLocation.getText().toString().length() > 0
                || hotelAbout.getText().toString().length() > 0
                || hotelContact.getText().toString().length() > 0
                || hotelImage.getText().toString().length() > 0
        ) {
            try {
                Map<String, Object> data = new HashMap<>();
                data.put("hotel_name", hotelName.getText().toString());
                data.put("location", hotelLocation.getText().toString());
                data.put("about", hotelAbout.getText().toString());
                data.put("contact", hotelContact.getText().toString());
                data.put("rating", "0.0");
                data.put("image", hotelImage.getText().toString());
                data.put("joined_on", date);

                myDB.collection("hotels")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                toastResult("Data added successfully");
                                Intent intent = new Intent(AddNewHotel.this, HotelMainPage.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                toastResult("Error while adding the data : " + e.getMessage());
                            }
                        });
            } catch (Exception e) {
                toastResult(e.getMessage());
            }
        }
    }
        @Override
        public void onValidationFailed (List < ValidationError > errors) {
            for (ValidationError error : errors) {
                View view = error.getView();
                String message = error.getCollatedErrorMessage(this);
                // Display error messages
                if (view instanceof EditText) {
                    ((EditText) view).setError(message);
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onPointerCaptureChanged ( boolean hasCapture){
        }
    }
