package com.ragdroid.mockstar;

import android.os.Bundle;
import android.widget.TextView;

import com.ragdroid.mockstar.base.BaseActivity;
import com.ragdroid.mockstar.contracts.MainPresenter;
import com.ragdroid.mockstar.contracts.MainScene;
import com.ragdroid.mockstar.dagger.ActivityComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainScene {

    @BindView(R.id.main_text) TextView textView;

    @Override
    protected void setupActivity(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectFrom(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void setApiText(String id) {
        textView.setText(id);
    }

    @Override
    public void showErrorDialog(String string) {
        textView.setText(string);
    }
}
