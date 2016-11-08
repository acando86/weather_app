package com.weatherapp.weatherapp.di.module;

import android.content.Context;

import com.weatherapp.weatherapp.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alessandro.candolini on 08/11/2016.
 */
@Module
public class ApplicationModule {
    /** Reference to the application class */
    BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        if ( application != null ) {
            return application.getApplicationContext();
        }
        return null;
    }
}
