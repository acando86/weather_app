package com.weatherapp.weatherapp.presentation.base;

import com.weatherapp.weatherapp.BaseApplication;
import com.weatherapp.weatherapp.di.component.ApplicationComponent;
import com.weatherapp.weatherapp.di.component.DaggerApplicationComponent;
import com.weatherapp.weatherapp.di.module.ApplicationModule;
import com.weatherapp.weatherapp.di.module.TestNetworkModule;
import com.weatherapp.weatherapp.di.module.TestThreadingModule;
import com.weatherapp.weatherapp.presentation.home.HomeContract;
import com.weatherapp.weatherapp.presentation.home.HomeModel;
import com.weatherapp.weatherapp.presentation.home.HomePresenterImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomePresenterTest {


    @Spy
    HomePresenterImpl presenter = new HomePresenterImpl();

    @Mock
    HomeContract.HomeView mockView;

    @Mock
    BaseApplication mockApplication;

    TestNetworkModule testNetworkModule;

    TestThreadingModule testThreadingModule;

    @Mock
    ApplicationModule testApplicationModule;

    ApplicationComponent testComponent;

    HomeModel mockHome;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        // inject of mock repository inside presenter
        String testBaseUrl = "http://tesewrteh.ejh.com";
        String testApiKey = "dew4tw";
        mockHome = new HomeModel();
        mockHome.setTown("London");
        testNetworkModule = new TestNetworkModule(testBaseUrl, testApiKey); // fake baseUrl and apiKey
        testThreadingModule = new TestThreadingModule();
        testComponent = DaggerApplicationComponent.builder()
                .applicationModule(testApplicationModule)
                .networkModule(testNetworkModule)
                .threadingModule(testThreadingModule)
                .build();
    }

    @Test
    public void testInitialise() {
        testComponent.inject(presenter);
        when(testNetworkModule.mockRepository.retriveHomeModel(anyInt())).thenReturn(Observable.just(mockHome));
        Assert.assertTrue(!presenter.isViewAttached());
        presenter.attachView(mockView);
        Assert.assertTrue(presenter.isViewAttached());
        verify(mockView, times(2)).hideError();
        verify(mockView, times(1)).showLoading();
    }

    @Test
    public void testSuccess() {
        testComponent.inject(presenter);
        when(testNetworkModule.mockRepository.retriveHomeModel(anyInt())).thenReturn(Observable.just(mockHome));
        // test when the list is correct, we show it
        Assert.assertTrue(!presenter.isViewAttached());
        presenter.attachView(mockView);
        Assert.assertTrue(presenter.isViewAttached());
        presenter.refreshData();
        verify(mockView, times(2)).setTown(anyString()); // attachView trigger the first one
    }
//


}