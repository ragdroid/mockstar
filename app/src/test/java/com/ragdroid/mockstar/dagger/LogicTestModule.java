package com.ragdroid.mockstar.dagger;


import android.support.annotation.NonNull;

import com.ragdroid.mockstar.api.LocalResponseDispatcher;


import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by garimajain on 27/02/17.
 */
@Module
public class LogicTestModule extends ApiModule {
    @Override
    protected void addOkHttpConfig(OkHttpClient.Builder builder) {
        builder.readTimeout(2, TimeUnit.SECONDS);
    }

    private final LocalResponseDispatcher dispatcher;
    private final MockWebServer mockWebServer;

    public LogicTestModule() {
        dispatcher = new LocalResponseDispatcher();
        this.mockWebServer = getMockWebServer(dispatcher);
    }

    @Override
    protected String getBaseUrl() {
        return mockWebServer.url("/").toString();
    }

    @Provides
    @Singleton
    public MockWebServer provideDefaultMockWebServer(Dispatcher dispatcher) {
       return mockWebServer;
    }

    @NonNull
    private MockWebServer getMockWebServer(Dispatcher dispatcher) {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(dispatcher);
        return mockWebServer;
    }


    @Provides
    @Singleton
    public Dispatcher getTestDispatcher() {
        return dispatcher;
    }
}
