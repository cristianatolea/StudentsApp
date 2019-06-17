package com.example.cris.studentsapp.screen.discussionslistperforum.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.IDiscussionsPerForumModel;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.CanAddDiscussionResponse;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionsPerForumResponse;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.delegate.IDiscussionsPerForumViewDelegate;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DiscussionsPerForumPresenter implements IDiscussionsPerForumPresenter {

    private Context mContext;
    private IDiscussionsPerForumModel mModel;
    private IDiscussionsPerForumViewDelegate mViewDelegate;
    private CompositeDisposable mCompositeDisposable;

    public DiscussionsPerForumPresenter(Context context,
                                        IDiscussionsPerForumModel model,
                                        IDiscussionsPerForumViewDelegate viewDelegate) {
        mContext = context;
        mModel = model;
        mViewDelegate = viewDelegate;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getForumsDiscussions(String forumId) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getForumsDiscussions(forumId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<DiscussionsPerForumResponse>() {
                                @Override
                                public void accept(DiscussionsPerForumResponse discussionsPerForumResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (discussionsPerForumResponse != null) {
                                        mViewDelegate.onGetDiscussionsSuccess(
                                                discussionsPerForumResponse.getDiscussionsList());
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
    public void checkPermissionToAddDiscussion(String forumId) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.canAddDiscussion(forumId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<CanAddDiscussionResponse>() {
                                @Override
                                public void accept(CanAddDiscussionResponse discussionsPerForumResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (discussionsPerForumResponse != null) {
                                        if (discussionsPerForumResponse.getStatus()){
                                            mViewDelegate.onGetPermissionGrantedToAddDiscussion();
                                        } else
                                            mViewDelegate.onGetPermissionDeniedToAddDiscussion();
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
