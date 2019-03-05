package com.example.cris.studentsapp.base;

public interface IBaseViewDelegate extends IBaseNoInternetViewDelegate{
    void showProgress();

    void hideProgress();

    void onError(String errorMessage);
}
