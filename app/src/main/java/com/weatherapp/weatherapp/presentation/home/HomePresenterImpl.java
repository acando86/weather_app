package com.weatherapp.weatherapp.presentation.home;

import android.util.Log;

import com.weatherapp.weatherapp.BuildConfig;
import com.weatherapp.weatherapp.Constants;
import com.weatherapp.weatherapp.domain.Repository;
import com.weatherapp.weatherapp.presentation.base.BasePresenter;

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
    Repository repository;

    HomeModel model;

    public HomeModel getModel() {
        return model;
    }

    @Override
    public void attachView(HomeContract.HomeView mvpView) {
        super.attachView(mvpView);
        getMvpView().hideError();
        getMvpView().showLoading();
        refreshData();
    }

    @Override
    public void refreshData() {
        repository.retriveHomeModel(Constants.LOCATION_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeModel>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        if (BuildConfig.ENABLE_LOGS) Log.e(LOG_TAG, e.getMessage());
                        getMvpView().showError();
                        getMvpView().hideLoading();
                    }

                    @Override
                    public final void onNext(HomeModel response) {
                        getMvpView().showResults(response.getList());
                        getMvpView().hideLoading();
                        getMvpView().hideError();
                        getMvpView().setTown(response.getTown());

                    }
                });
    }
}
