package com.example.cris.studentsapp.screen.main.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.ApiInterface;
import com.example.cris.studentsapp.screen.main.model.entity.SiteInfoResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class MainModel implements IMainModel{

    private Context mContext;
    private ApiInterface mApiInterface;

    public MainModel(Context context, ApiInterface apiInterface) {
        mContext = context;
        mApiInterface = apiInterface;
    }

    @Override
    public Observable<SiteInfoResponse> getSiteInfo() {
        return mApiInterface.getSiteInfo(
                LocalSaving.getToken(mContext),
                "core_webservice_get_site_info");
    }
}
