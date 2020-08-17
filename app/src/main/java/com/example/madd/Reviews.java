package com.example.madd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Reviews extends AppCompatActivity {

    //String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","Android","IPhone","WindowsMobile","Blackberry","Android","IPhone","WindowsMobile","Blackberry",
     //       "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        ArrayList<ReviewInterface> items = new ArrayList<ReviewInterface>();
        items.add(new ReviewInterface("You", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));
        items.add(new ReviewInterface("Swyatha ", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("Suwathi", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));

        //ArrayAdapter adapter = new ArrayAdapter(this, R.layout.review_item, this.mobileArray);
        ReviewListAdapter adapter = new ReviewListAdapter(this, items);
        ListView listview = (ListView) findViewById(R.id.reviews_list);
        listview.setAdapter(adapter);
    }

    public void onClickAdd(View v) {
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addReview = inflater.inflate(R.layout.new_review, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(addReview)
                .setTitle("Add Review")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Save review
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
        toolbar.setSubtitle("SUB TITLE");
/*
        SupportActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("mytest");
        actionBar.setDisplayHomeAsUpEnabled(true);
  */
    }
}