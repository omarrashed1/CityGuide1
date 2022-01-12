package com.developerdepository.scout.HelperClasses.DashboardHelperClasses;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.developerdepository.scout.R;

import java.util.ArrayList;

public class MostViewedLocationsAdapter extends RecyclerView.Adapter<MostViewedLocationsAdapter.MostViewedViewHolder> {

    ArrayList<MostViewedModel> mostViewedLocations;

    public MostViewedLocationsAdapter(ArrayList<MostViewedModel> mostViewedLocations) {
        this.mostViewedLocations = mostViewedLocations;

    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_most_viewed_locations_recycler, parent, false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {
        MostViewedModel mostViewedModel = mostViewedLocations.get(position);

        holder.mostViewedLocationImg.setImageResource(mostViewedModel.getImage());
        holder.mostViewedLocationTitle.setText(mostViewedModel.getTitle());
        holder.mostViewedLocationDesc.setText(mostViewedModel.getDescription());
        holder.ratingBar.setRating((float) mostViewedModel.getRatingBar());
        holder.mostViewedLocationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(mostViewedModel.getUrl());
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }

        });
    }

    @Override
    public int getItemCount() {
        return mostViewedLocations.size();
    }

    public static class MostViewedViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mostViewedLocationItem;
        ImageView mostViewedLocationImg;
        TextView mostViewedLocationTitle, mostViewedLocationDesc;
        RatingBar ratingBar;
        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            mostViewedLocationItem = itemView.findViewById(R.id.most_viewed_location_item);
            mostViewedLocationImg = itemView.findViewById(R.id.most_viewed_location_item_img);
            mostViewedLocationTitle = itemView.findViewById(R.id.most_viewed_location_item_title);
            mostViewedLocationDesc = itemView.findViewById(R.id.most_viewed_location_item_desc);
            ratingBar = itemView.findViewById(R.id.most_viewed_location_item_rating);
        }
    }
}
