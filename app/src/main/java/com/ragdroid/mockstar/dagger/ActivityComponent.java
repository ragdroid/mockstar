package com.ragdroid.mockstar.dagger;

import com.ragdroid.mockstar.MainActivity;

import dagger.Component;

/**
 * Created by garimajain on 05/03/17.
 */
@Component(dependencies = {AppComponent.class},
        modules = ActivityModule.class)
@ActivityScope
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
