package com.example.cris.studentsapp.apiprovider;

import com.example.cris.studentsapp.screen.login.model.entity.LoginRequestEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/login/token.php?service=moodle_mobile_app")
    Observable<LoginResponseEntity> login(@Body LoginRequestEntity requestEntity);
}
