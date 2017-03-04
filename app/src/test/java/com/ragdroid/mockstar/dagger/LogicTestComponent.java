package com.ragdroid.mockstar.dagger;

import com.ragdroid.mockstar.MainPresenterImplTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by garimajain on 27/02/17.
 */

@Singleton
@Component(modules = {LogicTestModule.class})
public interface LogicTestComponent {

    void inject(MainPresenterImplTest mainPresenterImplTest);
}