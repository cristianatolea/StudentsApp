package com.example.cris.studentsapp.screen.welcome.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class WelcomeModel implements IWelcomeModel {

    private ApiInterface mApiInterface;

    public WelcomeModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}
