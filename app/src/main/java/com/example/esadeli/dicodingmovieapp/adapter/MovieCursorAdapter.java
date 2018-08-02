package com.example.esadeli.dicodingmovieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import com.example.esadeli.dicodingmovieapp.model.MovieDataCursor;


/**
 * Created by esadeli on 02/08/18.
 *
 * Cursor adapter for Favorite Movie
 */

public class MovieCursorAdapter extends RecyclerView.Adapter<MovieCursorAdapter.movieCursorHolder> {


    private Cursor movieCursorList;
    private Context mContext;

    //Constant to differentiate button behavior
    private static final String FROM_FAVORITE = "favorite-movie";

    public MovieCursorAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public Cursor getMovieCursorList() {
        return movieCursorList;
    }

    public void setMovieCursorList(Cursor movieCursorList) {
        this.movieCursorList = movieCursorList;
    }

    @Override
    public movieCursorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_list,parent,false);

        return new movieCursorHolder(view);
    }

    @Override
    public void onBindViewHolder(movieCursorHolder holder, int position) {


        final MovieDataCursor movieCursor = getItem(position);
        final String holderTitle = movieCursor.getTitleCursor();
        final String holderImgUrl = movieCursor.getImageLinkCursor();
        final String holderRating = movieCursor.getRatingCursor();
        final String holderRelDate = movieCursor.getReleaseDateCursor();
        final String holderSynopsis = movieCursor.getOverviewCursor();


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
                openDetailIntent(holderTitle,
                                holderImgUrl,
                                holderRating,
                                holderRelDate,
                                holderSynopsis,
                                FROM_FAVORITE);

                Snackbar.make(v,"Opening Detail: "+holderTitle, Snackbar.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        if (movieCursorList == null) {
            return 0;
        }
        return movieCursorList.getCount();
    }

    private MovieDataCursor getItem(int position){
        if (!movieCursorList.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new MovieDataCursor(movieCursorList);
    }

    public class movieCursorHolder extends RecyclerView.ViewHolder{

        private TextView titleText;
        private TextView releaseDateText;
        private ImageView smallImage;
        private TextView ratingText;
        private RelativeLayout itemContentHolder;

        public movieCursorHolder(View itemView) {
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
                                  String synopsis,
                                  String fromFavorite) {
        Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

        detailIntent.putExtra(MovieDetailActivity.EXTRA_TITLE,title);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_IMG_URL,imageUrl);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_RATING,rating);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_REL_DATE,releaseDate);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_SYNOPSIS,synopsis);
        detailIntent.putExtra(MovieDetailActivity.EXTRA_FROM_FAVORITE, fromFavorite);

        mContext.startActivity(detailIntent);

    }
}
