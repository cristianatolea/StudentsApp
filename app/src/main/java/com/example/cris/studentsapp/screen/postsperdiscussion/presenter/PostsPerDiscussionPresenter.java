package com.example.cris.studentsapp.screen.postsperdiscussion.presenter;

import android.content.Context;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.IPostsPerDiscussionModel;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostsResponse;
import com.example.cris.studentsapp.screen.postsperdiscussion.view.delegate.IPostsPerDiscussionViewDelegate;
import com.example.cris.studentsapp.utils.InternetUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostsPerDiscussionPresenter implements IPostsPerDiscussionPresenter {

    private Context mContext;
    private IPostsPerDiscussionViewDelegate mViewDelegate;
    private IPostsPerDiscussionModel mModel;
    private CompositeDisposable mCompositeDisposable;

    public PostsPerDiscussionPresenter(Context context,
                                       IPostsPerDiscussionViewDelegate viewDelegate,
                                       IPostsPerDiscussionModel model) {
        mContext = context;
        mViewDelegate = viewDelegate;
        mModel = model;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getPosts(String discussionId) {
        if (InternetUtils.hasActiveInternetConnection(mContext)) {
            mViewDelegate.showProgress();
            mCompositeDisposable.add(
                    mModel.getDiscussionsPosts(discussionId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<PostsResponse>() {
                                @Override
                                public void accept(PostsResponse postsResponse) throws Exception {
                                    mViewDelegate.hideProgress();
                                    if (postsResponse != null) {
                                        if (!postsResponse.getPosts().isEmpty())
                                            mViewDelegate.onGetPostsSuccess(postsResponse.getPosts());
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
