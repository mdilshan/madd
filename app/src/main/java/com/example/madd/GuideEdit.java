package com.example.madd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.mobsandgeeks.saripaar.annotation.Url;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Guide Profile Edit
 * @Author - https://github.com/MhmdAsq
 */
public class GuideEdit extends AppCompatActivity implements Validator.ValidationListener{
    FirebaseFirestore myDB;
    Button editsubmit;
    @NotEmpty
    private TextView edit_guide_name;

    @NotEmpty
    private TextView edit_place;

    @NotEmpty
    @Pattern(regex =  "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")
    private TextView edit_mobile;

    @NotEmpty
    @Url
    private TextView edit_image;

    @NotEmpty
    private TextView edit_about;

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
        setContentView(R.layout.activity_guide_edit);
        Intent intent = getIntent();
        final String ids = intent.getStringExtra("ids");
        setDoc_id(intent.getStringExtra("ids"));
        doc_id =intent.getStringExtra("ids");
        ImageButton Back  = findViewById(R.id.back_btn);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myDB = FirebaseFirestore.getInstance();
        edit_guide_name = findViewById(R.id.edit_guide_name);
        edit_place = findViewById(R.id.edit_place);
        edit_mobile = findViewById(R.id.edit_mobile);
        edit_image = findViewById(R.id.edit_image);
        edit_about = findViewById(R.id.edit_about);
        readData(ids);
        editsubmit = findViewById(R.id.editsubmit);
        if(doc_id != null){
            editsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateGuide(view);
                }
            });
            validator = new Validator(this);
            validator.setValidationListener(this);
        }else{
            setDoc_id(intent.getStringExtra("ids"));
            doc_id =intent.getStringExtra("ids");
        }
    }
    private void UpdateGuide(View view) {
        hideKeyboard(this);
        validator.validate();
    }
    @Override
    public void onValidationSucceeded() {
        final String id_doc = getDoc_id();
        Map<String, Object> data = new HashMap<>();
        data.put("guide_name", edit_guide_name.getText().toString());
        data.put("place", edit_place.getText().toString());
        data.put("mobile", edit_mobile.getText().toString());
        data.put("imageUrl", edit_image.getText().toString());
        data.put("about", edit_about.getText().toString());
        try {
            myDB.collection("guides").document(id_doc).update(data)
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
        }catch (Exception e){
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
    void readData(String id) {
        setDoc_id(id);
        try {
            DocumentReference documentReference = myDB.collection("guides").document(id);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        edit_guide_name.setText(task.getResult().get("guide_name").toString());
                        edit_place.setText(task.getResult().get("place").toString());
                        edit_mobile.setText(task.getResult().get("mobile").toString());
                        edit_image.setText(task.getResult().get("imageUrl").toString());
                        edit_about.setText(task.getResult().get("about").toString());
                    }
                }
            });
        }catch (Exception e){}

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