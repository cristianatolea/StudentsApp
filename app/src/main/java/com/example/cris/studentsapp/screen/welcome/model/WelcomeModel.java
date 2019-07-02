package com.example.cris.studentsapp.screen.welcome.model;

import com.example.cris.studentsapp.apiprovider.IApiInterface;

public class WelcomeModel implements IWelcomeModel {

    private IApiInterface mIApiInterface;

    public WelcomeModel(IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
    }
}
