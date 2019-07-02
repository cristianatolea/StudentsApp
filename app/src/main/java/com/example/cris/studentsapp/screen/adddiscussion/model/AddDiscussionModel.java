package com.example.cris.studentsapp.screen.adddiscussion.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.adddiscussion.model.entity.AddNewDiscussionResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class AddDiscussionModel implements IAddDiscussionModel {

    private IApiInterface mIApiInterface;
    private Context mContext;

    public AddDiscussionModel(Context context,
                              IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<AddNewDiscussionResponse> addNewDiscussion(String forumId, String subject, String message) {
        return mIApiInterface.addNewDiscussion(
                LocalSaving.getToken(mContext),
                "mod_forum_add_discussion",
                forumId,
                subject,
                message);
    }
}
