package com.example.cris.studentsapp.screen.login.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.forgottenaccount.view.activity.ForgottenAccountActivity;
import com.example.cris.studentsapp.screen.login.presenter.ILoginPresenter;
import com.example.cris.studentsapp.screen.login.view.delegate.ILoginViewDelegate;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements
        ILoginViewDelegate,
        View.OnClickListener {

    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private ImageView mImageUsernameValidation;
    private ImageView mImagePasswordValidation;
    private TextView mTextForgottenPassword;
    private Button mButtonLogin;

    @Inject
    ILoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_forgotten_password:
                startActivity(new Intent(LoginActivity.this, ForgottenAccountActivity.class));
                break;
            case R.id.button_login:
                break;
        }
    }


    private void initView() {
        mEditTextUsername = findViewById(R.id.edit_text_username);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mImageUsernameValidation = findViewById(R.id.image_username_validation);
        mImagePasswordValidation = findViewById(R.id.image_password_validation);
        mTextForgottenPassword = findViewById(R.id.text_forgotten_password);
        mButtonLogin = findViewById(R.id.button_login);

        mTextForgottenPassword.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
    }
}
