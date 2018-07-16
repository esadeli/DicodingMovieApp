package com.example.esadeli.dicodingmovieapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.esadeli.dicodingmovieapp.utility.localLang;

/**
 * Updating GitHub due to the first push did not involve all files in the DicodingMovieApp folder
 */
    public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = findViewById(R.id.view_pager);
        MoviePagerAdapter pagerAdapter =
                new MoviePagerAdapter(MainActivity.this,getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_id);
        tabLayout.setupWithViewPager(viewPager);
    }
}
