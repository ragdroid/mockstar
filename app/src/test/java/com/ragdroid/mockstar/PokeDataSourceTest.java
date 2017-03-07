package com.ragdroid.mockstar;

import com.ragdroid.mockstar.api.PokemonService;
import com.ragdroid.mockstar.dagger.BaseLogicTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.sql.DataSource;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by garimajain on 07/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PokeDataSourceTest extends BaseLogicTest {


    @Inject PokemonService pokemonService;


    @Override
    public void setUp() throws Exception {
        getComponent().inject(this);
        super.setUp();
    }

    @Test
    public void testOnAddedHeader() throws InterruptedException {
        PokeDataSource dataSource = new PokeDataSource(pokemonService);

        TestObserver<String> observer = new TestObserver<>();
        dataSource.getPokemonStringObservable("12").subscribe(observer);

        RecordedRequest recordedRequest = getMockWebServer().takeRequest();

        assertEquals("application/json", recordedRequest.getHeader("Content-Type"));

    }

    @Test
    public void testGetPokemonStringObservable() {
        PokeDataSource dataSource = new PokeDataSource(pokemonService);
        TestObserver observer = new TestObserver();
        dataSource.getPokemonStringObservable("12")
                .subscribe(observer);
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        observer.assertValue("Id: 12\n" +
                            "Name: butterfree");
    }

    @Test
    public void testGetPokemonAbilityStringObservable() {
        PokeDataSource dataSource = new PokeDataSource(pokemonService);
        TestObserver observer = new TestObserver();
        dataSource.getPokemonAbilityStringObservable("12")
                .subscribe(observer);
        observer.assertNoErrors();
        observer.awaitTerminalEvent();
        observer.assertComplete();
        observer.assertValue("Id: 12\n" +
                "Name: butterfreeAbility Name : tinted-lens\n" +
                " Is Hidden : true");
    }

}
