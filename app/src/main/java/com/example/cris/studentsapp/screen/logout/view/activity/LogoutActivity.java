package com.example.cris.studentsapp.screen.logout.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.logout.view.delegate.ILogoutViewDelegate;

public class LogoutActivity extends BaseActivity implements
        ILogoutViewDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
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

    @Override
    public void onBackPressed() {
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackCount > 1) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
            finish();
            return;
        }
    }
}
