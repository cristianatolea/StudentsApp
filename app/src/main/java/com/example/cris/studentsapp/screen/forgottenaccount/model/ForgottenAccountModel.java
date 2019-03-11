package com.example.cris.studentsapp.screen.forgottenaccount.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class ForgottenAccountModel implements IForgottenAccountModel {

    private ApiInterface mApiInterface;

    public ForgottenAccountModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}
