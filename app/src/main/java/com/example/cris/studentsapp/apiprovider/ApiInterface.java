package com.example.cris.studentsapp.apiprovider;

import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.courses.model.entity.CoursesResponse;
import com.example.cris.studentsapp.screen.login.model.entity.LoginRequestEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;
import com.example.cris.studentsapp.screen.main.model.entity.SiteInfoResponse;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    //form data calls -> multipart

    @FormUrlEncoded
    @POST("login/token.php?service=moodle_mobile_app")
    Observable<LoginResponseEntity> login(@Field("username") String username,
                                          @Field("password") String password);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<SiteInfoResponse> getSiteInfo(@Field("wstoken") String userToke,
                                             @Field("wsfunction") String function);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<List<CourseEntity>> getUserCourses(@Field("wstoken") String userToken,
                                                  @Field("wsfunction") String function,
                                                  @Field("userid") String userId);
}
