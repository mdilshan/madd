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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GuideJoin extends AppCompatActivity {
    FirebaseFirestore myDB;
    Button joinsubmit;
    TextView input_guide_name,input_place,input_mobile,input_image,input_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_join);

        myDB = FirebaseFirestore.getInstance();

        input_guide_name = findViewById(R.id.input_guide_name);
        input_place = findViewById(R.id.input_place);
        input_mobile = findViewById(R.id.input_mobile);
        input_image = findViewById(R.id.input_image);
        input_about = findViewById(R.id.input_about);


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

        String date = LocalDate.now().toString();
        hideKeyboard(this);
        if (input_guide_name.getText().toString().length() > 0
                &&input_place.getText().toString().length() > 0
                &&input_mobile.getText().toString().length() > 0
                &&input_image.getText().toString().length() > 0
                &&input_about.getText().toString().length() > 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("guide_name", input_guide_name.getText().toString());
            data.put("place", input_place.getText().toString());
            data.put("mobile", input_mobile.getText().toString());
            data.put("imageUrl", input_image.getText().toString());
            data.put("about", input_about.getText().toString());
            data.put("rating", "0.0");
            data.put("joined_on", date);
           try {
               myDB.collection("guides")
                       .add(data)
                       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                           @Override
                           public void onSuccess(DocumentReference documentReference) {
                               toastResult("Data added successfully");
                               Intent intent = new Intent(GuideJoin.this, GuideDetails.class);
                               intent.putExtra("ids",documentReference.getId());
                               startActivity(intent);
                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               toastResult("Error while adding the data : " + e.getMessage());
                           }
                       });

           }catch (Exception e){}

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