package com.example.cris.studentsapp.screen.coursedetails.presenter;

import android.content.Context;
import android.util.Log;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.model.ICourseDetailsModel;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsResponse;
import com.example.cris.studentsapp.screen.coursedetails.view.delegate.ICourseDetailsViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CourseDetailsPresenter implements ICourseDetailsPresenter {

    private Context mContext;
    private ICourseDetailsModel mModel;
    private ICourseDetailsViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public CourseDetailsPresenter(Context context,
                                  ICourseDetailsModel model,
                                  ICourseDetailsViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getCourseDetails(String courseId) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getCourseDetails(courseId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CourseDetailsItem>>() {
                                @Override
                                public void accept(List<CourseDetailsItem> courseDetailsResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (courseDetailsResponse != null) {
                                        mViewDelegate.onGetCourseDetailsSuccess(courseDetailsResponse);
                                        Log.d("course details", "accept success maybe");
                                    } else {
                                        mViewDelegate.onError(mContext.getString(R.string.alert_error_occured));
                                        Log.d("course details err1", "error");
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    mViewDelegate.hideProgress();
                                    mViewDelegate.onError(mContext.getString(R.string.alert_error_occured));
                                    Log.d("course details err2", throwable.getMessage());
                                }
                            })
            );
        } else {
            mViewDelegate.onNoInternetConnection();
        }
    }
}
