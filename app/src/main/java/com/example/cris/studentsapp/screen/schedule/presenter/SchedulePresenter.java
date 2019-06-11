package com.example.cris.studentsapp.screen.schedule.presenter;

import android.content.Context;

import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;
import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;
import com.example.cris.studentsapp.screen.schedule.view.delegate.IScheduleViewDelegate;
import com.example.cris.studentsapp.utils.LocalSaving;

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
                                    LocalSaving.setEventsList(mContext, weekDayEntities);
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

    @Override
    public void createTypeList() {
        mCompositeDisposable.add(
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> typeList = new ArrayList<>();
                        typeList.add("Select type of event");
                        typeList.add("Course");
                        typeList.add("Lab");
                        typeList.add("Seminary");

                        emitter.onNext(typeList);
                        emitter.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> typeList) throws Exception {
                                if (typeList != null && !typeList.isEmpty()) {
                                    LocalSaving.setTypeList(mContext, typeList);
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

    @Override
    public void createRecurrenceList() {
        mCompositeDisposable.add(
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> recurrenceList = new ArrayList<>();
                        recurrenceList.add("Set recurrence");
                        recurrenceList.add("Weekly");
                        recurrenceList.add("Odd");
                        recurrenceList.add("Even");

                        emitter.onNext(recurrenceList);
                        emitter.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> recurrenceList) throws Exception {
                                if (recurrenceList != null && !recurrenceList.isEmpty()) {
                                    LocalSaving.setRecurrenceList(mContext, recurrenceList);
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
