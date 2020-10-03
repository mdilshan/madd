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
import com.example.madd.model.GuideRecentsData;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class GuideRecentsAdapter extends RecyclerView.Adapter<GuideRecentsAdapter.GuideRecentsViewHolder> {

    Context context;
    List<GuideRecentsData> recentsDataList;
    OnItemClickListner listner;

    public GuideRecentsAdapter(Context context, List<GuideRecentsData> recentsDataList) {
        this.context = context;
        this.recentsDataList = recentsDataList;
    }

    @NonNull
    @Override
    public GuideRecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.guide_recents_row_item,parent,false);
        return new GuideRecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideRecentsViewHolder holder, int position) {

        holder.PlaceguideName.setText(recentsDataList.get(position).getGuideName());
        holder.guidePlace.setText(recentsDataList.get(position).getPlace());
        holder.PlaceImage.setImageResource(recentsDataList.get(position).getImageUrl());
        holder.document = (recentsDataList.get(position).getDocument());

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
        return recentsDataList.size();
    }


    public static final class GuideRecentsViewHolder extends RecyclerView.ViewHolder{

        ImageView PlaceImage;
        TextView PlaceguideName,guidePlace;
        String document;


        public GuideRecentsViewHolder(@NonNull View itemView) {
            super(itemView);

            PlaceImage = itemView.findViewById(R.id.place_recnet_image);
            PlaceguideName = itemView.findViewById(R.id.guide_recent_name);
            guidePlace = itemView.findViewById(R.id.guide_recent_place);
        }
    }
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }
}
