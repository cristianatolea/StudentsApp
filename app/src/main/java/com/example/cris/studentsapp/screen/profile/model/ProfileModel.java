package com.example.cris.studentsapp.screen.profile.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class ProfileModel implements IProfileModel {

    private ApiInterface mApiInterface;

    public ProfileModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}
