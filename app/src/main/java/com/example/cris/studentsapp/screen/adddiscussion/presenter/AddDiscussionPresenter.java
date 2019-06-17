package com.example.cris.studentsapp.screen.adddiscussion.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.adddiscussion.model.IAddDiscussionModel;
import com.example.cris.studentsapp.screen.adddiscussion.model.entity.AddNewDiscussionResponse;
import com.example.cris.studentsapp.screen.adddiscussion.view.delegate.IAddDiscussionViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AddDiscussionPresenter implements IAddDiscussionPresenter {

    private Context mContext;
    private IAddDiscussionModel mModel;
    private IAddDiscussionViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public AddDiscussionPresenter(Context context,
                                  IAddDiscussionModel model,
                                  IAddDiscussionViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void addNewDiscussion(String forumId, String subject, String message) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.addNewDiscussion(forumId, subject, message)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<AddNewDiscussionResponse>() {
                                @Override
                                public void accept(AddNewDiscussionResponse discussionResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (discussionResponse != null) {
                                        mViewDelegate.onDiscussionAddedSuccessfully();
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
