package com.weatherapp.weatherapp.helper;

import android.util.Log;

import com.weatherapp.weatherapp.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utility methods to help displayingj the forecast data.
 * Created by alessandro.candolini on 07/11/2016.
 */

public class Formatter {

    public String formattedDateFromString(String inputDate){

        SimpleDateFormat df_input = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.UK);
        SimpleDateFormat df_output = new SimpleDateFormat( "EEEE d MMMM yyyy", Locale.UK);

        Date parsed = null;
        String outputDate = "";
        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);
        } catch (Exception e) {
            if (BuildConfig.ENABLE_LOGS) Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
        }
        return outputDate;

    }

    public String formatTemperature(double temperature) {
        return String.valueOf(Math.round(temperature)) + "°";
    }


}
