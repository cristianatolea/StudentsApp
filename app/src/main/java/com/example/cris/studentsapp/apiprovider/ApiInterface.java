package com.example.cris.studentsapp.apiprovider;

import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionsPerForumResponse;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;
import com.example.cris.studentsapp.screen.main.model.entity.SiteInfoResponse;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<List<UserProfileEntity>> getUserInformation(@Query("wstoken") String userToken,
                                                           @Query("wsfunction") String function,
                                                           @Query("field") String idString,
                                                           @Query("values[0]") String userIdValue);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<List<CourseDetailsItem>> getCourseDetails(@Field("wstoken") String userToken,
                                                         @Field("wsfunction") String function,
                                                         @Field("courseid") String courseId);

    @GET("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<List<ForumEntity>> getForumsPerCourse(@Query("wstoken") String userToken,
                                                     @Query("wsfunction") String function,
                                                     @Query("courseids[0]") String courseId);

    @GET("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<DiscussionsPerForumResponse> getForumsDiscussions(@Query("wstoken") String userToken,
                                                                 @Query("wsfunction") String function,
                                                                 @Query("forumid") String forumId);
}
