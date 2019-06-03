package com.example.cris.studentsapp.screen.profile.presenter;

import com.example.cris.studentsapp.screen.profile.model.entity.ProfileCustomField;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;

import java.util.List;

public interface IProfilePresenter {

    void getUserInformation();

    void parseUserCustomFields(List<ProfileCustomField> customFields, UserProfileEntity entity);
}
