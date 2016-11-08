package com.weatherapp.weatherapp.di.module;


import dagger.Module;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * schedulers provided via DI to improve decoupling and make test easier
 * Created by alessandro.candolini on 08/11/2016.
 */
@Module
public class TestThreadingModule extends ThreadingModule {

    @Override
    Scheduler provideIoScheduler() {
        return  Schedulers.immediate(); // we override this in test so that everything happens on the main thread
    }

    @Override
    Scheduler provideMainThreadScheduler() {
        return  Schedulers.immediate(); // we override this in test so that everything happens on the main thread
    }
}
