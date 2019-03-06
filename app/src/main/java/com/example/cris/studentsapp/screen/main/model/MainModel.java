package com.example.cris.studentsapp.screen.main.model;

import com.example.cris.studentsapp.apiprovider.ApiInterface;

public class MainModel implements IMainModel{
    private ApiInterface mApiInterface;

    public MainModel(ApiInterface apiInterface) {
        mApiInterface = apiInterface;
    }
}
