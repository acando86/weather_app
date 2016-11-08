package com.weatherapp.weatherapp.presentation.home;

import android.util.Log;

import com.weatherapp.weatherapp.BuildConfig;
import com.weatherapp.weatherapp.Constants;
import com.weatherapp.weatherapp.data.remote.WeatherService;
import com.weatherapp.weatherapp.data.remote.pojo.WeatherList;
import com.weatherapp.weatherapp.data.remote.pojo.WeatherResponse;
import com.weatherapp.weatherapp.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    private static final String LOG_TAG = "HomePresenterImpl";

    @Inject
    WeatherService restClient;

    @Override
    public void attachView(HomeContract.HomeView mvpView) {
        super.attachView(mvpView);
        getMvpView().hideError();
        getMvpView().showLoading();
        refreshData();
    }

    @Override
    public void refreshData() {
        restClient.fetchWeatherForecasts(Constants.LOCATION_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherResponse>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        if (BuildConfig.ENABLE_LOGS) Log.e(LOG_TAG, e.getMessage());
                        getMvpView().showError();
                    }

                    @Override
                    public final void onNext(WeatherResponse response) {
                        WeatherList[] array = response.getList();
                        List<HomeItem> list = new ArrayList<>();
                        for (int loop =0; loop < array.length; loop++) {
                            HomeItem item = new HomeItem();
                            WeatherList weatherList = array[loop];
                            item.setTime(weatherList.getForecastTime()); // TODO format
                            item.setDescription(weatherList.getWeather()[0].getDescription());
                            item.setTemperature(Double.toString(weatherList.getMain().getTemp()));
                            list.add(item);
                        }
                        getMvpView().showResults(list);
                        getMvpView().hideLoading();
                        getMvpView().hideError();
                        if ( response.getCity() != null ) {
                            getMvpView().setTown(response.getCity().getName());
                        }

                    }
                });
    }
}
