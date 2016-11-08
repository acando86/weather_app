package com.weatherapp.weatherapp.data.remote.pojo;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Model for API response.
 * <p>
 * Contains logansquare annotation: better to map this model to a view model in the presentation layer and
 * use another mapped object for persistency
 * Created by alessandro.candolini on 08/11/2016.
 */
@JsonObject
public class WeatherResponse {

    @JsonField
    private WeatherCity city;

    @JsonField
    private double message;

    @JsonField
    private WeatherList[] list;

    public WeatherResponse() {
    }

    public WeatherCity getCity() {
        return city;
    }

    public void setCity(WeatherCity city) {
        this.city = city;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public WeatherList[] getList() {
        return list;
    }

    public void setList(WeatherList[] list) {
        this.list = list;
    }
}
