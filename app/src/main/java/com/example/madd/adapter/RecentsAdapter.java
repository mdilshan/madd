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
import com.squareup.picasso.Picasso;

import com.example.madd.DetailsActivity;
import com.example.madd.R;
import com.example.madd.model.RecentsData;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {

    Context context;
    List<RecentsData> recentsDataList;
    RecentsAdapter.OnItemClickListner listner;

    public RecentsAdapter(Context context, List<RecentsData> recentsDataList) {
        this.context = context;
        this.recentsDataList = recentsDataList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_items, parent, false);

        // here we create a recyclerview row item layout file
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecentsViewHolder holder, int position) {

        holder.cityName.setText(recentsDataList.get(position).getCityName());
        holder.placeName.setText(recentsDataList.get(position).getPlaceName());
        //holder.distance.setText(recentsDataList.get(position).getDistance());
        Picasso.get().load(recentsDataList.get(position).getImageUrl()).into(holder.placeImage);
        holder.document = (recentsDataList.get(position).getDocument());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context, DetailsActivity.class);
                i.putExtra("ids",holder.document);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recentsDataList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, cityName;
        String document;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            cityName = itemView.findViewById(R.id.city_name);
           // distance = itemView.findViewById(R.id.distance);

        }
    }
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListner(RecentsAdapter.OnItemClickListner listner){
        this.listner = listner;
    }
}
