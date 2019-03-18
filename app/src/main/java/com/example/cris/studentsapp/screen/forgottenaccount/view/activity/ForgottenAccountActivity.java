package com.example.cris.studentsapp.screen.forgottenaccount.view.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.forgottenaccount.presenter.IForgottenAccountPresenter;
import com.example.cris.studentsapp.screen.forgottenaccount.view.delegate.IForgottenAccountViewDelegate;

import javax.inject.Inject;

public class ForgottenAccountActivity extends BaseActivity implements
        IForgottenAccountViewDelegate {

    @Inject
    IForgottenAccountPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_account);
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
        finish();
    }

    private void initView() {
        final TextView textTitle = findViewById(R.id.text_screen_title);
        Toolbar toolbar = findViewById(R.id.toolbar);
        LinearLayout linearBack = findViewById(R.id.linear_back);
        WebView view = findViewById(R.id.web_view_content);

        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textTitle.setText(getString(R.string.resetare_parola));

        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl("file:///android_asset/forgotten_account.html");

        setSupportActionBar(toolbar);
    }
}
