package com.weatherapp.weatherapp.presentation.base;

/**
 * Interface for all presenters
 * Created by alessandro.candolini on 08/11/2016.
 */

public interface MvpPresenter<V extends MvpView> {
    public void attachView(V mvpView);
    public void detachView();

}
