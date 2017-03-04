package com.ragdroid.mockstar.dagger;

import com.ragdroid.mockstar.MainPresenterImpl;
import com.ragdroid.mockstar.contracts.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garimajain on 05/03/17.
 */
@Module
@ActivityScope
public class ActivityModule {

    @ActivityScope
    @Provides
    public MainPresenter provideHomePresenter(MainPresenterImpl presenter) {
        return presenter;
    }

}
