package com.example.cris.studentsapp.screen.adddiscussion.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.adddiscussion.model.entity.AddNewDiscussionResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class AddDiscussionModel implements IAddDiscussionModel {

    private ApiInterface mApiInterface;
    private Context mContext;

    public AddDiscussionModel(Context context,
                              ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<AddNewDiscussionResponse> addNewDiscussion(String forumId, String subject, String message) {
        return mApiInterface.addNewDiscussion(
                LocalSaving.getToken(mContext),
                "mod_forum_add_discussion",
                forumId,
                subject,
                message);
    }
}
