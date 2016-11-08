package com.weatherapp.weatherapp.di.module;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * schedulers provided via DI to improve decoupling and make test easier
 * Created by alessandro.candolini on 08/11/2016.
 */
@Module
public class ThreadingModule {

    @Provides
    @Named("io")
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("main")
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
