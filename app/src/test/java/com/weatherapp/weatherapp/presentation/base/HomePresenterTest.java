package com.weatherapp.weatherapp.presentation.base;

import com.weatherapp.weatherapp.presentation.home.HomeContract;
import com.weatherapp.weatherapp.presentation.home.HomeModel;
import com.weatherapp.weatherapp.presentation.home.HomePresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

/**
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomePresenterTest {

    @Spy
    HomePresenterImpl presenter = new HomePresenterImpl();

    @Mock
    HomeModel mockBasket;

    @Mock
    HomeContract.HomeView mockView;

    @Before
    public void setUpTest() {
        //when(mockItem.getProductCode()).thenReturn("testProductCode");
    }

}