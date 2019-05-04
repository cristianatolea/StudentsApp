package com.example.cris.studentsapp.screen.courses.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.courses.model.ICoursesModel;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.courses.view.delegate.ICoursesViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CoursesPresenter implements ICoursesPresenter {

    private Context mContext;
    private ICoursesModel mModel;
    private ICoursesViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public CoursesPresenter(Context context,
                            ICoursesModel model,
                            ICoursesViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getCourses() {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getUserCourses()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CourseEntity>>() {
                                @Override
                                public void accept(List<CourseEntity> list) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (list != null) {
                                        mViewDelegate.onGetCoursesSuccess(list);
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
