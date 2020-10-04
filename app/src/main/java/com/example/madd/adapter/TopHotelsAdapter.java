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

import com.example.madd.HotelDetails;
import com.example.madd.R;
import com.example.madd.model.TopHotelsData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopHotelsAdapter extends RecyclerView.Adapter<TopHotelsAdapter.TopHotelsViewHolder> {

    Context context;
    List<TopHotelsData> topHotelsDataList;
    OnItemClickListner listner;

    public TopHotelsAdapter(Context context, List<TopHotelsData> topHotelsDataList) {
        this.context = context;
        this.topHotelsDataList = topHotelsDataList;

    }

    @NonNull
    @Override
    public TopHotelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_top_hotel_row_item,parent,false);
        return new TopHotelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopHotelsViewHolder holder, int position) {

            holder.cityName.setText(topHotelsDataList.get(position).getCityName());
            holder.hotelName.setText(topHotelsDataList.get(position).getHotelName());
//            holder.price.setText(topHotelsDataList.get(position).getPrice());
//            holder.hotelImage.setImageResource(topHotelsDataList.get(position).getImageUrl());
        holder.document = (topHotelsDataList.get(position).getDocument());
        Picasso.get().load(topHotelsDataList.get(position).getImageUrl()).into(holder.hotelImage);
        holder.HotelRating.setText(topHotelsDataList.get(position).getRating());
        holder.HotelRatingBAR.setRating(topHotelsDataList.get(position).getRatingBar());

        final String ids = holder.document;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, HotelDetails.class);
                i.putExtra("ids",ids);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return topHotelsDataList.size();
    }

    public static  final class TopHotelsViewHolder extends RecyclerView.ViewHolder{

        ImageView hotelImage;
        TextView hotelName,cityName,HotelRating;
        RatingBar HotelRatingBAR;
        String document;;

        public TopHotelsViewHolder(@NonNull View itemView) {
            super(itemView);
        hotelImage = itemView.findViewById(R.id.hotel_image);
        hotelName = itemView.findViewById(R.id.hotel_name);
        cityName = itemView.findViewById(R.id.city_name);
        HotelRating = itemView.findViewById(R.id.hotel_review_avg);
        HotelRatingBAR = itemView.findViewById(R.id.hotel_rating_bar);


        }

    }
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }

    }

