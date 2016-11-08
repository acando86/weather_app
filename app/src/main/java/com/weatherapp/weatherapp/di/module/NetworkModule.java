package com.weatherapp.weatherapp.di.module;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.weatherapp.weatherapp.data.remote.AuthenticationInterceptor;
import com.weatherapp.weatherapp.data.remote.WeatherService;
import com.weatherapp.weatherapp.domain.Repository;
import com.weatherapp.weatherapp.helper.Formatter;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by alessandro.candolini on 08/11/2016.
 */
@Module
public class NetworkModule {

    /** Default network connection timeout for new connections, in seconds */
    private final int CONNECTION_TIMEOUT = 30;

    /** Default network read timeout for new connections, in seconds */
    private final int READ_TIMEOUT = 30;

    /** Base url for all API request. */
    private final String baseUrl;

    /** Api Key for all API request. */
    private final String apiKey;

    public NetworkModule(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }


    @Provides
    Interceptor provideInterceptor() {
        return  new AuthenticationInterceptor(apiKey) ;
    }

    /**
     * @return instance of the okhttp client builder properly configured, this builder is used to instantiate the okttp client
     */
    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder(Interceptor interceptor) {

        return new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(interceptor)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS);
    }

    /**
     * @param builder okhttpclient builder
     * @return instance of the okhttp client configured accoridng to the provided builder
     */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Singleton
    @Provides
    @Named("logan_square_converter")
    Converter.Factory provideConvertFactory(){
        return LoganSquareConverterFactory.create();
    }

    @Singleton
    @Provides
    CallAdapter.Factory provideCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client,
                             @Named("logan_square_converter") Converter.Factory converterFactory,
                             CallAdapter.Factory callAdapterFactory
    ) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .baseUrl(baseUrl)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    WeatherService provideWeatherRest(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Singleton
    @Provides
    Formatter provideFormatter(Retrofit retrofit) {
        return new Formatter();
    }


    @Singleton
    @Provides
    Repository provideRpository(Formatter formatter, WeatherService service) {
        return new Repository(service,formatter);
    }

}
