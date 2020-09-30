package com.example.madd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madd.model.PlaceDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        String item_id = extras.getString("item_id");

        if (item_id == null) {
            Log.e(TAG, "onCreate: Item Id is null");
        } else {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference col = db.collection("places");
            col
                    .whereEqualTo("id", item_id)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> d = document.getData();
                                    PlaceDto data = new PlaceDto(
                                            (String) d.get("id"),
                                            (String) d.get("user_id"),
                                            (String) d.get("name"),
                                            (String) d.get("location"),
                                            (String) d.get("description"),
                                            (String) d.get("img_url")
                                    );
                                    // SET data to the View
                                }
                            } else {
                                Log.e(TAG, "onComplete: ", task.getException());
                            }
                        }
                    });

        }

        ImageView deleteProfile = findViewById(R.id.deleteBtn);

        builder = new AlertDialog.Builder(this);

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you need to delete your Account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "your account is deleted successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DetailsActivity.this, PlaceActivity.class);
                                startActivity(intent);
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

        ImageView editProfile = findViewById(R.id.editButton);

        builder = new AlertDialog.Builder(this);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you need to edit your Account ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "your can edit the information",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DetailsActivity.this, EditPlaces.class);
                                startActivity(intent);
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
                alert.setTitle("Edit Profile");
                alert.show();
            }
        });

    }
}
