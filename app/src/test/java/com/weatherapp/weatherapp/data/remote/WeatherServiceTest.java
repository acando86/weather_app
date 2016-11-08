package com.weatherapp.weatherapp.data.remote;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.weatherapp.weatherapp.data.remote.pojo.WeatherResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.observers.TestSubscriber;

/**
 * Use mock server to test authentication interceptor. Here just a simple test that the API_KET is present, can be improved.
 * Created by alessandro.candolini on 08/11/2016.
 */

public class WeatherServiceTest {

    MockWebServer mockWebServer;

    Retrofit retrofit;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }


    @Test
    public void testRetrofitSuccess() throws Exception {

        TestSubscriber<WeatherResponse> testSubscriber = new TestSubscriber<>();

        mockWebServer.enqueue(new MockResponse().setBody("{\"city\":{\"id\":524901,\"name\":\"Moscow\"}}")); // very basic response mocked, we can store the tntire response

        WeatherService service = retrofit.create(WeatherService.class);
        service.fetchWeatherForecasts(23).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();

    }




}



