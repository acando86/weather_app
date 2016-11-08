package com.weatherapp.weatherapp.presentation.home;

import com.weatherapp.weatherapp.Constants;
import com.weatherapp.weatherapp.domain.Repository;
import com.weatherapp.weatherapp.presentation.base.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.Subscriber;


/**
 * Created by alessandro.candolini on 08/11/2016.
 */

public class HomePresenterImpl extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    private static final String LOG_TAG = "HomePresenterImpl";

    @Inject
    Repository repository;

    @Inject
            @Named("io")
    Scheduler ioScheduler;

    @Inject
            @Named("main")
    Scheduler mainScheduler;

    @Override
    public void attachView(HomeContract.HomeView mvpView) {
        super.attachView(mvpView);
        getMvpView().hideError();
        getMvpView().showLoading();
        refreshData();
    }

    @Override
    public void refreshData() {
         repository.retriveHomeModel(Constants.LOCATION_ID)// ideally, location id is provided by the user (view)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Subscriber<HomeModel>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        if (isViewAttached()) {
                            getMvpView().hideLoading();
                            getMvpView().showError();
                        }
                    }

                    @Override
                    public final void onNext(HomeModel response) {
                        if (isViewAttached()) {
                            getMvpView().hideLoading();
                            getMvpView().hideError();
                            getMvpView().setTown(response.getTown());
                            getMvpView().showResults(response.getList());
                        }

                    }
                });
    }

}
