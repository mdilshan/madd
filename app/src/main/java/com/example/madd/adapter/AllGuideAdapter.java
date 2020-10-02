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

import com.example.madd.GuideDetails;
import com.example.madd.R;
import com.example.madd.model.AllGuideData;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class AllGuideAdapter extends RecyclerView.Adapter<AllGuideAdapter.AllGuidesViewHolder> {

    Context context;
    List<AllGuideData> allGuidesDataList;
    OnItemClickListner listner;

    public AllGuideAdapter(Context context, List<AllGuideData> allGuidesDataList) {
        this.context = context;
        this.allGuidesDataList = allGuidesDataList;
    }

    @NonNull
    @Override
    public AllGuidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.guide_top_row_item,parent,false);
        return new AllGuidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllGuidesViewHolder holder, final int position) {

        holder.PlaceguideName.setText(allGuidesDataList.get(position).getGuideName());
        holder.guidePlace.setText(allGuidesDataList.get(position).getPlace());
        holder.price.setText(allGuidesDataList.get(position).getPrice());
        holder.PlaceImage.setImageResource(allGuidesDataList.get(position).getImageUrl());
        holder.document = (allGuidesDataList.get(position).getDocument());

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
        return allGuidesDataList.size();
    }

    class AllGuidesViewHolder extends RecyclerView.ViewHolder{

        ImageView PlaceImage;
        TextView PlaceguideName,guidePlace,price;
        String document;

        public AllGuidesViewHolder(@NonNull View itemView) {
            super(itemView);
            PlaceImage = itemView.findViewById(R.id.place_image);
            PlaceguideName = itemView.findViewById(R.id.guide_name);
            guidePlace = itemView.findViewById(R.id.guide_place);
            price = itemView.findViewById(R.id.guide_price);
        }
    }

    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }

}