//package com.example.madd;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.madd.model.PlaceDto;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.UUID;
//
//public class AddPlaces extends AppCompatActivity {
//    private static final String TAG = "AddPlaces";
//
//    EditText PlaceName,PlaceLocation,PlaceDescription;
//    Button addsubmit;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_places);
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        addsubmit = findViewById(R.id.addSubmit);
//        EditText placeName = (EditText) findViewById(R.id.etHotelLocation);
//        EditText placeLocation = (EditText) findViewById(R.id.etPlaceLocation);
//        EditText placeDescription = (EditText) findViewById(R.id.etPlaceAbout);
//
//        addsubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: " + placeName.getText().toString());
//                String id = UUID.randomUUID().toString();
//                String user_id = "user_1";
//                PlaceDto new_place = new PlaceDto(
//                        id,
//                        user_id,
//                        placeName.getText().toString(),
//                        placeLocation.getText().toString(),
//                        placeDescription.getText().toString(),
//                        "https://images.unsplash.com/photo-1508672019048-805c876b67e2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
//                );
//
//
//
//                // Add a new document with a generated ID
//                db.collection("places")
//                        .add(new_place)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Intent intent = new Intent(AddPlaces.this, DetailsActivity.class);
//                                intent.putExtra("item_id", new_place.id);
//                                startActivity(intent);
//                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w(TAG, "Error adding document", e);
//                            }
//                        });
//            }
//
//        });
//    }
//}