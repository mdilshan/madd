package com.example.madd.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madd.DetailsActivity;
import com.example.madd.R;
import com.example.madd.model.AllPlaceData;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class AllPlaceAdapter extends RecyclerView.Adapter<AllPlaceAdapter.AllPlaceViewHolder> {
    private static final String TAG = "AllPlaceAdapter";
    Context context;
    List<AllPlaceData> allPlacesDataList;
    AllGuideAdapter.OnItemClickListner listner;

    public AllPlaceAdapter(Context context, List<AllPlaceData> allPlacesDataLists) {
        this.context = context;
        this.allPlacesDataList = allPlacesDataLists;
    }

   @NonNull
   @Override
    public AllPlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.top_places_row_item,parent,false);
        return new AllPlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPlaceViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: NAME +++++++++++++++++++++++ " +  allPlacesDataList.get(position).getPlaceName());
        holder.PlaceName.setText(allPlacesDataList.get(position).getPlaceName());
        holder.PlaceLocation.setText(allPlacesDataList.get(position).getPlaceLocation());
       // holder.Distance.setText(allPlacesDataList.get(position).getDistance());
        Picasso.get().load(allPlacesDataList.get(position).getImageUrl()).into(holder.PlaceImg);

        final String ids = allPlacesDataList.get(position).getDocument();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                Log.d(TAG, "onClick: ID ON ALLPLACEADPATER ________________ " + ids);
                i.putExtra("ids",ids);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPlacesDataList.size();
    }

    class AllPlaceViewHolder extends RecyclerView.ViewHolder{

        ImageView PlaceImg;
        TextView PlaceName,PlaceLocation,Distance;
        String document;

        public AllPlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            PlaceImg = itemView.findViewById(R.id.place_image);
            PlaceName = itemView.findViewById(R.id.place_name);
            PlaceLocation = itemView.findViewById(R.id.city_name);
            Distance = itemView.findViewById(R.id.distance);
        }
    }

    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }


}
