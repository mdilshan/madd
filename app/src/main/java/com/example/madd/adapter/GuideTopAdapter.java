package com.example.madd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.GuideDetails;
import com.example.madd.R;
import com.example.madd.model.GuidesTopData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter for Top Guide
 * @Author - https://github.com/MhmdAsq
 */
public class GuideTopAdapter extends RecyclerView.Adapter<GuideTopAdapter.GuideTopViewHolder> {

    Context context;
    List<GuidesTopData> guidesTopDataList;
    OnItemClickListner listner;

    public GuideTopAdapter(Context context, List<GuidesTopData> guidesTopDataList) {
        this.context = context;
        this.guidesTopDataList = guidesTopDataList;
    }

    @NonNull
    @Override
    public GuideTopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.guide_top_row_item,parent,false);
        return new GuideTopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideTopViewHolder holder, int position) {

        holder.PlaceguideName.setText(guidesTopDataList.get(position).getGuideName());
        holder.guidePlace.setText(guidesTopDataList.get(position).getPlace());
        holder.GuideRating.setText(guidesTopDataList.get(position).getRating());
        holder.GuideRatingBAR.setRating(guidesTopDataList.get(position).getRatingBar());
        Picasso.get().load(guidesTopDataList.get(position).getImageUrl()).into(holder.PlaceImage);
        holder.document = (guidesTopDataList.get(position).getDocument());

        final String ids = holder.document;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, GuideDetails.class);
                i.putExtra("ids",ids);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return guidesTopDataList.size();
    }


    public static final class GuideTopViewHolder extends RecyclerView.ViewHolder{

        ImageView PlaceImage;
        TextView PlaceguideName,guidePlace,GuideRating;
        RatingBar GuideRatingBAR;
        String document;



        public GuideTopViewHolder(@NonNull View itemView) {
            super(itemView);
            PlaceImage = itemView.findViewById(R.id.place_image);
            PlaceguideName = itemView.findViewById(R.id.guide_name);
            guidePlace = itemView.findViewById(R.id.guide_place);
            GuideRating = itemView.findViewById(R.id.guide_review_avg);
            GuideRatingBAR = itemView.findViewById(R.id.guide_rating_bar);
        }
    }
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }
}
