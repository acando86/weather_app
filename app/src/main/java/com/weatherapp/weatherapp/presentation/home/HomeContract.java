package com.weatherapp.weatherapp.presentation.home;

import com.weatherapp.weatherapp.presentation.base.ErrorLoadingView;
import com.weatherapp.weatherapp.presentation.base.MvpPresenter;

import java.util.List;

/**
 * Interfaces that decsribes the binding between view and presenter for home screen
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomeContract {

    public interface HomeView extends ErrorLoadingView {
        public void showResults(List<HomeItem> list);
    }

    public interface HomePresenter extends MvpPresenter<HomeView> {
        public void refreshData();
    }
}
