package com.example.esadeli.dicodingmovieapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.MovieColumns.COLUMN_IMAGE_LINK;
import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.MovieColumns.COLUMN_OVERVIEW;
import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.MovieColumns.COLUMN_RATING;
import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.MovieColumns.COLUMN_RELEASE_DATE;
import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.MovieColumns.COLUMN_TITLE;
import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.MovieColumns.CONTENT_URI;

/**
 * Activity to see detail data of the selected movie
 *
 */
public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static String EXTRA_IMG_URL= "extra-img-url";
    public static String EXTRA_TITLE = "extra-title";
    public static String EXTRA_RATING = "extra-rating";
    public static String EXTRA_REL_DATE = "extra-rel-date";
    public static String EXTRA_SYNOPSIS = "extra-synopsis";
    public static String EXTRA_FROM_FAVORITE = "extra-from-favorite";

    //View Object
    private Button databaseBtn;

    //String data:
    String titleDetail;
    String imgUrlDetail;
    String ratingDetail;
    String relDateDetail;
    String synopsisDetail;
    String fromFavorite;  // string to differentiate button behavior

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        TextView detailTitleTV = findViewById(R.id.titleDetailTV);
        TextView detailRatingTV = findViewById(R.id.ratingDetailTV);
        TextView detailRelDateTV = findViewById(R.id.releaseDateDetailTV);
        TextView detailSynopsisTV = findViewById(R.id.synopsisTV);

        ImageView detailPosterImg = findViewById(R.id.big_poster_img);

        databaseBtn = findViewById(R.id.database_btn);

        titleDetail = getIntent().getStringExtra(EXTRA_TITLE);
        imgUrlDetail = getIntent().getStringExtra(EXTRA_IMG_URL);
        ratingDetail = getIntent().getStringExtra(EXTRA_RATING);
        relDateDetail = getIntent().getStringExtra(EXTRA_REL_DATE);
        synopsisDetail = getIntent().getStringExtra(EXTRA_SYNOPSIS);
        fromFavorite = getIntent().getStringExtra(EXTRA_FROM_FAVORITE);

        // Customized button name
        if(fromFavorite==null){
            databaseBtn.setText(R.string.button_save);
        }else if(fromFavorite.equals(getResources().getString(R.string.button_save_disabled))){
            databaseBtn.setVisibility(View.GONE);
        }

        databaseBtn.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.database_btn){

            if(fromFavorite == null){
                //send data to database
                ContentValues movieDetailValues = new ContentValues();
                movieDetailValues.put(COLUMN_TITLE,titleDetail);
                movieDetailValues.put(COLUMN_RATING,ratingDetail);
                movieDetailValues.put(COLUMN_RELEASE_DATE,relDateDetail);
                movieDetailValues.put(COLUMN_OVERVIEW,synopsisDetail);
                movieDetailValues.put(COLUMN_IMAGE_LINK,imgUrlDetail);

                getContentResolver().insert(CONTENT_URI,movieDetailValues);

                Toast.makeText(this,"Save "+titleDetail +" as favorite",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
