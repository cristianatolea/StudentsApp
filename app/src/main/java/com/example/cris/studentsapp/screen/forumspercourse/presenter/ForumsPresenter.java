package com.example.cris.studentsapp.screen.forumspercourse.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.courses.model.ICoursesModel;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;
import com.example.cris.studentsapp.screen.forumspercourse.model.IForumsModel;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.screen.forumspercourse.view.delegate.IForumsViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ForumsPresenter implements IForumsPresenter {

    private Context mContext;
    private IForumsViewDelegate mViewDelegate;
    private IForumsModel mDiscussionsModel;
    private ICoursesModel mCoursesModel;
    private CompositeDisposable mCompositeDisposable;

    public ForumsPresenter(Context context,
                           IForumsModel model,
                           ICoursesModel coursesModel,
                           IForumsViewDelegate viewDelegate) {

        mContext = context;
        mDiscussionsModel = model;
        mCoursesModel = coursesModel;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getCoursesList() {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mCoursesModel.getUserCourses()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CourseEntity>>() {
                                @Override
                                public void accept(List<CourseEntity> list) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (list != null) {
                                        mViewDelegate.onGetCoursesListSuccess(list);
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
    public void getForumsPerCourse(String courseId) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mDiscussionsModel.getForumsPerCourse(courseId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<ForumEntity>>() {
                                @Override
                                public void accept(List<ForumEntity> list) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (list != null) {
                                        mViewDelegate.onGetForumsSuccess(list);
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
