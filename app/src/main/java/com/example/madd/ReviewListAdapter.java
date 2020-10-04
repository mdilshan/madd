package com.example.madd;

import android.app.*;
import android.media.Image;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.*;

import android.content.*;

import androidx.annotation.NonNull;

import com.example.madd.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Custom ArrayAdapter for listing Reviews
 * @Author - https://github.com/mdilshan
 */
public class ReviewListAdapter extends ArrayAdapter<ReviewInterface> {
    private static final String TAG = "ReviewListAdapter";
    private final Activity context;
    private final LayoutInflater layoutInflater;
    private List<ReviewInterface> items;

    // Public constructor
    public ReviewListAdapter(Activity context, List<ReviewInterface> items) {
        super(context, R.layout.review_item, items);
        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        // Reusing Inflater
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.review_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            if(!items.get(position).name.equals("You")) {
                viewHolder.editButton.setVisibility(View.INVISIBLE);
                viewHolder.deletBUtton.setVisibility(View.INVISIBLE);
            }
        }

        //ImageButton editButton = (ImageButton) convertView.findViewById(R.id.editReviewBtn);
        //ImageButton deletBUtton = (ImageButton) convertView.findViewById(R.id.deleteReviewBtn);

        // set
        if (items.get(position).name.equals("You")) {
            Log.d(TAG, "getView: ---------------------------------------" + items.get(position).name);
            viewHolder.editButton.setOnClickListener(new Button.OnClickListener() {
                final View editView = layoutInflater.inflate(R.layout.edit_review, null);
                final EditText input = (EditText) editView.findViewById(R.id.editText);
                final RatingBar ratingBar = (RatingBar) editView.findViewById(R.id.ratingBarEdit);

                @Override
                public void onClick(View v) {
                    if (editView.getParent() != null) {
                        ((ViewGroup) editView.getParent()).removeView(editView);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setView(editView)
                            .setTitle("Edit Review")
                            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // Save review
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    String id = items.get(position).id;
                                    HashMap<String, Object> d = new HashMap<>();
                                    d.put("comment", input.getText().toString());
                                    d.put("rating", Utils.roundFloat(ratingBar.getRating(), 1));
                                    db.collection("reviews")
                                            .document(id)
                                            .update(d)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Log.d(TAG, "onComplete: Update Success");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.e(TAG, "onFailure: Update Failed");
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
                    input.setText(items.get(position).comment);
                    ratingBar.setRating((float) items.get(position).rating);
                    builder.show();
                    //AlertDialog dialog = builder.create();
                    // dialog.show();
                }
            });

            viewHolder.deletBUtton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Warning");
                    builder.setMessage("Are you sure you want to delete the review ? ");

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("reviews").document(items.get(position).id).delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "onSuccess: Delete Success");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.e(TAG, "onFailure: Failed to delete");
                                                }
                                            });
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("Yes", dialogClickListener);

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("No", dialogClickListener);

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();
                }
            });

            viewHolder.editButton.setVisibility(View.VISIBLE);
            viewHolder.deletBUtton.setVisibility(View.VISIBLE);
        }

        viewHolder.img.setImageResource(R.drawable.profile_pic);
        viewHolder.name.setText(items.get(position).name);
        //img.setImageResource(R.drawable.profile_pic);
        //name.setText(items.get(position).name);
        viewHolder.posted_by.setText("Reviewed On - " + items.get(position).posted_by);
        viewHolder.rating.setRating((float) items.get(position).rating);
        viewHolder.comment.setText(items.get(position).comment);

        return convertView;
    }

    private class ViewHolder {

        final ImageView img;
        final TextView name;
        final TextView posted_by;
        final RatingBar rating;
        final TextView comment;
        final ImageButton editButton;
        final ImageButton deletBUtton;
        ViewHolder(View v) {
            this.img = (ImageView) v.findViewById(R.id.icon);
            this.name = (TextView) v.findViewById(R.id.label);
            this.posted_by = (TextView) v.findViewById(R.id.label1);
            this.rating = (RatingBar) v.findViewById(R.id.ratingBar);
            this.comment = (TextView) v.findViewById(R.id.comment);
            this.editButton = (ImageButton) v.findViewById(R.id.editReviewBtn);
            this.deletBUtton = (ImageButton) v.findViewById(R.id.deleteReviewBtn);
        }

    }
}
