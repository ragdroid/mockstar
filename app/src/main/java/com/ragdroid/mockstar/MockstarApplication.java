package com.ragdroid.mockstar;

import android.app.Application;

import com.ragdroid.mockstar.dagger.ApiModule;
import com.ragdroid.mockstar.dagger.AppComponent;
import com.ragdroid.mockstar.dagger.AppModule;
import com.ragdroid.mockstar.dagger.DaggerAppComponent;

/**
 * Created by garimajain on 05/03/17.
 */

public class MockstarApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
