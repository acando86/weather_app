package com.weatherapp.weatherapp.data.remote.pojo;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Pojo for network request.
 * <p>
 *     Generated using json2class android studio plugin and annotated with loganquare annotations for
 *     parsing. Not meant to be used in the presentation layer or for persistency
 * </p>
 * Created by alessandro.candolini on 08/11/2016.
 */
@JsonObject
public class WeatherList {

        /** Time of data forecasted, unix, UTC */
        @JsonField(name = "dt_txt")
        private String forecastTime;

        @JsonField
        private WeatherListWeather[] weather;

        @JsonField
        private WeatherListMain main;

        public String getForecastTime() {
            return forecastTime;
        }

        public void setForecastTime(String forecastTime) {
            this.forecastTime = forecastTime;
        }

        public WeatherListWeather[] getWeather() {
            return this.weather;
        }

        public void setWeather(WeatherListWeather[] weather) {
            this.weather = weather;
        }

        public WeatherListMain getMain() {
            return this.main;
        }

        public void setMain(WeatherListMain main) {
            this.main = main;
        }


}
