package com.example.cris.studentsapp.screen.schedule.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;
import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;
import com.example.cris.studentsapp.screen.schedule.view.delegate.IScheduleViewDelegate;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SchedulePresenter implements ISchedulePresenter {

    private Context mContext;
    private IScheduleViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public SchedulePresenter(Context context, IScheduleViewDelegate viewDelegate) {

        mContext = context;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void prepareDays() {
        mCompositeDisposable.add(
                Observable.create(new ObservableOnSubscribe<List<WeekDayEntity>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<WeekDayEntity>> emitter) throws Exception {
                        List<WeekDayEntity> list = new ArrayList<>();
                        WeekDayEntity entity = new WeekDayEntity("Monday", new ArrayList<DayElementEntity>());
                        list.add(entity);
                        entity = new WeekDayEntity("Tuesday", new ArrayList<DayElementEntity>());
                        list.add(entity);
                        entity = new WeekDayEntity("Wednesday", new ArrayList<DayElementEntity>());
                        list.add(entity);
                        entity = new WeekDayEntity("Thursday", new ArrayList<DayElementEntity>());
                        list.add(entity);
                        entity = new WeekDayEntity("Friday", new ArrayList<DayElementEntity>());
                        list.add(entity);

                        emitter.onNext(list);
                        emitter.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<WeekDayEntity>>() {
                            @Override
                            public void accept(List<WeekDayEntity> weekDayEntities) throws Exception {
                                if (weekDayEntities != null && !weekDayEntities.isEmpty()) {
                                    mViewDelegate.onGetNamedDaysSuccess(weekDayEntities);
                                } else {
                                    mViewDelegate.onGetNamedDaysFailed();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mViewDelegate.onError(throwable.getMessage());
                            }
                        })
        );
    }
}
