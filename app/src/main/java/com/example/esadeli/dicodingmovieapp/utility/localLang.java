package com.example.esadeli.dicodingmovieapp.utility;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by esadeli on 16/07/18.
 *
 * Utility class to determine local language
 */

public final class localLang {

    public static void setLang(Context context, String lang){
        Locale setLocale = new Locale(lang);
        Configuration config = new Configuration();
        config.locale = setLocale;

        context.getResources().updateConfiguration(
                config, context.getResources().getDisplayMetrics()
        );

        //Intent refreshApp = new Intent(context, MainActivity.class);
        //context.startActivity(refreshApp);
    }
}
