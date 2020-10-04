package com.example.madd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.madd.HotelDetails;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.madd.HotelSeeAll;
import com.example.madd.R;
import com.example.madd.model.AllHotelsData;

import java.util.List;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class AllHotelsAdapter extends RecyclerView.Adapter<AllHotelsAdapter.AllHotelsViewHolder> {

    Context context;
    List<AllHotelsData> allHotelsDataList;
    OnItemClickListner listner;


    public AllHotelsAdapter(Context context , List<AllHotelsData> allHotelsDataList) {
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

            holder.cityName.setText(allHotelsDataList.get(position).getLocation());
            holder.hotelName.setText(allHotelsDataList.get(position).getHotelName());
        holder.HotelRating.setText(allHotelsDataList.get(position).getRating());
        holder.HotelRatingBAR.setRating(allHotelsDataList.get(position).getRatingBar());
        Picasso.get().load(allHotelsDataList.get(position).getImageUrl()).into(holder.hotelImage);
        holder.document = (allHotelsDataList.get(position).getDocument());

            final String ids = holder.document;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,HotelDetails.class);
                    i.putExtra("ids",ids);
                    context.startActivity(i);
                }
            });


    }

    @Override
    public int getItemCount() {
        return allHotelsDataList.size();
    }


    class AllHotelsViewHolder extends RecyclerView.ViewHolder{


        ImageView hotelImage;
        TextView hotelName,cityName,HotelRating;
        RatingBar HotelRatingBAR;
        String document;

        public AllHotelsViewHolder(@NonNull View itemView) {
            super(itemView);
        hotelImage = itemView.findViewById(R.id.hotel_image);
        hotelName = itemView.findViewById(R.id.hotel_name);
        cityName = itemView.findViewById(R.id.city_name);
        HotelRating = itemView.findViewById(R.id.hotel_review_avg);
        HotelRatingBAR = itemView.findViewById(R.id.hotel_rating_bar);


        }

    }

    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }
    }

