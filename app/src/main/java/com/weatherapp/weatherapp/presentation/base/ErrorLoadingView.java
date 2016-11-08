package com.weatherapp.weatherapp.presentation.base;

/**
 * Interface for error and loading views
 * Created by alessandro.candolini on 08/11/2016.
 */

public interface ErrorLoadingView extends  MvpView {

    public void showError();
    public void hideError();
    public void showLoading();
    public void hideLoading();

}
