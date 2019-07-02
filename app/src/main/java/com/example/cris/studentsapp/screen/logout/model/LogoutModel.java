package com.example.cris.studentsapp.screen.logout.model;

import com.example.cris.studentsapp.apiprovider.IApiInterface;

public class LogoutModel implements ILogoutModel {

    private IApiInterface mIApiInterface;

    public LogoutModel(IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
    }
}
