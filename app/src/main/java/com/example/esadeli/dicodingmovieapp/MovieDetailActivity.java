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
    private static final String LOG_TAG=MovieDetailActivity.class.getSimpleName();

    public static String EXTRA_IMG_URL= "extra-img-url";
    public static String EXTRA_TITLE = "extra-title";
    public static String EXTRA_RATING = "extra-rating";
    public static String EXTRA_REL_DATE = "extra-rel-date";
    public static String EXTRA_SYNOPSIS = "extra-synopsis";

    private TextView detailTitleTV, detailRatingTV, detailRelDateTV, detailSynopsisTV;
    private ImageView detailPosterImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        detailTitleTV = (TextView)findViewById(R.id.titleDetailTV);
        detailRatingTV = (TextView)findViewById(R.id.ratingDetailTV);
        detailRelDateTV = (TextView)findViewById(R.id.releaseDateDetailTV);
        detailSynopsisTV = (TextView) findViewById(R.id.synopsisTV);

        detailPosterImg = (ImageView)findViewById(R.id.big_poster_img);

        String titleDetail = getIntent().getStringExtra(EXTRA_TITLE);
        String imgUrlDetail = getIntent().getStringExtra(EXTRA_IMG_URL);
        String ratingDetail = getIntent().getStringExtra(EXTRA_RATING);
        String relDateDetail = getIntent().getStringExtra(EXTRA_REL_DATE);
        String synopsisDetail = getIntent().getStringExtra(EXTRA_SYNOPSIS);

        detailTitleTV.setText(titleDetail);
        detailRatingTV.setText(ratingDetail);
        detailRelDateTV.setText(relDateDetail);
        detailSynopsisTV.setText(synopsisDetail);

        Glide.with(this)
                .load(imgUrlDetail)
                .override(250,250)
                .crossFade()
                .into(detailPosterImg);
    }
}
