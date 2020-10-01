package com.example.madd.util;

import com.example.madd.ReviewInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
    public static float getAverage(int length, float sum) {
        Float cal = sum / length;
        return roundFloat(cal, 1);
    }

    public static float roundFloat(float d, int place) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(place, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * Use to seed the review data collection on firestore
     */
    public void seed() {
        ArrayList<ReviewInterface> items = new ArrayList<ReviewInterface>();
        items.add(new ReviewInterface("user_23", "Swyatha ", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_24","Suwathi", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_25", "Dilshan", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_26","Ashiq", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));
        items.add(new ReviewInterface("user_27", "Kamal", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_28","Shrimali", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_29", "Gihan", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_30","Kavishka", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));
        items.add(new ReviewInterface("user_31", "Pramuka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_32","Lahiru", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_33", "Madushanka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_34","Praveen", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));
        items.add(new ReviewInterface("user_35", "Kalinga", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_36","Dilanka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_37", "Dinuka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_38","Isanka", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for(ReviewInterface item : items) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("resource_id", "CBIWoyemq9mPqOZQ7rgT");
            data.put("comment", item.comment);
            data.put("name", item.name);
            data.put("posted_by", item.posted_by);
            data.put("rating", item.rating);
            data.put("user_id", "user_34");

            db.collection("reviews").add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                    }
                                }
                            });
                        }
                    });
        }
    }

}
