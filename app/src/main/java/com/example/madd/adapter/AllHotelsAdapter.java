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
import com.example.madd.HotelDetails;
import com.example.madd.R;
import com.example.madd.model.AllHotelsData;
import com.example.madd.model.RecentData;
import com.example.madd.model.RecentsData;

import java.util.List;

public class AllHotelsAdapter extends RecyclerView.Adapter<AllHotelsAdapter.AllHotelsViewHolder> {

    Context context;
    List<AllHotelsData> allHotelsDataList;

    public AllHotelsAdapter(Context context, List<AllHotelsData> allHotelsDataList) {
        this.context = context;
        this.allHotelsDataList = allHotelsDataList;
    }

    @NonNull
    @Override
    public AllHotelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_top_hotel_row_item,parent,false);
        return new AllHotelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllHotelsViewHolder holder, int position) {

            holder.cityName.setText(allHotelsDataList.get(position).getCityName());
            holder.hotelName.setText(allHotelsDataList.get(position).getHotelName());
            holder.price.setText(allHotelsDataList.get(position).getPrice());
            holder.hotelImage.setImageResource(allHotelsDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return allHotelsDataList.size();
    }

    public static  final class AllHotelsViewHolder extends RecyclerView.ViewHolder{

        ImageView hotelImage;
        TextView hotelName,cityName,price;

        public AllHotelsViewHolder(@NonNull View itemView) {
            super(itemView);
        hotelImage = itemView.findViewById(R.id.hotel_image);
        hotelName = itemView.findViewById(R.id.hotel_name);
        cityName = itemView.findViewById(R.id.city_name);
        price = itemView.findViewById(R.id.price);


        }

    }



}

