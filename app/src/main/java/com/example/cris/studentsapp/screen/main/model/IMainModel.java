package com.example.cris.studentsapp.screen.main.model;

import com.example.cris.studentsapp.screen.main.model.entity.SiteInfoResponse;

import io.reactivex.Observable;

public interface IMainModel {

    Observable<SiteInfoResponse> getSiteInfo();
}
