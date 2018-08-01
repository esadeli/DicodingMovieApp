package com.example.esadeli.dicodingmovieapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.esadeli.dicodingmovieapp.adapter.MoviePagerAdapter;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.lang_setting_menu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String userPrefLang;
        switch (item.getItemId()){
            case R.id.indo_lang:
                userPrefLang = "id";
                localLang.setLang(this,userPrefLang);
                finish();
                return true;

            case R.id.eng_lang:
                userPrefLang = "en";
                localLang.setLang(this,userPrefLang);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
