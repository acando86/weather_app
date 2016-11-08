package com.weatherapp.weatherapp.presentation.base;

import android.support.annotation.CallSuper;

/**
 * Basic abstract class for presenters. It handles basic binding between views and presenters
 * Created by alessandro.candolini on 08/11/2016.
 */

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    /** View attached to the presenter */
    private V mvpView;

    @CallSuper
    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @CallSuper
    @Override
    public void detachView() {
        mvpView = null;
    }


    /**
     * Access instance of the view (if attached).
     * <p>
     *     The method is public so that we can use it in the tests. In principle can be protected.
     * </p>
     * @return instance of the current view if attached, null otherwise.
     */
    public V getMvpView() {
        return mvpView;
    }

    /**
     * @return true if view is attached, false otherwise (useful for testing)
     */
    public boolean isViewAttached() {
        return mvpView != null;
    }
}
