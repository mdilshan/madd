package com.example.madd.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.HotelDetails;
import com.example.madd.R;
import com.example.madd.model.RecentData;

import java.util.List;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {

    Context context;
    List<RecentData> recentDataList;

    public RecentsAdapter(Context context, List<RecentData> recentDataList) {
        this.context = context;
        this.recentDataList = recentDataList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item,parent,false);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {

            holder.cityName.setText(recentDataList.get(position).getCityName());
            holder.hotelName.setText(recentDataList.get(position).getHotelName());
            holder.price.setText(recentDataList.get(position).getPrice());
            holder.hotelImage.setImageResource(recentDataList.get(position).getImageUrl());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent i = new Intent(context, HotelDetails.class);
                    context.startActivity(i);
                }
            });

    }

    @Override
    public int getItemCount() {
        return recentDataList.size();
    }

    public static  final class RecentsViewHolder extends RecyclerView.ViewHolder{

        ImageView hotelImage;
        TextView hotelName,cityName,price;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
        hotelImage = itemView.findViewById(R.id.hotel_image);
        hotelName = itemView.findViewById(R.id.hotel_name);
        cityName = itemView.findViewById(R.id.city_name);
        price = itemView.findViewById(R.id.price);


        }

    }


    }

