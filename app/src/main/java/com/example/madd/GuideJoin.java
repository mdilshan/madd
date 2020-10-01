package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GuideJoin extends AppCompatActivity {
    FirebaseFirestore myDB;
    Button joinsubmit;
    TextView input_guide_name,input_place,input_price,input_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_join);

        myDB = FirebaseFirestore.getInstance();

        input_guide_name = findViewById(R.id.input_guide_name);
        input_place = findViewById(R.id.input_place);
        input_price = findViewById(R.id.input_price);
        input_image = findViewById(R.id.input_image);


//        joinsubmit = findViewById(R.id.joinsubmit);
//
//        joinsubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(GuideJoin.this, GuideDetails.class);
//                startActivity(intent);
//            }
//        });
    }

    public void onAddClicked(View view) {


        hideKeyboard(this);
        if (input_guide_name.getText().toString().length() > 0
                ||input_place.getText().toString().length() > 0
                ||input_price.getText().toString().length() > 0
                ||input_image.getText().toString().length() > 0 ) {
            Map<String, Object> data = new HashMap<>();
            data.put("guide_name", input_guide_name.getText().toString());
            data.put("place", input_place.getText().toString());
            data.put("price", input_price.getText().toString());
            data.put("imageUrl", input_image.getText().toString());
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
            myDB.collection("guides")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            toastResult("Data added successfully");
                            Intent intent = new Intent(GuideJoin.this, GuideDetails.class);
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
            input_guide_name.setError("Value Required");
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