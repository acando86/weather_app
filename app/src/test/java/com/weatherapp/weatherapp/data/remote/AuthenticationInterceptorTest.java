package com.weatherapp.weatherapp.data.remote;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Use mock server to test authentication interceptor. Here just a simple test that the API_KET is present, can be improved.
 * Created by alessandro.candolini on 08/11/2016.
 */

public class AuthenticationInterceptorTest {

    MockWebServer mockWebServer;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }


    @Test
    public void testHttpInterceptor() throws Exception {

        mockWebServer.enqueue(new MockResponse());
        String apiKey = "wljew";
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new AuthenticationInterceptor(apiKey)).build();
        okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/")).build()).execute();

        RecordedRequest request = mockWebServer.takeRequest();
        Assert.assertTrue(request.getPath().contains(apiKey));

    }

    @Test
    public void testHttpInterceptor2() throws Exception {

        mockWebServer.enqueue(new MockResponse());
        String apiKey = "wljew";
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new AuthenticationInterceptor(apiKey)).build();
        okHttpClient.newCall(new Request.Builder().url(mockWebServer.url("/?test=hello")).build()).execute();

        RecordedRequest request = mockWebServer.takeRequest();
        Assert.assertTrue(request.getPath().contains(apiKey));

    }

}



