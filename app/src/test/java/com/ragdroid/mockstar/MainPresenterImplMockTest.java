package com.ragdroid.mockstar;

import com.ragdroid.mockstar.api.Pokemon;
import com.ragdroid.mockstar.contracts.MainScene;
import com.ragdroid.mockstar.dagger.BaseLogicTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.mockwebserver.MockResponse;
import retrofit2.HttpException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by garimajain on 05/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplMockTest extends BaseLogicTest {

    @Mock PokeDataSource pokeDataSource;
    @Mock MainScene mainSceneMock;
    @Mock HttpException httpException;
    @Mock SocketTimeoutException socketTimeoutException;

    @Override
    public void setUp() throws Exception {
        getComponent().inject(this);
        super.setUp();
    }




    @Test
    public void testOnSceneAdded() {
        reset(mainSceneMock);
        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        when(pokeDataSource.getPokemonAbilityStringObservable(anyString()))
                .thenReturn(getMockPokemonObservable());

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();


        verify(mainSceneMock, times(1)).setApiText(anyString());
        verify(mainSceneMock, times(0)).showErrorDialog(anyString());

    }

    private Observable<String> getMockPokemonObservable() {
        return Observable.just("");
    }

    @Test
    public void testDemoResponseError503() {
        reset(mainSceneMock);
        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        when(httpException.code()).thenReturn(HttpURLConnection.HTTP_UNAVAILABLE);
        when(pokeDataSource.getPokemonAbilityStringObservable(anyString()))
                .thenReturn(Observable.<String>error(httpException));


        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(0)).setApiText(anyString());
        verify(mainSceneMock, times(1)).showErrorDialog("Fire on the Server");
    }

    @Test
    public void testDemoResponseError404() {
        reset(mainSceneMock);

        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);


        when(httpException.code()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);
        when(pokeDataSource.getPokemonAbilityStringObservable(anyString()))
                .thenReturn(Observable.<String>error(httpException));


        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(1)).showErrorDialog("Lost!");
        verify(mainSceneMock, times(0)).setApiText(anyString());
    }

    @Test
    public void testDemoResponseError401() {
        reset(mainSceneMock);

        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        when(httpException.code()).thenReturn(HttpURLConnection.HTTP_UNAUTHORIZED);
        when(pokeDataSource.getPokemonAbilityStringObservable(anyString()))
                .thenReturn(Observable.<String>error(httpException));

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(1)).showErrorDialog("You shall not pass!");
        verify(mainSceneMock, times(0)).setApiText(anyString());
    }


    @Test
    public void testDemoResponseErrorSocket() {
        reset(mainSceneMock);

        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        when(socketTimeoutException.getMessage()).thenReturn("");
        when(pokeDataSource.getPokemonAbilityStringObservable(anyString()))
                .thenReturn(Observable.<String>error(socketTimeoutException));

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(1)).showErrorDialog("");
        verify(mainSceneMock, times(0)).setApiText(anyString());
    }



}
