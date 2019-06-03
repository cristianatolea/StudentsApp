package com.example.cris.studentsapp.screen.profile.view.delegate;

import com.example.cris.studentsapp.base.IBaseViewDelegate;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;

public interface IProfileViewDelegate extends IBaseViewDelegate {

    void onGetUserInformationSuccess(UserProfileEntity entity);

    void onGetCustomInformation(String univYear, String university, String cycleOfStudy,
                                String specialization, String yearOfStudy, String group,
                                UserProfileEntity entity);
}
