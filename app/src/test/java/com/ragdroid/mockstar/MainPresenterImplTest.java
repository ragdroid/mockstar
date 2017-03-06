package com.ragdroid.mockstar;

import com.ragdroid.mockstar.contracts.MainScene;
import com.ragdroid.mockstar.dagger.BaseLogicTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by garimajain on 05/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest extends BaseLogicTest {

    @Inject PokeDataSource pokeDataSource;
    @Mock MainScene mainSceneMock;

    @Override
    public void setUp() throws Exception {
        getComponent().inject(this);
        super.setUp();
    }




    @Test
    public void testOnSceneAdded() {
        reset(mainSceneMock);
        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mainSceneMock).setApiText(captor.capture());

        assertEquals("Id : 12", captor.getValue());
    }

    @Test
    public void testDemoResponseError503() {
        reset(mainSceneMock);
        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        MockResponse response = new MockResponse();
        response.setResponseCode(HttpURLConnection.HTTP_UNAVAILABLE);

        getErrorMockWebServer().enqueue(response);

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(0)).setApiText(anyString());
        verify(mainSceneMock, times(1)).showErrorDialog("Fire on the Server");
    }

    @Test
    public void testDemoResponseError404() {
        reset(mainSceneMock);

        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        MockResponse response = new MockResponse();
        response.setResponseCode(HttpURLConnection.HTTP_NOT_FOUND);

        getErrorMockWebServer().enqueue(response);

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(1)).showErrorDialog("Lost!");
        verify(mainSceneMock, times(0)).setApiText(anyString());
    }

    @Test
    public void testDemoResponseError403() {
        reset(mainSceneMock);

        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        MockResponse response = new MockResponse();
        response.setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED);

        getErrorMockWebServer().enqueue(response);

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(1)).showErrorDialog("You shall not pass!");
        verify(mainSceneMock, times(0)).setApiText(anyString());
    }


    @Test
    public void testDemoResponseErrorSocket() {
        reset(mainSceneMock);

        MainPresenterImpl presenter = new MainPresenterImpl(schedulersProvider, pokeDataSource);

        MockResponse response = new MockResponse();
        response.setBody("\"message\":\"Hello\"").throttleBody(1, 2, TimeUnit.SECONDS);

        getErrorMockWebServer().enqueue(response);

        presenter.onSceneAdded(mainSceneMock, null);

        testScheduler.triggerActions();

        verify(mainSceneMock, times(1)).showErrorDialog(anyString());
        verify(mainSceneMock, times(0)).setApiText(anyString());
    }



}
