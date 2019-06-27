package com.example.cris.studentsapp.apiprovider;

import com.example.cris.studentsapp.screen.adddiscussion.model.entity.AddNewDiscussionResponse;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventsResponse;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.CanAddDiscussionResponse;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionsPerForumResponse;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.screen.login.model.entity.LoginResponseEntity;
import com.example.cris.studentsapp.screen.main.model.entity.SiteInfoResponse;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.NewPostResponse;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostsResponse;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

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

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<List<UserProfileEntity>> getUserInformation(@Field("wstoken") String userToken,
                                                           @Field("wsfunction") String function,
                                                           @Field("field") String idString,
                                                           @Field("values[0]") String userIdValue);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<List<CourseDetailsItem>> getCourseDetails(@Field("wstoken") String userToken,
                                                         @Field("wsfunction") String function,
                                                         @Field("courseid") String courseId);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<List<ForumEntity>> getForumsPerCourse(@Field("wstoken") String userToken,
                                                     @Field("wsfunction") String function,
                                                     @Field("courseids[0]") String courseId);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<DiscussionsPerForumResponse> getForumsDiscussions(@Field("wstoken") String userToken,
                                                                 @Field("wsfunction") String function,
                                                                 @Field("forumid") String forumId);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<PostsResponse> getDiscussionsPosts(@Field("wstoken") String userToken,
                                                  @Field("wsfunction") String function,
                                                  @Field("discussionid") String discussionId);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<NewPostResponse> addNewPost(@Field("wstoken") String userToken,
                                           @Field("wsfunction") String function,
                                           @Field("postid") String postId,
                                           @Field("subject") String subject,
                                           @Field("message") String message);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<CanAddDiscussionResponse> canAddDiscussion(@Field("wstoken") String userToken,
                                                          @Field("wsfunction") String function,
                                                          @Field("forumid") String forumId);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<AddNewDiscussionResponse> addNewDiscussion(@Field("wstoken") String userToken,
                                                          @Field("wsfunction") String function,
                                                          @Field("forumid") String forumId,
                                                          @Field("subject") String subject,
                                                          @Field("message") String message);

    @FormUrlEncoded
    @POST("webservice/rest/server.php?moodlewsrestformat=json")
    Observable<EventsResponse> getUpcomingDeadlines(@Field("wstoken") String userToken,
                                                    @Field("wsfunction") String function,
                                                    @Field("timesortfrom") String fromTime);

}
