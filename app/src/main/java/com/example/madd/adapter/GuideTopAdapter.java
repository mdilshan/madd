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
import com.example.madd.model.GuidesTopData;

import java.util.List;

public class GuideTopAdapter extends RecyclerView.Adapter<GuideTopAdapter.GuideTopViewHolder> {

    Context context;
    List<GuidesTopData> guidesTopDataList;

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

        holder.guidePlace.setText(guidesTopDataList.get(position).getPlace());
        holder.PlaceguideName.setText(guidesTopDataList.get(position).getGuideName());
        holder.price.setText(guidesTopDataList.get(position).getPrice());
        holder.PlaceImage.setImageResource(guidesTopDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return guidesTopDataList.size();
    }


    public static final class GuideTopViewHolder extends RecyclerView.ViewHolder{

        ImageView PlaceImage;
        TextView PlaceguideName,guidePlace,price;


        public GuideTopViewHolder(@NonNull View itemView) {
            super(itemView);

            PlaceImage = itemView.findViewById(R.id.place_image);
            PlaceguideName = itemView.findViewById(R.id.guide_name);
            guidePlace = itemView.findViewById(R.id.guide_place);
            price = itemView.findViewById(R.id.guide_price);
        }
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{

            ImageView placeImage;
            TextView placeName, cityName, price;

            public RecentsViewHolder(@NonNull View itemView) {
                super(itemView);

                placeImage = itemView.findViewById(R.id.place_image);
                placeName = itemView.findViewById(R.id.place_name);
                cityName = itemView.findViewById(R.id.city_name);
                price = itemView.findViewById(R.id.price);

            }
        }
}
