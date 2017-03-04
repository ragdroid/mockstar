package com.ragdroid.mockstar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ragdroid.mockstar.MockstarApplication;
import com.ragdroid.mockstar.R;
import com.ragdroid.mockstar.dagger.ActivityComponent;
import com.ragdroid.mockstar.dagger.ActivityModule;
import com.ragdroid.mockstar.dagger.AppComponent;
import com.ragdroid.mockstar.dagger.DaggerActivityComponent;
import com.ragdroid.mockstar.mvp.Presenter;
import com.ragdroid.mockstar.mvp.Scene;

import javax.inject.Inject;

/**
 * Created by garimajain on 05/03/17.
 */

abstract public class BaseActivity<P extends Presenter> extends AppCompatActivity implements Scene {

    @Inject protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppComponent appComponent = ((MockstarApplication) getApplication()).getAppComponent();
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule())
                .build();
        injectFrom(activityComponent);
        setupActivity(savedInstanceState);
    }

    protected abstract void setupActivity(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected abstract void injectFrom(ActivityComponent activityComponent);


    @Override
    protected void onPause() {
        presenter.onSceneRemoved();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onSceneAdded(this, getIntent().getExtras());
    }



}
