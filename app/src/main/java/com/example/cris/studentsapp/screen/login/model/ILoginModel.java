package com.example.cris.studentsapp.screen.login.model;

import com.example.cris.studentsapp.screen.login.model.entity.LoginRequestEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;

import io.reactivex.Observable;

public interface ILoginModel {

    Observable<LoginResponseEntity> login(LoginRequestEntity requestEntity);
}
