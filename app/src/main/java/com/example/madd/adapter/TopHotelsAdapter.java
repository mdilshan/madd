package com.example.madd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.R;
import com.example.madd.model.TopHotelsData;

import java.util.List;

public class TopHotelsAdapter extends RecyclerView.Adapter<TopHotelsAdapter.TopHotelsViewHolder> {

    Context context;
    List<TopHotelsData> topHotelsDataList;

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
            holder.price.setText(topHotelsDataList.get(position).getPrice());
            holder.hotelImage.setImageResource(topHotelsDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return topHotelsDataList.size();
    }

    public static  final class TopHotelsViewHolder extends RecyclerView.ViewHolder{

        ImageView hotelImage;
        TextView hotelName,cityName,price;

        public TopHotelsViewHolder(@NonNull View itemView) {
            super(itemView);
        hotelImage = itemView.findViewById(R.id.hotel_image);
        hotelName = itemView.findViewById(R.id.hotel_name);
        cityName = itemView.findViewById(R.id.city_name);
        price = itemView.findViewById(R.id.distance);


        }

    }


    }

