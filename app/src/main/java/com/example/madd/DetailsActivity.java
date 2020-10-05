package com.example.madd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    FirebaseFirestore myDB;
    TextView plce_joined,plce_name,plce_star,plce_about,plce_location,plce_review;
    ImageView plce_image;
    ImageButton editbtn;
    Button callbtn;
    RatingBar PlaceRatingBAR;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        myDB = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        final String ids = intent.getStringExtra("ids");
        plce_image=findViewById(R.id.profile_img);
        plce_review=findViewById(R.id.place_review);
        plce_name=findViewById(R.id.plc_name);
        plce_joined=findViewById(R.id.place_joined);
        plce_star=findViewById(R.id.place_star);
        plce_location=findViewById(R.id.plc_location);
        plce_about=findViewById(R.id.plc_about);
        PlaceRatingBAR=findViewById(R.id.place_rating_bars);
        readData(ids);
        bottomnav();

        ImageView Bck_bttn = findViewById(R.id.imageV4);
        Bck_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        if(ids != null) {
//            myDB.collection("places").document(ids)
//                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if(task.isSuccessful()) {
//                        DocumentSnapshot res = task.getResult();
//
//                    }
//                }
//            });
//        }
        //joinBtn = findViewById(R.id.joinbutton);
        final ImageButton editbutton = (ImageButton) findViewById(R.id.editButton);
        /*
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, AddPlaces.class);
                startActivity(intent);
            }
        }); */

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ON CLICK EDIT BUTTON");
                Intent intent = new Intent(DetailsActivity.this, EditPlaces.class);
                intent.putExtra("ids",ids);
                startActivity(intent);
            }
        });

        plce_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = myDB.collection("places").document(ids);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(DetailsActivity.this, Reviews.class);
                            intent.putExtra("ids",ids);
                            intent.putExtra( "resource_type", "PLACE");
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        final ImageView deleteProfile = findViewById(R.id.deleteBtnPlace);

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you need to delete your Account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myDB.collection("places").document(ids).delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(), "your account is deleted successfully",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(DetailsActivity.this, PlaceActivity.class);
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

    void readData(String ids) {
        hideKeyboard(this);
        try {
            DocumentReference documentReference = myDB.collection("places").document(ids);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        plce_name.setText(task.getResult().get("place_name").toString());
                        Picasso.get().load(task.getResult().get("imageUrl").toString()).into(plce_image);
                        plce_location.setText(task.getResult().get("place_location").toString());

                        plce_star.setText(task.getResult().get("rating").toString());
                        PlaceRatingBAR.setRating(Float.parseFloat(task.getResult().get("rating").toString()));
                        plce_about.setText(task.getResult().get("place_description").toString());
                        plce_joined.setText(task.getResult().get("joined_on").toString());
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
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

    public void bottomnav() {
        final Activity A = DetailsActivity.this;
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
}
