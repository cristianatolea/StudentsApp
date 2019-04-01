package com.example.cris.studentsapp.screen.login.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseActivity;
import com.example.cris.studentsapp.screen.forgottenaccount.view.activity.ForgottenAccountActivity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;
import com.example.cris.studentsapp.screen.login.presenter.ILoginPresenter;
import com.example.cris.studentsapp.screen.login.view.delegate.ILoginViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

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
    private String mUsername;
    private String mPassword;

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
        AlertUtils.alert(LoginActivity.this, getString(R.string.alert_title), errorMessage);
        mButtonLogin.setEnabled(true);
        mEditTextUsername.setEnabled(true);
        mEditTextPassword.setEnabled(true);
    }

    @Override
    public void onLoginSuccess(LoginResponseEntity entity) {
//        new Thread() {
//            public void run() {
//                try {
//                    FirebaseInstanceId.getInstance().deleteInstanceId();
//                } catch (IOException v) {
//                    System.out.println(v);
//                }
//            }
//        }.start();

        mButtonLogin.setEnabled(true);
        mEditTextPassword.setEnabled(true);
        mEditTextUsername.setEnabled(true);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFailed(String error) {
        mButtonLogin.setEnabled(true);
        mEditTextUsername.setEnabled(true);
        mEditTextPassword.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_forgotten_password:
                startActivity(new Intent(LoginActivity.this, ForgottenAccountActivity.class));
                break;
            case R.id.button_login:
                mUsername = mEditTextUsername.getText().toString();
                mPassword = mEditTextPassword.getText().toString();
                if (!("".equals(mPassword) && "".equals(mUsername))) {
                    mButtonLogin.setEnabled(false);
                    mEditTextUsername.setEnabled(false);
                    mEditTextPassword.setEnabled(false);
                    mPresenter.login(mUsername, mPassword);
                } else {
                    Toast.makeText(this, "Log err", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private void initView() {
        mUsername = "";
        mPassword = "";
        mEditTextUsername = findViewById(R.id.edit_text_username);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mImageUsernameValidation = findViewById(R.id.image_username_validation);
        mImagePasswordValidation = findViewById(R.id.image_password_validation);
        mTextForgottenPassword = findViewById(R.id.text_forgotten_password);
        mButtonLogin = findViewById(R.id.button_login);

        mTextForgottenPassword.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);

//        mEditTextUsername.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mUsername = s.toString();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        mEditTextPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mPassword = s.toString();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }
}
