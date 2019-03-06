package com.example.cris.studentsapp.screen.main.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.main.presenter.IMainPresenter;
import com.example.cris.studentsapp.screen.main.view.delegate.IMainViewDelegate;
import com.example.cris.studentsapp.screen.splashscreen.SplashScreenActivity;
import com.example.cris.studentsapp.utils.Constants;
import com.example.cris.studentsapp.utils.LocalSaving;

import javax.inject.Inject;

import dagger.Provides;

public class MainActivity extends BaseActivity implements
        IMainViewDelegate {

    private boolean mOpenNotifications = false;
    private Bundle mBundle;

    @Inject
    IMainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mOpenNotifications = mBundle.getBoolean(Constants.KEY_OPEN_NOTIFICATIONS_SCREEN);
        }

        if ("".equals(LocalSaving.getToken(MainActivity.this))) {
            Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onError(String errorMessage) {

    }
}
