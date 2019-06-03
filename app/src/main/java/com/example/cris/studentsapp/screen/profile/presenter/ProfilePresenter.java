package com.example.cris.studentsapp.screen.profile.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.profile.model.entity.ProfileCustomField;
import com.example.cris.studentsapp.screen.profile.model.IProfileModel;
import com.example.cris.studentsapp.screen.profile.model.entity.UserProfileEntity;
import com.example.cris.studentsapp.screen.profile.view.delegate.IProfileViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter implements IProfilePresenter {

    private Context mContext;
    private IProfileViewDelegate mViewDelegate;
    private IProfileModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public ProfilePresenter(Context context,
                            IProfileViewDelegate viewDelegate,
                            IProfileModel model) {

        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getUserInformation() {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getUserInformation()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<UserProfileEntity>>() {
                                @Override
                                public void accept(List<UserProfileEntity> userProfileEntityList) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (userProfileEntityList != null) {
                                        if (userProfileEntityList.get(0) != null)
                                            mViewDelegate.onGetUserInformationSuccess(
                                                    userProfileEntityList.get(0));
                                    } else {
                                        mViewDelegate.onError(mContext.getString(R.string.alert_error_occured));
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    mViewDelegate.hideProgress();
                                    mViewDelegate.onError(throwable.getMessage());
                                }
                            })
            );
        } else {
            mViewDelegate.onNoInternetConnection();
        }

    }

    @Override
    public void parseUserCustomFields(List<ProfileCustomField> customFields, UserProfileEntity entity) {
        String univYear = "";
        String university = "";
        String cycle = "";
        String spec = "";
        String yearOfStudy = "";
        String group = "";

        for (ProfileCustomField customField : customFields) {
            if (mContext.getString(R.string.an_universitar).equals(customField.getName())) {
                univYear = customField.getValue();
            } else if (mContext.getString(R.string.facultatea).equals(customField.getName())) {
                university = customField.getValue();
            } else if (mContext.getString(R.string.ciclu_de_studii).equals(customField.getName())) {
                cycle = customField.getValue();
            } else if (mContext.getString(R.string.specializarea).equals(customField.getName())) {
                spec = customField.getValue();
            } else if (mContext.getString(R.string.an).equals(customField.getName())) {
                yearOfStudy = customField.getValue();
            } else if (mContext.getString(R.string.grupa).equals(customField.getName())) {
                group = customField.getValue();
            }
        }

        if (!"".equals(univYear) && !"".equals(university) && !"".equals(cycle) && !"".equals(spec)
                && !"".equals(yearOfStudy) && !"".equals(group))
            mViewDelegate.onGetCustomInformation(
                    univYear, university, cycle,
                    spec, yearOfStudy, group, entity);
    }
}
