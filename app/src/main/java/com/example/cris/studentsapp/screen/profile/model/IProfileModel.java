package com.example.cris.studentsapp.screen.profile.model;

import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;

import java.util.List;

import io.reactivex.Observable;

public interface IProfileModel {

    Observable<List<UserProfileEntity>> getUserInformation();
}
