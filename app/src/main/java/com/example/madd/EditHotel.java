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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class EditHotel extends AppCompatActivity implements Validator.ValidationListener {
   // private static Build.VERSION_CODES andriod;

    FirebaseFirestore myDB;
    Button edit_hotel_submit;
    //TextView edit_hotel_name,edit_hotel_location,edit_hotel_about,edit_hotel_image,edit_hotel_contact;

    @NotEmpty
    private TextView edit_hotel_name;

    @NotEmpty
    private  TextView edit_hotel_location;

    @NotEmpty
    @Pattern(regex =  "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")
    private TextView edit_hotel_contact;

    @NotEmpty
    @Url
    private TextView edit_hotel_image;

    @NotEmpty
    private TextView edit_hotel_about;

    private Validator validator;

    public String doc_id;

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hotel);
        Intent intent = getIntent();

        final String id = intent.getStringExtra("ids");
        setDoc_id(intent.getStringExtra("ids"));
        doc_id = intent.getStringExtra("ids");

        myDB = FirebaseFirestore.getInstance();

        edit_hotel_name = findViewById(R.id.etedHotelName);
        edit_hotel_location = findViewById(R.id.etedHotelLocation);
        edit_hotel_about = findViewById(R.id.etedHotelAbout);
        edit_hotel_image = findViewById(R.id.etedHotelImage);
        edit_hotel_contact = findViewById(R.id.etedHotelContact);

        ImageButton Back  = findViewById(R.id.back_btn);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        readData(id);

        edit_hotel_submit = findViewById(R.id.btnSaveHotel);
        if(doc_id != null){
            edit_hotel_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateHotel(view);
                }
            });
            validator = new Validator(this);
            validator.setValidationListener(this);
        }else{
            setDoc_id(intent.getStringExtra("ids"));
            doc_id =intent.getStringExtra("ids");
        }
    }


    private void UpdateHotel(View view) {
        hideKeyboard(this);
        validator.validate();
    }


    @Override
    public void onValidationSucceeded() {
        final String id_doc = getDoc_id();
        Map<String, Object> data = new HashMap<>();
        data.put("hotel_name", edit_hotel_name.getText().toString());
        data.put("location", edit_hotel_location.getText().toString());
        data.put("about", edit_hotel_about.getText().toString());
        data.put("contact", edit_hotel_contact.getText().toString());
        data.put("image", edit_hotel_image.getText().toString());
    try {
        myDB.collection("hotels").document(id_doc).update(data)
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

                Intent intent = new Intent(EditHotel.this, HotelDetails.class);
                intent.putExtra("ids",id_doc);
                startActivity(intent);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        toastResult("Error while updating the data : " + e.getMessage());
                    }
                });

    } catch (Exception e){
//            e.printStackTrace();
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



//    void readData(String id) {
//        setDoc_id(id);
//        try {
//
//                if( edit_hotel_name.getText().toString().length()>0 ||
//                    edit_hotel_location.getText().toString().length()>0 ||
//                        edit_hotel_contact.getText().toString().length()>0 ||
//                     edit_hotel_image.getText().toString().length()>0 ||
//                    edit_hotel_about.getText().toString().length()>0
//                ){
//

    void readData(String id) {
        setDoc_id(id);
        try {
            DocumentReference documentReference = myDB.collection("hotels").document(id);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        edit_hotel_name.setText(task.getResult().get("hotel_name").toString());
                        edit_hotel_location.setText(task.getResult().get("location").toString());
                        edit_hotel_about.setText(task.getResult().get("about").toString());
                        edit_hotel_contact.setText(task.getResult().get("contact").toString());
                        edit_hotel_image.setText(task.getResult().get("image").toString());


                    }
                }
            });
        } catch (Exception e) {
        }

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