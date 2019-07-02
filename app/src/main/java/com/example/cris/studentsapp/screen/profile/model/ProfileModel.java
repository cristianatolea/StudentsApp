package com.example.cris.studentsapp.screen.profile.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.Observable;

public class ProfileModel implements IProfileModel {

    private IApiInterface mIApiInterface;
    private Context mContext;

    public ProfileModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<List<UserProfileEntity>> getUserInformation() {
        return mIApiInterface.getUserInformation(LocalSaving.getToken(mContext),
                "core_user_get_users_by_field",
                "id",
                LocalSaving.getUserId(mContext));
    }
}
