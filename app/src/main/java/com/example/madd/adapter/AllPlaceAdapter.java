package com.example.madd.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.DetailsActivity;
import com.example.madd.R;
import com.example.madd.model.AllPlaceData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllPlaceAdapter extends RecyclerView.Adapter<AllPlaceAdapter.AllPlaceViewHolder> {
    private static final String TAG = "AllPlaceAdapter";
    Context context;
    List<AllPlaceData> allPlacesDataList;
    AllGuideAdapter.OnItemClickListner listner;

    public AllPlaceAdapter(Context context, List<AllPlaceData> allPlacesDataLists) {
        this.context = context;
        this.allPlacesDataList = allPlacesDataLists;
    }

   @NonNull
   @Override
    public AllPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.top_places_row_item,parent,false);
        return new AllPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPlaceViewHolder holder, final int position) {
        holder.cityName.setText(allPlacesDataList.get(position).getCityName());
        holder.placeName.setText(allPlacesDataList.get(position).getPlaceName());
        //holder.distance.setText(topPlacesDataList.get(position).getDistance());
        Picasso.get().load(allPlacesDataList.get(position).getImageUrl()).into(holder.placeImage);
        holder.PlaceRating.setText(allPlacesDataList.get(position).getRating());
        holder.document = (allPlacesDataList.get(position).getDocument());
        //holder.PlaceRateBar.setRating(topPlacesDataList.get(position).getPlaceRateBar());


        final String ids = holder.document;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("ids",ids);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPlacesDataList.size();
    }

    class AllPlaceViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, cityName, distance,PlaceRating,PlaceRateBar;
        String document;

        public AllPlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            cityName = itemView.findViewById(R.id.city_name);
            // distance = itemView.findViewById(R.id.distance);
            PlaceRating = itemView.findViewById(R.id.place_review_avg);
            PlaceRateBar = itemView.findViewById(R.id.place_rating_bars);
        }
    }

    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
}
