package com.example.cris.studentsapp.screen.login.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;

public interface ILoginViewDelegate extends IBaseViewDelegate {

    void onLoginSuccess(LoginResponseEntity entity);

    void onLoginFailed(String error);
}
