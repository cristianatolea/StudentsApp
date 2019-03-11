package com.example.cris.studentsapp.screen.forgottenaccount.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.forgottenaccount.presenter.IForgottenAccountPresenter;
import com.example.cris.studentsapp.screen.forgottenaccount.view.delegate.IForgottenAccountViewDelegate;

import javax.inject.Inject;

public class ForgottenAccountActivity extends BaseActivity implements IForgottenAccountViewDelegate {

    @Inject
    IForgottenAccountPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_account);
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
