package com.example.esadeli.dicodingmovieapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by esadeli on 16/07/18.
 *
 * Extension of FragmentPagerAdapter class for Tab title display
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    MoviePagerAdapter(Context context, FragmentManager fm) {

        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new NowFragment();
        }else if(position == 1){
            return new UpcomingFragment();
        }else{
            return new SearchFragment();
        }
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getResources().getString(R.string.playing_now_movie);
            case 1:
                return mContext.getResources().getString(R.string.upcoming_movie);
            case 2:
                return mContext.getResources().getString(R.string.search_movie);
            default:
                return super.getPageTitle(position);
        }
    }
}
