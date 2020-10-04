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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
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
 * Guide Add
 * @Author - https://github.com/MhmdAsq
 */
public class GuideJoin extends AppCompatActivity implements Validator.ValidationListener {
    FirebaseFirestore myDB;
    Button joinsubmit;

    @NotEmpty
    private TextView input_guide_name;

    @NotEmpty
    private TextView input_place;

    @NotEmpty
    @Pattern(regex =  "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")
    private TextView input_mobile;

    @NotEmpty
    @Url
    private TextView input_image;

    @NotEmpty
    private TextView input_about;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_join);

        myDB = FirebaseFirestore.getInstance();
        ImageButton Back  = findViewById(R.id.back_btn);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        input_guide_name = findViewById(R.id.input_guide_name);
        input_place = findViewById(R.id.input_place);
        input_mobile = findViewById(R.id.input_mobile);
        input_image = findViewById(R.id.input_image);
        input_about = findViewById(R.id.input_about);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void onAddClicked(View view) {
        hideKeyboard(this);
        validator.validate();

    }
    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationSucceeded() {
        String date = LocalDate.now().toString();
        Map<String, Object> data = new HashMap<>();
        data.put("guide_name", input_guide_name.getText().toString());
        data.put("place", input_place.getText().toString());
        data.put("mobile", input_mobile.getText().toString());
        data.put("imageUrl", input_image.getText().toString());
        data.put("about", input_about.getText().toString());
        data.put("rating", "0.0");
        data.put("joined_on", date);
        try {
            myDB.collection("guides")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            toastResult("Data added successfully");
                            Intent intent = new Intent(GuideJoin.this, GuideDetails.class);
                            intent.putExtra("ids",documentReference.getId());
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastResult("Error while adding the data : " + e.getMessage());
                        }
                    });

        }catch (Exception e){}
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
}