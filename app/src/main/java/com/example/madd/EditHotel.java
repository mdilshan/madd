package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditHotel extends AppCompatActivity {
    private static Build.VERSION_CODES andriod;
    FirebaseFirestore myDB;
    Button edit_hotel_submit;
    TextView edit_hotel_name,edit_hotel_location,edit_hotel_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hotel);

        myDB = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ids");

        edit_hotel_name = findViewById(R.id.etedHotelName);
        edit_hotel_location = findViewById(R.id.etedHotelLocation);
        edit_hotel_about = findViewById(R.id.etedHotelAbout);

        readData(id);

        edit_hotel_submit = findViewById(R.id.btnSaveHotel);

        edit_hotel_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( edit_hotel_name.getText().toString().length()>0 ||
                    edit_hotel_location.getText().toString().length()>0 ||
                    edit_hotel_about.getText().toString().length()>0
                ){
                    Map<String, Object> data = new HashMap<>();
                    data.put("hotel_name",edit_hotel_name.getText().toString());
                    data.put("location",edit_hotel_location.getText().toString());
                    data.put("about",edit_hotel_about.getText().toString());

                    myDB.collection("hotels").document(id).update(data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    toastResult("Data updated successfully");
                                }
                            });

                }else{
                    edit_hotel_name.setError("Value Required");
                }

            }
        });

    }

    void readData(String id){
        DocumentReference documentReference = myDB.collection("hotels").document(id);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    edit_hotel_name.setText(task.getResult().get("hotel_name").toString());
                    edit_hotel_location.setText(task.getResult().get("location").toString());
                    edit_hotel_about.setText(task.getResult().get("about").toString());


                }
            }
        });
    }
    public void toastResult(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(Activity activity){
        View view = activity.findViewById(android.R.id.content);
        if(view != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        };
    }
}