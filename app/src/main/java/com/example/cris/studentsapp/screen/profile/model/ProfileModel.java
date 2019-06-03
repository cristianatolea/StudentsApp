package com.example.cris.studentsapp.screen.profile.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.Observable;

public class ProfileModel implements IProfileModel {

    private ApiInterface mApiInterface;
    private Context mContext;

    public ProfileModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<List<UserProfileEntity>> getUserInformation() {
        return mApiInterface.getUserInformation(LocalSaving.getToken(mContext),
                "core_user_get_users_by_field",
                "id",
                LocalSaving.getUserId(mContext));
    }
}
