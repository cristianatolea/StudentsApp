package com.example.cris.studentsapp.screen.settings.model;

import com.example.cris.studentsapp.apiprovider.IApiInterface;

public class SettingsModel implements ISettingsModel {

    private IApiInterface mIApiInterface;

    public SettingsModel(IApiInterface IApiInterface) {
        mIApiInterface = IApiInterface;
    }
}
