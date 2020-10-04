package com.example.madd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

/**
 * Guide Profile
 * @Author - https://github.com/MhmdAsq
 */
public class GuideDetails extends AppCompatActivity {
    FirebaseFirestore myDB;
    TextView guide_joined,guide_name,guide_star,guide_about,guide_mobile,guide_place,guide_review;
    ImageView guide_image;
    ImageButton editbtn;
    Button callbtn;
    RatingBar GuideRatingBAR;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_details);

        Intent intent = getIntent();
        final String ids = intent.getStringExtra("ids");

        myDB = FirebaseFirestore.getInstance();
        guide_image = findViewById(R.id.profile_image);
        guide_name= findViewById(R.id.guide_name);
        guide_joined= findViewById(R.id.guide_joined);
        guide_star= findViewById(R.id.guide_star);
        guide_place= findViewById(R.id.guide_place);
        guide_mobile= findViewById(R.id.guide_mobile);
        guide_about= findViewById(R.id.guide_about);
        GuideRatingBAR = findViewById(R.id.guide_rating_bars);
        readData(ids);
        bottomnav();
        ImageButton Back  = findViewById(R.id.back_btn);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editbtn = (ImageButton) findViewById(R.id.editbtn);
        guide_review = findViewById(R.id.guide_review);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = myDB.collection("guides").document(ids);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(GuideDetails.this, GuideEdit.class);
                            intent.putExtra("ids",ids);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        callbtn = findViewById(R.id.callbtn);
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = myDB.collection("guides").document(ids);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse("tel:"+task.getResult().get("mobile").toString()));
                            startActivity(callIntent);
                        }
                    }
                });
            }
        });
        guide_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = myDB.collection("guides").document(ids);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(GuideDetails.this, Reviews.class);
                            intent.putExtra("ids",ids);
                            intent.putExtra( "resource_type", "GUIDE");
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        ImageView deleteProfile = findViewById(R.id.deletebtn);

        builder = new AlertDialog.Builder(this);

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you need to delete your Account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myDB.collection("guides").document(ids).delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(),"your account is deleted successfully",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(GuideDetails.this, GuideHome.class);
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
                                Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
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
    void readData(String ids) {
        hideKeyboard(this);
        try {
            DocumentReference documentReference = myDB.collection("guides").document(ids);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        guide_name.setText(task.getResult().get("guide_name").toString());
                        Picasso.get().load(task.getResult().get("imageUrl").toString()).into(guide_image);
                        guide_mobile.setText(task.getResult().get("mobile").toString());
                        guide_place.setText(task.getResult().get("place").toString());
                        guide_star.setText(task.getResult().get("rating").toString());
                        GuideRatingBAR.setRating(Float.parseFloat(task.getResult().get("rating").toString()));
                        guide_about.setText(task.getResult().get("about").toString());
                        guide_joined.setText(task.getResult().get("joined_on").toString());
                    }
                }
            });

        }catch (Exception e){}
    }
    public void bottomnav() {
        final Activity A = GuideDetails.this;
        ImageView home_btn_nav1 =  (ImageView)findViewById(R.id.home_btn_nav);
        ImageView guide_btn_nav1 =(ImageView)findViewById(R.id.guide_btn_nav);
        ImageView places_btn_nav1 =(ImageView)findViewById(R.id.places_btn_nav);
        ImageView hotel_btn_nav1 = (ImageView)findViewById(R.id.hotel_btn_nav);

        home_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A,MainActivity.class);
                startActivity(intent);
            }
        });
        guide_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A,GuideHome.class);
                startActivity(intent);
            }
        });
        places_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A, PlaceActivity.class);
                startActivity(intent);
            }
        });
        hotel_btn_nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A, HotelMainPage.class);
                startActivity(intent);
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