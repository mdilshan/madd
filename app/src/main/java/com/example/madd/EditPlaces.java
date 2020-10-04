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
import android.widget.ImageView;
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
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.mobsandgeeks.saripaar.annotation.Url;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditPlaces extends AppCompatActivity implements Validator.ValidationListener {
    private static final String TAG = "EditPlaces";
    FirebaseFirestore myDB;
    Button updatebtn;


    @NotEmpty
    private TextView edit_placeName;

    @NotEmpty
    private TextView edit_placeLocation;

    @NotEmpty
    @Url
    private TextView edit_placeURL;

    @NotEmpty
    private TextView edit_placeDescription;

    private Validator validator;

    public String doc_id;

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        doc_id = doc_id;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_places);

        myDB = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("ids");
        setDoc_id(intent.getStringExtra("ids"));
        doc_id =intent.getStringExtra("ids");
        Log.d(TAG, "onCreate: ID RECIEVED " + id);

        ImageView Bck_bttn = findViewById(R.id.back_btn3);
        Bck_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edit_placeName = findViewById(R.id.editPlace);
        edit_placeLocation = findViewById(R.id.editPlaceLocation);
        edit_placeDescription = findViewById(R.id.editPlaceAbout);
        edit_placeURL = findViewById(R.id.editURL);
        readData(id);
        updatebtn = findViewById(R.id.updateSubmit);
        if(doc_id != null){
            updatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdatePlace(view);
                }
            });
            validator = new Validator(this);
            validator.setValidationListener(this);
        }else{
            setDoc_id(intent.getStringExtra("ids"));
            doc_id =intent.getStringExtra("ids");
        }
    }
    private void UpdatePlace(View view) {
        hideKeyboard(this);
        validator.validate();
    }


            @Override
            public void onValidationSucceeded() {
                final String doc_id=getDoc_id();
                if (edit_placeName.getText().toString().length() > 0 ||
                        edit_placeLocation.getText().toString().length() > 0 ||
                        edit_placeDescription.getText().toString().length() > 0 ||
                        edit_placeURL.getText().toString().length() > 0) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("place_name", edit_placeName.getText().toString());
                    data.put("place_location", edit_placeLocation.getText().toString());
                    data.put("place_description", edit_placeDescription.getText().toString());
                    data.put("place_URL", edit_placeURL.getText().toString());
                    myDB.collection("places").document(doc_id).update(data)
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
                                    intent.putExtra("ids",doc_id);
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



    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    void readData(String id) {
        setDoc_id(id);
        try {
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
        }catch (Exception e){

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
}
