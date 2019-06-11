package com.example.cris.studentsapp.screen.main.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.main.model.IMainModel;
import com.example.cris.studentsapp.screen.main.model.entity.SiteInfoResponse;
import com.example.cris.studentsapp.screen.main.view.delegate.IMainViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;
import com.example.cris.studentsapp.utils.LocalSaving;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements IMainPresenter {

    private Context mContext;
    private IMainModel mModel;
    private IMainViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public MainPresenter(Context context,
                         IMainViewDelegate mainViewDelegate,
                         IMainModel mainModel) {
        mContext = context;
        mViewDelegate = mainViewDelegate;
        mModel = mainModel;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getSiteInfo() {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getSiteInfo()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<SiteInfoResponse>() {
                                @Override
                                public void accept(SiteInfoResponse siteInfoResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (siteInfoResponse != null) {
                                        LocalSaving.setUserId(mContext, siteInfoResponse.getUserId());
                                        LocalSaving.setUsername(mContext, siteInfoResponse.getUserName());
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
