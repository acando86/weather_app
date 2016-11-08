package com.weatherapp.weatherapp.data.remote.pojo;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * City data in API response.
 * Created by alessandro.candolini on 08/11/2016.
 */
@JsonObject
public class WeatherCity {

    @JsonField
    private String country;

    @JsonField
    private WeatherCityCoord coord;

    @JsonField
    private String name;

    @JsonField
    private int id;

    public WeatherCity() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public WeatherCityCoord getCoord() {
        return coord;
    }

    public void setCoord(WeatherCityCoord coord) {
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
