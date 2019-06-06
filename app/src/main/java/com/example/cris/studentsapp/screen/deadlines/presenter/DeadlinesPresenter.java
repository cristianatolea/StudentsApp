package com.example.cris.studentsapp.screen.deadlines.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlines.model.IDeadlinesModel;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventsResponse;
import com.example.cris.studentsapp.screen.deadlines.view.delegate.IDeadlinesViewDelegate;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DeadlinesPresenter implements IDeadlinesPresenter {

    private Context mContext;
    private IDeadlinesModel mModel;
    private IDeadlinesViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public DeadlinesPresenter(Context context,
                              IDeadlinesModel model,
                              IDeadlinesViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getDeadlines() {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getUpcomingDeadlines()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<EventsResponse>() {
                                @Override
                                public void accept(EventsResponse eventsResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (eventsResponse != null) {
                                        if (!eventsResponse.getEventsList().isEmpty())
                                            mViewDelegate.onGetEventsSuccess(eventsResponse.getEventsList());
                                        else
                                            mViewDelegate.onGetNoEvents();
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
