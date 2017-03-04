package com.ragdroid.mockstar.contracts;

import com.ragdroid.mockstar.mvp.Scene;

/**
 * Created by garimajain on 05/03/17.
 */

public interface MainScene extends Scene {
    void setApiText(String id);

    void showErrorDialog(String string);
}
