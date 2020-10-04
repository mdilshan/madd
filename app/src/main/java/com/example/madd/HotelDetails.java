package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HotelDetails extends AppCompatActivity {
    FirebaseFirestore myDB;
    TextView hotel_joined,hotel_name,hotel_star,hotel_about,hotel_mobile,hotel_location,hotel_review;
    ImageView hotel_image;
    AlertDialog.Builder builder;
    ImageButton HotelEdit,HotelDelete;
    RatingBar HotelRatingBAR;
    Button AddHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        myDB = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        final String ids = intent.getStringExtra("ids");


        hotel_joined = findViewById(R.id.dt_hotel_joined);
        hotel_name = findViewById(R.id.dt_hotel_name);
        hotel_star = findViewById(R.id.dt_hotel_star);
        hotel_about = findViewById(R.id.dt_hotel_about);
        hotel_mobile = findViewById(R.id.dt_hotel_mobile);
        hotel_location = findViewById(R.id.dt_hotel_place);
        hotel_review = findViewById(R.id.dt_hotel_review);
       hotel_image = findViewById(R.id.dt_hotel_profile_image);
        HotelEdit = findViewById(R.id.btnHotelEdit);
        HotelDelete = findViewById(R.id.btnHotelDelete);
        HotelRatingBAR = findViewById(R.id.dt_hotel_rating_bars);
        readData(ids);

        AddHotel = findViewById(R.id.AddHotelbtn);

        AddHotel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelDetails.this,AddNewHotel.class);
                startActivity(intent);
            }
        });

        HotelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = myDB.collection("hotels").document(ids);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(HotelDetails.this, EditHotel.class);
                            intent.putExtra("ids", ids);
                            startActivity(intent);
                        }
                    }
                });

            }
        });

        hotel_review.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = myDB.collection("hotels").document(ids);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(HotelDetails.this,EditHotel.class);
                            intent.putExtra("ids",ids);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        builder = new AlertDialog.Builder(this);

        HotelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you need to delete this hotel Account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final Task<Void> voidTask = myDB.collection("hotels").document(ids).delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(), "your account is deleted successfully",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(HotelDetails.this, HotelMainPage.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                toastResult("Error while deleting the data : " + e.getMessage());
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "you choose no action for alertbox",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Delete Profile");
                alert.show();
            }
        });
    }
void readData(String ids){
    hideKeyboard(this);
    try{
        DocumentReference documentReference = myDB.collection("hotels").document(ids);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    hotel_name.setText(task.getResult().get("hotel_name").toString());
                   // hotel_image.setText(task.getResult().get("image").toString());
                    hotel_mobile.setText(task.getResult().get("contact").toString());
                    hotel_location.setText(task.getResult().get("location").toString());
                    hotel_star.setText(task.getResult().get("rating").toString());
                    hotel_about.setText(task.getResult().get("about").toString());
                    hotel_joined.setText(task.getResult().get("joined_on").toString());
                    HotelRatingBAR.setRating(Float.parseFloat(task.getResult().get("rating").toString()));
                }
            }
        });
        }
    catch (Exception e){}

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
