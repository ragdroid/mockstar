package com.ragdroid.mockstar.base;

import android.os.Bundle;

import com.ragdroid.mockstar.mvp.Presenter;
import com.ragdroid.mockstar.mvp.Scene;

/**
 * Created by garimajain on 05/03/17.
 */

public class BasePresenter<S extends Scene> implements Presenter<S> {

    protected BaseSchedulerProvider provider;

    public BasePresenter(BaseSchedulerProvider provider) {
        this.provider = provider;
    }

    protected S getScene() {
        return scene;
    }

    private S scene;

    @Override
    public void onSceneAdded(S scene, Bundle data) {
        this.scene = scene;
    }

    @Override
    public void onSceneRemoved() {
        this.scene = null;
    }
}
