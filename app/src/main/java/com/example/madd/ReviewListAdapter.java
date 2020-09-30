package com.example.madd;
import android.app.*;
import android.media.Rating;
import android.os.*;
import android.util.TypedValue;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.widget.BaseAdapter;

import androidx.recyclerview.widget.ListAdapter;

public class ReviewListAdapter extends ArrayAdapter<ReviewInterface> {
    private final Activity context;
    private List<ReviewInterface> items;

    // Public constructor
    public ReviewListAdapter(Activity context, List<ReviewInterface> items) {
        super(context, R.layout.review_item, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.review_item, parent, false);

        final View editView = inflater.inflate(R.layout.edit_review, null);
        final EditText input = (EditText) editView.findViewById(R.id.editText);
        final RatingBar ratingBar = (RatingBar) editView.findViewById(R.id.ratingBar);

        ImageView img = (ImageView) rowView.findViewById(R.id.icon);
        TextView name = (TextView) rowView.findViewById(R.id.label);
        TextView posted_by = (TextView) rowView.findViewById(R.id.label1);
        final RatingBar rating = (RatingBar) rowView.findViewById(R.id.ratingBar);
        TextView comment = (TextView) rowView.findViewById(R.id.comment);

        ImageButton editButton = (ImageButton) rowView.findViewById(R.id.edit);
        ImageButton deletBUtton = (ImageButton) rowView.findViewById(R.id.delete);

        if(items.get(position).name == "You") {
            editButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setView(editView)
                            .setTitle("Edit Review")
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
                    input.setText(items.get(position).comment);
                    rating.setRating((float) items.get(position).rating);
                    builder.show();
                    //AlertDialog dialog = builder.create();
                   // dialog.show();
                }
            });

            deletBUtton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Warning");
                    builder.setMessage("Are you sure you want to delete the review ? ");

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("Yes", dialogClickListener);

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("No",dialogClickListener);

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();
                }
            });

            editButton.setVisibility(View.VISIBLE);
            deletBUtton.setVisibility(View.VISIBLE);
        }

        img.setImageResource(R.drawable.profile_pic);
        name.setText(items.get(position).name);
        posted_by.setText("Reviewed On - " + items.get(position).posted_by);
        rating.setRating((float)items.get(position).rating);
        comment.setText(items.get(position).comment);

        return rowView;
    }
}
