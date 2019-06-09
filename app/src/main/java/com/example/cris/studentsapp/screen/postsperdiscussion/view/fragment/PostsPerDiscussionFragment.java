package com.example.cris.studentsapp.screen.postsperdiscussion.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.screen.postsperdiscussion.model.entity.PostEntity;
import com.example.cris.studentsapp.screen.postsperdiscussion.presenter.IPostsPerDiscussionPresenter;
import com.example.cris.studentsapp.screen.postsperdiscussion.view.delegate.IPostsPerDiscussionViewDelegate;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;
import static com.example.cris.studentsapp.utils.Constants.DISCUSSION_ID;
import static com.example.cris.studentsapp.utils.Constants.DISCUSSION_NAME;

public class PostsPerDiscussionFragment extends BaseFragment implements IPostsPerDiscussionViewDelegate {

    private ProgressBar mProgressBar;

    private String mDiscussionId = "";
    private String mDiscussionName = "";
    private String mCourseName = "";
    private List<PostEntity> mPostsList;

    @Inject
    IPostsPerDiscussionPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts_discussions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPostsList = new ArrayList<>();

        mDiscussionId = getArguments().getString(DISCUSSION_ID);
        mDiscussionName = getArguments().getString(DISCUSSION_NAME);
        mCourseName = getArguments().getString(COURSE_NAME);

        initView(view);

        mPresenter.getPosts(mDiscussionId);

        ((MainActivity) getActivity()).setToolbarTitle(R.string.discussions);
        ((MainActivity) getActivity()).changeFocusOnMenu(0, false, false);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String errorMessage) {
        AlertUtils.alert(getContext(), R.string.alert_title, errorMessage);
    }

    @Override
    public void onGetPostsSuccess(List<PostEntity> list) {
        mPostsList.clear();
        mPostsList.addAll(list);
        //adapter

    }

    public static PostsPerDiscussionFragment newInstance(String discussionId,
                                                         String discussionName,
                                                         String courseName) {
        Bundle args = new Bundle();
        args.putString(DISCUSSION_ID, discussionId);
        args.putString(DISCUSSION_NAME, discussionName);
        args.putString(COURSE_NAME, courseName);
        PostsPerDiscussionFragment fragment = new PostsPerDiscussionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
    }
}
