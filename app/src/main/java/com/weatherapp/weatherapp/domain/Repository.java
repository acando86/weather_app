package com.weatherapp.weatherapp.domain;

import com.weatherapp.weatherapp.Constants;
import com.weatherapp.weatherapp.data.remote.WeatherService;
import com.weatherapp.weatherapp.data.remote.pojo.WeatherList;
import com.weatherapp.weatherapp.data.remote.pojo.WeatherResponse;
import com.weatherapp.weatherapp.helper.Formatter;
import com.weatherapp.weatherapp.presentation.home.HomeItem;
import com.weatherapp.weatherapp.presentation.home.HomeModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Provides additional layer between data requesrts and presentation layer.
 * This layer can be used to handle cache, or to map netwrok models into view models.
 * Created by alessandro.candolini on 08/11/2016.
 */

public class Repository {

    /**
     * Rest service for API request
     */
    private WeatherService service;

    /**
     * Formatter to format output
     */
   private Formatter formatter;

    public Repository(WeatherService service, Formatter formatter) {
        this.service = service;
        this.formatter = formatter;
    }

    /**
     * Call network layer and provides new fresh data in the correct format.
     * <P>This class offers an additional layer to wrap network request, handle cache, offline mode etc</P>
     * @param cityId id of the town
     * @return obnservabe of home model
     */
    public Observable<HomeModel> retriveHomeModel(int cityId) {
        Observable<WeatherResponse>  networkObservable =      service.fetchWeatherForecasts(cityId);

        // add here other observables for cache,file etc and concat them to have a basic cache

        return networkObservable .map(new Func1<WeatherResponse, HomeModel>() {

            @Override
            public HomeModel call(WeatherResponse response) {
                HomeModel homeModel = new HomeModel();
                WeatherList[] array = response.getList();
                List<HomeItem> list = new ArrayList<>();
                for (int loop =0; loop < array.length; loop++) {
                    HomeItem item = new HomeItem();
                    WeatherList weatherList = array[loop];
                    item.setTime(formatter.formattedDateFromString(weatherList.getForecastTime()));
                    item.setDescription(weatherList.getWeather()[0].getDescription());
                    item.setTemperature(formatter.formatTemperature(weatherList.getMain().getTemp()));
                    list.add(item);
                }
                homeModel.setList(list);
                if ( response.getCity() != null ) {
                    homeModel.setTown(response.getCity().getName());
                }
                return homeModel;
            }
        });
    }

}
