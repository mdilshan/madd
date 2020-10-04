package com.example.madd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.DetailsActivity;
import com.example.madd.GuideDetails;
import com.example.madd.R;
import com.example.madd.model.TopPlacesData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopPlacesAdapter extends RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder> {

    Context context;
    List<TopPlacesData> topPlacesDataList;
    TopPlacesAdapter.OnItemClickListner listner;

    public TopPlacesAdapter(Context context, List<TopPlacesData> topPlacesDataList) {
        this.context = context;
        this.topPlacesDataList = topPlacesDataList;
    }

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.top_places_row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new TopPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlacesViewHolder holder, int position) {

        holder.cityName.setText(topPlacesDataList.get(position).getCityName());
        holder.placeName.setText(topPlacesDataList.get(position).getPlaceName());
        //holder.distance.setText(topPlacesDataList.get(position).getDistance());
        Picasso.get().load(topPlacesDataList.get(position).getImageUrl()).into(holder.placeImage);
        holder.PlaceRating.setText(topPlacesDataList.get(position).getRating());
        holder.document = (topPlacesDataList.get(position).getDocument());
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
        return topPlacesDataList.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, cityName, distance,PlaceRating,PlaceRateBar;
        String document;


        public TopPlacesViewHolder(@NonNull View itemView) {
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

    public void setOnItemClickListner(TopPlacesAdapter.OnItemClickListner listner){
        this.listner = listner;
    }
}


