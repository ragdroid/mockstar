package com.ragdroid.mockstar.base;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by garimajain on 05/03/17.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}

