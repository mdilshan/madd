package com.example.madd;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.madd.util.Utils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Review Screen
 *
 * @Author - https://github.com/mdilshan
 */
public class Reviews extends AppCompatActivity {
    private static final String TAG = "Reviews";
    ArrayList<ReviewInterface> items;
    double overall_rating = 0.0;
    private String resource_id = null;
    private String resource_type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        //Utils.seed();
        items = new ArrayList<ReviewInterface>();

        Intent intent = getIntent();
        this.resource_id = intent.getStringExtra("ids");
        this.resource_type = intent.getStringExtra("resource_type");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("reviews").whereEqualTo("resource_id", resource_id).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null)
                    Log.d(TAG, "onEvent: Reviews donwloaded");
                items.clear();
                int itemCount = 0;
                float review_sum = 0;
                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                    ReviewInterface r = new ReviewInterface(
                            doc.getId().toString(),
                            doc.getString("name"),
                            doc.getString("posted_by"),
                            doc.getString("comment"),
                            doc.getDouble("rating")
                    );
                    itemCount++;
                    review_sum += doc.getDouble("rating");
                    items.add(r);
                }

                if (itemCount > 0) {
                    ReviewListAdapter adapter = new ReviewListAdapter(Reviews.this, items);
                    ListView listview = (ListView) findViewById(R.id.reviews_list);
                    listview.setAdapter(adapter);

                    overall_rating = Utils.getAverage(itemCount, review_sum);
                    TextView summary = (TextView) findViewById(R.id.textViewSummary);
                    RatingBar summaryRating = (RatingBar) findViewById(R.id.ratingBarSummary);

                    summary.setText(String.format("%1.1f", overall_rating));
                    summaryRating.setRating((float) overall_rating);
                }
            }
        });

    }

    public void onClickAdd(View v) {
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addReview = inflater.inflate(R.layout.new_review, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(addReview)
                .setTitle("Add Review")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Save review
                        String comment = ((TextView) addReview.findViewById(R.id.newTextReview)).getText().toString();
                        final float review = ((RatingBar) addReview.findViewById(R.id.newRatingBar)).getRating();
                        String date = LocalDate.now().toString();
                        String user_id = "user_99";
                        String user_name = "You";

                        Intent intent = getIntent();
                        final String resource_id = intent.getStringExtra("ids");

                        HashMap<String, Object> data = new HashMap<>();
                        data.put("comment", comment);
                        data.put("name", user_name);
                        data.put("posted_by", date);
                        data.put("rating", Utils.roundFloat(review, 1));
                        data.put("user_id", user_id);
                        data.put("resource_id", resource_id);

                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("reviews").add(data)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if (documentSnapshot.exists()) {
                                                    String path = null;

                                                    switch (resource_type) {
                                                        case "HOTEL":
                                                            path = "hotels";
                                                            break;
                                                        case "GUIDE":
                                                            path = "guides";
                                                            break;
                                                        case "PLACE":
                                                            path = "places";
                                                    }

                                                    if (path != null) {
                                                        update_review_summary(db, path, resource_id, "" + Utils.roundFloat(review, 1));
                                                    }

                                                }
                                            }
                                        });
                                    }
                                });
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }

    public void update_review_summary(FirebaseFirestore db, String collection, String path_id, String review) {
        HashMap<String, Object> d = new HashMap<>();
        d.put("rating", review);
        Log.d(TAG, "update_review_summary: Collection " + collection);
        Log.d(TAG, "update_review_summary: Path " + path_id);
        Log.d(TAG, "update_review_summary: Review " + review);
        db.collection(collection).document(path_id).update(d);
    }

    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
    }
}