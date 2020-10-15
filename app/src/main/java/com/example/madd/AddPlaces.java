package com.example.madd;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madd.model.PlaceDto;
import com.example.madd.model.RecentsData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.UUID;


public class AddPlaces extends AppCompatActivity implements Validator.ValidationListener {
    FirebaseFirestore myDB;
    Button addsubmit;
    //TextView place_Name, place_Location, place_Description, place_URL;

    @NotEmpty
    private TextView place_Name;

    @NotEmpty
    private TextView place_Location;

    @NotEmpty
    @Url
    private TextView place_URL;

    @NotEmpty
    private TextView place_Description;

    private Validator validator;

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

        ImageView Bck_bttn = findViewById(R.id.back_btn4);
        Bck_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addsubmit = findViewById(R.id.addSubmit);
        place_Name = findViewById(R.id.etPlace);
        place_Location = findViewById(R.id.etPlaceLocation);
        place_Description = findViewById(R.id.etPlaceAbout);
        place_URL = findViewById(R.id.etURL);

        validator = new Validator(this);
        validator.setValidationListener(this);

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
        validator.validate();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onValidationSucceeded()  {
        String date = LocalDate.now().toString();
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
            data.put("rating", "0.0");
            data.put("joined_on", date);
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
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
