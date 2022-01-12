package com.developerdepository.scout.HelperClasses.DashboardHelperClasses;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.developerdepository.scout.R;

import java.util.ArrayList;

public class FeaturedLocationsAdapter extends  RecyclerView.Adapter<FeaturedLocationsAdapter.FeaturedViewHolder>  {

    ArrayList<FeaturedModel> featuredLocations;

    public FeaturedLocationsAdapter(ArrayList<FeaturedModel> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_featured_locations_recycler, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedModel featuredModel = featuredLocations.get(position);

        holder.featuredLocationImg.setImageResource(featuredModel.getImage());
        holder.featuredLocationTitle.setText(featuredModel.getTitle());
        holder.featuredLocationDesc.setText(featuredModel.getDescription());
        holder.ratingBar.setRating((float) featuredModel.getRatingBar());
        holder.featuredLocationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(featuredModel.getUrl());
               v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }

        });


    }


    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout featuredLocationItem;
        ImageView featuredLocationImg;
        TextView featuredLocationTitle, featuredLocationDesc;
        RatingBar ratingBar;
        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            featuredLocationItem = itemView.findViewById(R.id.featured_location_item);
            featuredLocationImg = itemView.findViewById(R.id.featured_location_item_img);
            featuredLocationTitle = itemView.findViewById(R.id.featured_location_item_title);
            featuredLocationDesc = itemView.findViewById(R.id.featured_location_item_desc);
            ratingBar = itemView.findViewById(R.id.featured_location_item_rating);
        }
    }

}
