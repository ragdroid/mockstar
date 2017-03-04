package com.ragdroid.mockstar.dagger;

import com.ragdroid.mockstar.api.PokemonService;
import com.ragdroid.mockstar.base.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by garimajain on 05/03/17.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    BaseSchedulerProvider getSchedulerProvider();

    PokemonService providePokemonService();

}
