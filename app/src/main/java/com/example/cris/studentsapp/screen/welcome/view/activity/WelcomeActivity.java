package com.example.cris.studentsapp.screen.welcome.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.login.view.activity.LoginActivity;
import com.example.cris.studentsapp.screen.welcome.presenter.IWelcomePresenter;
import com.example.cris.studentsapp.screen.welcome.view.adapter.SliderViewPager;
import com.example.cris.studentsapp.screen.welcome.view.delegate.IWelcomeViewDelegate;

import javax.inject.Inject;

public class WelcomeActivity extends BaseActivity implements IWelcomeViewDelegate {

    private TextView mTextAutentificare;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Inject
    IWelcomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_acitivity);
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

    private void initView() {
        mTextAutentificare = findViewById(R.id.text_autentificare);
        mViewPager = findViewById(R.id.slider_view_pager);
        mTabLayout = findViewById(R.id.tab_dots);

        mTextAutentificare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });

        mViewPager.setAdapter(new SliderViewPager(getSupportFragmentManager(), 3));
        mTabLayout.setupWithViewPager(mViewPager, true);
    }
}