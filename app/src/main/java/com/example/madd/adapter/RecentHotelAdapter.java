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

import com.example.madd.HotelDetails;
import com.example.madd.R;
import com.example.madd.model.RecentHotelData;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecentHotelAdapter extends RecyclerView.Adapter<RecentHotelAdapter.RecentsViewHolder> {

    Context context;
    List<RecentHotelData> recentHotelDataList;
    OnItemClickListner listner;

    public RecentHotelAdapter(Context context, List<RecentHotelData> recentHotelDataList) {
        this.context = context;
        this.recentHotelDataList = recentHotelDataList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_hotel_item,parent,false);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {

            holder.cityName.setText(recentHotelDataList.get(position).getCityName());
            holder.hotelName.setText(recentHotelDataList.get(position).getHotelName());
            //holder.price.setText(recentHotelDataList.get(position).getPrice());
           // holder.hotelImage.setImageResource(recentHotelDataList.get(position).getImageUrl());
        holder.document = (recentHotelDataList.get(position).getDocument());

        Picasso.get().load(recentHotelDataList.get(position).getImageUrl()).into(holder.hotelImage);

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
        return recentHotelDataList.size();
    }

    public static  final class RecentsViewHolder extends RecyclerView.ViewHolder{

        ImageView hotelImage;
        TextView hotelName,cityName;
        String document;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
        hotelImage = itemView.findViewById(R.id.hotel_image);
        hotelName = itemView.findViewById(R.id.hotel_name);
        cityName = itemView.findViewById(R.id.city_name);
        //price = itemView.findViewById(R.id.distance);


        }

    }
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }

    }

