package com.example.cris.studentsapp.screen.login.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.login.model.entity.LoginRequestEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;

import io.reactivex.Observable;

public class LoginModel implements ILoginModel {
    private ApiInterface mApiInterface;

    public LoginModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<LoginResponseEntity> login(LoginRequestEntity requestEntity) {
        return mApiInterface.login(requestEntity.getUsername(),
                requestEntity.getPassword());
    }
}
