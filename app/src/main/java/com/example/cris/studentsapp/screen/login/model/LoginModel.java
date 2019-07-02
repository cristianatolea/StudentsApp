package com.example.cris.studentsapp.screen.login.model;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.login.model.entity.LoginRequestEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;

import io.reactivex.Observable;

public class LoginModel implements ILoginModel {
    private IApiInterface mIApiInterface;

    public LoginModel(IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<LoginResponseEntity> login(LoginRequestEntity requestEntity) {
        return mIApiInterface.login(requestEntity.getUsername(),
                requestEntity.getPassword());
    }
}
