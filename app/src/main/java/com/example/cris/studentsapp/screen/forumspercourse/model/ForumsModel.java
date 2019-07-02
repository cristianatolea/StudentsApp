package com.example.cris.studentsapp.screen.forumspercourse.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.Observable;

public class ForumsModel implements IForumsModel {

    private Context mContext;
    private IApiInterface mIApiInterface;

    public ForumsModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<List<ForumEntity>> getForumsPerCourse(String courseId) {
        return mIApiInterface.getForumsPerCourse(LocalSaving.getToken(mContext),
                "mod_forum_get_forums_by_courses",
                courseId);
    }
}
