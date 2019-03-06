package com.example.cris.studentsapp.screen.login.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.login.presenter.ILoginPresenter;
import com.example.cris.studentsapp.screen.login.view.delegate.ILoginViewDelegate;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements
        ILoginViewDelegate {


    @Inject
    ILoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
