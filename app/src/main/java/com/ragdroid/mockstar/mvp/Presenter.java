package com.ragdroid.mockstar.mvp;

import android.os.Bundle;

/**
 * Created by garimajain on 05/03/17.
 */

public interface Presenter<T extends Scene> {

    void onSceneAdded(T scene, Bundle data);
    void onSceneRemoved();

}
