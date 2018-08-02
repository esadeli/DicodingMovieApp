package com.example.esadeli.dicodingmovieapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.esadeli.dicodingmovieapp.FavoriteFragment;
import com.example.esadeli.dicodingmovieapp.NowFragment;
import com.example.esadeli.dicodingmovieapp.R;
import com.example.esadeli.dicodingmovieapp.SearchFragment;
import com.example.esadeli.dicodingmovieapp.UpcomingFragment;

/**
 * Created by esadeli on 16/07/18.
 *
 * Extension of FragmentPagerAdapter class for Tab title display
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MoviePagerAdapter(Context context, FragmentManager fm) {

        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new NowFragment();
        }else if(position == 1){
            return new UpcomingFragment();
        } else if(position ==2){
            return  new FavoriteFragment();
        }else{
            return new SearchFragment();
        }
    }

    @Override
    public int getCount() {

        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getResources().getString(R.string.playing_now_movie);
            case 1:
                return mContext.getResources().getString(R.string.upcoming_movie);
            case 2:
                return mContext.getResources().getString(R.string.favorite);
            case 3:
                return mContext.getResources().getString(R.string.search_movie);
            default:
                return super.getPageTitle(position);
        }
    }
}
