package com.weatherapp.weatherapp.data.remote.pojo;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by alessandro.candolini on 08/11/2016.
 */
@JsonObject
public class WeatherCityCoord {

    @JsonField
    private double lon;

    @JsonField
    private double lat;

    public double getLon() {
        return lon;
    }

    public WeatherCityCoord() {
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
