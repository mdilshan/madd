package com.example.madd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.adapter.AllGuideAdapter;
import com.example.madd.model.AllGuideData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class GuideEdit extends AppCompatActivity {
    FirebaseFirestore myDB;
    Button editsubmit;
    TextView edit_guide_name,edit_place,edit_price,edit_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_edit);

        myDB = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ids");

        edit_guide_name = findViewById(R.id.edit_guide_name);
        edit_place = findViewById(R.id.edit_place);
        edit_price = findViewById(R.id.edit_price);
        edit_image = findViewById(R.id.edit_image);

        readData(id);
        editsubmit = findViewById(R.id.saveSubmit);

        editsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_guide_name.getText().toString().length() > 0 ||
                        edit_place.getText().toString().length() > 0 ||
                        edit_price.getText().toString().length() > 0 ||
                        edit_image.getText().toString().length() > 0) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("guide_name", edit_guide_name.getText().toString());
                    data.put("place", edit_place.getText().toString());
                    data.put("price", edit_price.getText().toString());
                    data.put("image", edit_image.getText().toString());
                        myDB.collection("guides").document(id).update(data)
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

                                Intent intent = new Intent(GuideEdit.this, GuideDetails.class);
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
                    edit_guide_name.setError("Value Required");
                }
            }
        });

    }

    void readData(String id) {
        DocumentReference documentReference = myDB.collection("guides").document(id);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    edit_guide_name.setText(task.getResult().get("guide_name").toString());
                    edit_place.setText(task.getResult().get("place").toString());
                    edit_price.setText(task.getResult().get("price").toString());
                    edit_image.setText(task.getResult().get("imageUrl").toString());
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