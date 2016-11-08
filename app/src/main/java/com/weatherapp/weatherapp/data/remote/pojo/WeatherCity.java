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

}
