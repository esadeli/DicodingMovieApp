package com.example.esadeli.dicodingmovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Activity to see detail data of the selected movie
 *
 */
public class MovieDetailActivity extends AppCompatActivity {

    public static String EXTRA_IMG_URL= "extra-img-url";
    public static String EXTRA_TITLE = "extra-title";
    public static String EXTRA_RATING = "extra-rating";
    public static String EXTRA_REL_DATE = "extra-rel-date";
    public static String EXTRA_SYNOPSIS = "extra-synopsis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        TextView detailTitleTV = findViewById(R.id.titleDetailTV);
        TextView detailRatingTV = findViewById(R.id.ratingDetailTV);
        TextView detailRelDateTV = findViewById(R.id.releaseDateDetailTV);
        TextView detailSynopsisTV = findViewById(R.id.synopsisTV);

        ImageView detailPosterImg = findViewById(R.id.big_poster_img);

        String titleDetail = getIntent().getStringExtra(EXTRA_TITLE);
        String imgUrlDetail = getIntent().getStringExtra(EXTRA_IMG_URL);
        String ratingDetail = getIntent().getStringExtra(EXTRA_RATING);
        String relDateDetail = getIntent().getStringExtra(EXTRA_REL_DATE);
        String synopsisDetail = getIntent().getStringExtra(EXTRA_SYNOPSIS);

        detailTitleTV.setText(titleDetail);
        detailRatingTV.setText(ratingDetail);
        detailRelDateTV.setText(relDateDetail);
        detailSynopsisTV.setText(synopsisDetail);
        detailPosterImg.setContentDescription(titleDetail);

        Glide.with(this)
                .load(imgUrlDetail)
                .override(250,250)
                .crossFade()
                .into(detailPosterImg);
    }
}
