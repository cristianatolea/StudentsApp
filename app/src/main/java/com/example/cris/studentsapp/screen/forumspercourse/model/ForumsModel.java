package com.example.cris.studentsapp.screen.forumspercourse.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.Observable;

public class ForumsModel implements IForumsModel {

    private Context mContext;
    private ApiInterface mApiInterface;

    public ForumsModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<List<ForumEntity>> getForumsPerCourse(String courseId) {
        return mApiInterface.getForumsPerCourse(LocalSaving.getToken(mContext),
                "mod_forum_get_forums_by_courses",
                courseId);
    }
}
