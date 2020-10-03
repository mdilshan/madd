package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madd.model.PlaceDto;
import com.example.madd.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditPlaces extends AppCompatActivity {
    private static final String TAG = "EditPlaces";
    FirebaseFirestore myDB;
    Button updatebtn;
    TextView edit_placeName,edit_placeLocation,edit_placeDescription,edit_placeURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_places);

        myDB = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ids");
        Log.d(TAG, "onCreate: ID RECIEVED " + id);

        edit_placeName = findViewById(R.id.editPlace);
        edit_placeLocation = findViewById(R.id.editPlaceLocation);
        edit_placeDescription = findViewById(R.id.editPlaceAbout);
        edit_placeURL = findViewById(R.id.editURL);

        readData(id);

        updatebtn = findViewById(R.id.updateSubmit);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_placeName.getText().toString().length() > 0 ||
                        edit_placeLocation.getText().toString().length() > 0 ||
                        edit_placeDescription.getText().toString().length() > 0 ||
                        edit_placeURL.getText().toString().length() > 0) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("place_name", edit_placeName.getText().toString());
                    data.put("place_location", edit_placeLocation.getText().toString());
                    data.put("place_description", edit_placeDescription.getText().toString());
                    data.put("place_URL", edit_placeURL.getText().toString());
                    myDB.collection("places").document(id).update(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    toastResult("Data updated successfully");
                                }
                            })
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    toastResult("Data update Completed");

                                    Intent intent = new Intent(EditPlaces.this, DetailsActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    toastResult("Error while updating the data : " + e.getMessage());
                                }
                            });
                } else {
                    edit_placeName.setError("Value Required");
                }
            }
        });

    }

    void readData(String id) {
        DocumentReference documentReference = myDB.collection("places").document(id);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    edit_placeName.setText(task.getResult().get("place_name").toString());
                    edit_placeLocation.setText(task.getResult().get("place_location").toString());
                    edit_placeDescription.setText(task.getResult().get("place_description").toString());
                    edit_placeURL.setText(task.getResult().get("imageUrl").toString());
                }
            }
        });

    }

    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
