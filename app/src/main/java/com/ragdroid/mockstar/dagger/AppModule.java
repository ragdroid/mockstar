package com.ragdroid.mockstar.dagger;

import android.app.Application;

import com.ragdroid.mockstar.base.BaseSchedulerProvider;
import com.ragdroid.mockstar.logic.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garimajain on 05/03/17.
 */
@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider providerSchedulerProvider(SchedulerProvider provider) {
        return provider;
    }


}
