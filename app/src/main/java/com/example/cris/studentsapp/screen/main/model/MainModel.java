package com.example.cris.studentsapp.screen.main.model;

import android.content.Context;

import com.example.cris.studentsapp.apiprovider.IApiInterface;
import com.example.cris.studentsapp.screen.main.model.entity.SiteInfoResponse;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.Observable;

public class MainModel implements IMainModel{

    private Context mContext;
    private IApiInterface mIApiInterface;

    public MainModel(Context context, IApiInterface IApiInterface) {
        mContext = context;
        mIApiInterface = IApiInterface;
    }

    @Override
    public Observable<SiteInfoResponse> getSiteInfo() {
        return mIApiInterface.getSiteInfo(
                LocalSaving.getToken(mContext),
                "core_webservice_get_site_info");
    }
}
