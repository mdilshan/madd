package com.example.madd.testActivities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madd.R;
import com.example.madd.ReviewInterface;
import com.example.madd.ReviewListAdapter;
import com.example.madd.util.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * ReviewActivity Stub
 * @Author - https://github.com/mdilshan
 */
public class ReviewTestActivity extends AppCompatActivity {
    private static final String TAG = "ReviewsTestActivity";
    ArrayList<ReviewInterface> items;
    double overall_rating = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        //Utils.seed();
        items = new ArrayList<ReviewInterface>();

        ReviewListAdapter adapter = new ReviewListAdapter(ReviewTestActivity.this, items);
        ListView listview = (ListView) findViewById(R.id.reviews_list);
        listview.setAdapter(adapter);

        items.add(new ReviewInterface("1", "John", "2020-10-23","Lorem Ipsum", 4.5));
        items.add(new ReviewInterface("2", "Kamal", "2020-10-23","Lorem Ipsum", 3.5));
        items.add(new ReviewInterface("3", "You", "2020-10-23","Lorem Ipsum", 4.0));
        items.add(new ReviewInterface("4", "Swaytha", "2020-10-23","Lorem Ipsum", 4.9));

        overall_rating = Utils.getAverage(4, 16.9);
        TextView summary = (TextView) findViewById(R.id.textViewSummary);
        RatingBar summaryRating = (RatingBar) findViewById(R.id.ratingBarSummary);

        summary.setText(String.format("%1.1f", overall_rating));
        summaryRating.setRating((float)overall_rating);

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
                        float review = ((RatingBar) addReview.findViewById(R.id.newRatingBar)).getRating();
                        String date = LocalDate.now().toString();
                        String user_id = "user_99";
                        String user_name = "You";
                        String id = UUID.randomUUID().toString();

                        HashMap<String, Object> data = new HashMap<>();
                        data.put("comment", comment);
                        data.put("name", user_name);
                        data.put("posted_by", date);
                        data.put("rating", Utils.roundFloat(review, 1));
                        data.put("user_id", user_id);
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

    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
    }
}
