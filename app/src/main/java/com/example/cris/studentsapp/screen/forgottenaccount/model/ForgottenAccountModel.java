package com.example.cris.studentsapp.screen.forgottenaccount.model;

import com.example.cris.studentsapp.apiprovider.IApiInterface;

public class ForgottenAccountModel implements IForgottenAccountModel {

    private IApiInterface mIApiInterface;

    public ForgottenAccountModel(IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
    }
}
