package com.example.esadeli.dicodingmovieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.esadeli.dicodingmovieapp.MovieDetailActivity;
import com.example.esadeli.dicodingmovieapp.R;
import com.example.esadeli.dicodingmovieapp.model.movieData;

import java.util.ArrayList;

/**
 * Created by esadeli on 04/07/18.
 *
 * Adapter for displaying movie data, extension of RecyclerView class
 */

public class movieDataAdapter extends RecyclerView.Adapter<movieDataAdapter.movieDataHolder> {

    private ArrayList<movieData> movieList;
    private Context mContext;

    public movieDataAdapter(Context mContext, ArrayList<movieData> movieList) {
        this.movieList = movieList;
        this.mContext = mContext;
    }

    @Override
    public movieDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_list,parent,false);

        movieDataHolder holder = new movieDataHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(movieDataHolder holder, int position) {

        final String holderTitle = movieList.get(position).getTitle();
        final String holderImgUrl = movieList.get(position).getImageLink();
        final String holderRating = movieList.get(position).getRating();
        final String holderRelDate = movieList.get(position).getReleaseDate();
        final String holderSynopsis = movieList.get(position).getOverview();

        holder.titleText.setText(holderTitle);
        holder.ratingText.setText(holderRating);
        holder.releaseDateText.setText(holderRelDate);

        Glide.with(mContext)
                .load(holderImgUrl)
                .override(60,60)
                .crossFade()
                .into(holder.smallImage);

        holder.itemContentHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailIntent(holderTitle,holderImgUrl,holderRating,holderRelDate,holderSynopsis);

                Snackbar.make(v,"Opening Detail: "+holderTitle, Snackbar.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class movieDataHolder extends RecyclerView.ViewHolder{

        private TextView titleText;
        private TextView releaseDateText;
        private ImageView smallImage;
        private TextView ratingText;
        private RelativeLayout itemContentHolder;

        public movieDataHolder(View itemView) {
            super(itemView);
            itemContentHolder = itemView.findViewById(R.id.movie_content_item);
            titleText = itemView.findViewById(R.id.titleTV);
            releaseDateText = itemView.findViewById(R.id.releaseDateTV);
            smallImage = itemView.findViewById(R.id.small_poster_img);
            ratingText = itemView.findViewById(R.id.ratingTV);
        }
    }

    // Function to open explicit intent for more detail data
    private void openDetailIntent(String title,
                                  String imageUrl,
                                  String rating,
                                  String releaseDate,
                                  String synopsis) {
        Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

        detailIntent.putExtra(MovieDetailActivity.EXTRA_TITLE,title);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_IMG_URL,imageUrl);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_RATING,rating);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_REL_DATE,releaseDate);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_SYNOPSIS,synopsis);

        mContext.startActivity(detailIntent);

    }
}

