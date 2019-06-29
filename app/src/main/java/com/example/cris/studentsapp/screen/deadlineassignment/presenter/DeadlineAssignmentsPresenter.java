package com.example.cris.studentsapp.screen.deadlineassignment.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlineassignment.model.IDeadlineAssignmentsModel;
import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.EnrolledUserEntity;
import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.RoleEntity;
import com.example.cris.studentsapp.screen.deadlineassignment.view.delegate.IDeadlineAssignmentsViewDelegate;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventsResponse;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DeadlineAssignmentsPresenter implements IDeadlineAssignmentsPresenter {

    private Context mContext;
    private IDeadlineAssignmentsModel mModel;
    private IDeadlineAssignmentsViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public DeadlineAssignmentsPresenter(Context context,
                                        IDeadlineAssignmentsViewDelegate viewDelegate,
                                        IDeadlineAssignmentsModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getEnrolledStudents(String courseId) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getCourseEnrolledUsers(courseId)
                            .map(new Function<List<EnrolledUserEntity>, List<EnrolledUserEntity>>() {
                                @Override
                                public List<EnrolledUserEntity> apply(List<EnrolledUserEntity> list) throws Exception {
                                    List<EnrolledUserEntity> newList = new ArrayList<>();
                                    boolean ok;
                                    for (EnrolledUserEntity user : list) {
                                        ok = true;
                                        for (RoleEntity role : user.getRoles()) {
                                            if ("3".equals(role.getRoleId()) || "9".equals(role.getRoleId())) //todo assitant
                                                ok = false;
                                        }
                                        if (ok) {
                                            newList.add(user);
                                        }
                                    }
                                    return newList;
                                }
                            })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<EnrolledUserEntity>>() {
                                @Override
                                public void accept(List<EnrolledUserEntity> enrolledEntities) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (enrolledEntities != null) {
                                        if (!enrolledEntities.isEmpty())
                                            mViewDelegate.onGetEnrolledStudentsSuccess(enrolledEntities);
                                        else
                                            mViewDelegate.OnGetEnrolledFailed();
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
}
