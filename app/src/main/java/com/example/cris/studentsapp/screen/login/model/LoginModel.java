package com.example.cris.studentsapp.screen.login.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class LoginModel implements ILoginModel {
    private ApiInterface mApiInterface;

    public LoginModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}
