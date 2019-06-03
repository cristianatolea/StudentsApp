package com.example.cris.studentsapp.screen.logout.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.logout.view.delegate.ILogoutViewDelegate;
import com.example.cris.studentsapp.screen.welcome.view.activity.WelcomeActivity;
import com.example.cris.studentsapp.utils.LocalSaving;

public class LogoutActivity extends BaseActivity implements
        ILogoutViewDelegate,
        View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        initView();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_logout:
                LocalSaving.setToken(this, "");
                LocalSaving.setLsFirstOpen(this, false);
                LocalSaving.setLsIsLoggedIn(this, false);
                LocalSaving.setPrivateToken(this, "");
                Intent loginIntent = new Intent(this, WelcomeActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(loginIntent);
                finish();
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }

    private void initView() {
        Button buttonLogout = findViewById(R.id.button_logout);
        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonLogout.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }
}
