package com.example.cris.studentsapp.screen.deadlines.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlines.model.IDeadlinesModel;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventEntity;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventsResponse;
import com.example.cris.studentsapp.screen.deadlines.view.delegate.IDeadlinesViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                    mModel.getUpcomingDeadlines(getTodayTimeStamp()/*"1561684020"*/)
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

    private String getTodayTimeStamp() {
//        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        Date currentDate = Calendar.getInstance().getTime();
//        String currentString = new SimpleDateFormat("dd-MM-yyyy").format(currentDate);
//        //Date date = (Date)formatter.parse(currentString);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime()/1000);
    }
}
