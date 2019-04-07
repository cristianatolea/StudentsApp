package com.example.cris.studentsapp.screen.logout.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class LogoutModel implements ILogoutModel {

    private ApiInterface mApiInterface;

    public LogoutModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}
