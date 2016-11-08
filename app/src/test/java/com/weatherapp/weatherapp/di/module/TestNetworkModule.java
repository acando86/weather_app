package com.weatherapp.weatherapp.di.module;

import com.weatherapp.weatherapp.data.remote.WeatherService;
import com.weatherapp.weatherapp.helper.Formatter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;


@Module
public class TestNetworkModule extends NetworkModule {

    public Scheduler ioScheduler = Schedulers.test();
    public Scheduler mainThreadScheduler = Schedulers.test();
    public Formatter mockFormatter = mock(Formatter.class);
    public WeatherService mockPublicApi = mock(WeatherService.class);

    public TestNetworkModule() {
        super("/", "elwhfiw");
    }

    @Override
    @Singleton
    @Provides
    WeatherService provideWeatherRest(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Override
    @Singleton
    @Provides
    Formatter provideFormatter(Retrofit retrofit) {
        return new Formatter();
    }
}