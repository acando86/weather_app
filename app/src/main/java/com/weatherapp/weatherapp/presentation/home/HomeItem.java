package com.weatherapp.weatherapp.presentation.home;

/**
 * Model for Single item in the list
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomeItem {

    private String time;
    private String description;
    private String temperature;

    public HomeItem() {
        super();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
