package com.example.cris.studentsapp.screen.settings.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class SettingsModel implements ISettingsModel {

    private ApiInterface mApiInterface;

    public SettingsModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}
