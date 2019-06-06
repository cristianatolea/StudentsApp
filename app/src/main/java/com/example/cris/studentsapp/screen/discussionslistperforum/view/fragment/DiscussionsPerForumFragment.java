package com.example.cris.studentsapp.screen.discussionslistperforum.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionEntity;
import com.example.cris.studentsapp.screen.discussionslistperforum.presenter.IDiscussionsPerForumPresenter;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.adapter.DiscussionAdapter;
import com.example.cris.studentsapp.screen.discussionslistperforum.view.delegate.IDiscussionsPerForumViewDelegate;
import com.example.cris.studentsapp.screen.forumspercourse.view.adapter.SimpleDividerItemDecoration;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.cris.studentsapp.utils.Constants.COURSE_NAME;
import static com.example.cris.studentsapp.utils.Constants.FORUM_ID;
import static com.example.cris.studentsapp.utils.Constants.FORUM_NAME;

public class DiscussionsPerForumFragment extends BaseFragment implements
        IDiscussionsPerForumViewDelegate,
        DiscussionAdapter.OnItemDetailsClick {

    private ProgressBar mProgressBar;
    private CardView mCardDiscussions;

    private String mForumName = "";
    private String mCourseName = "";
    private String mForumId = "";
    private DiscussionAdapter mDiscussionAdapter;
    private List<DiscussionEntity> mDiscussionsList;

    @Inject
    IDiscussionsPerForumPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discussions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDiscussionsList = new ArrayList<>();

        mForumId = getArguments().getString(FORUM_ID);
        mForumName = getArguments().getString(FORUM_NAME);
        mCourseName = getArguments().getString(COURSE_NAME);

        initView(view);

        mPresenter.getForumsDiscussions(mForumId);

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
    public void onGetDiscussionsSuccess(List<DiscussionEntity> list) {
        mDiscussionsList.clear();
        mDiscussionsList.addAll(list);
        mDiscussionAdapter.notifyDataSetChanged();
        if (mDiscussionsList != null) {
            mCardDiscussions.setVisibility(View.VISIBLE);
        } else {
            mCardDiscussions.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemDetailsClick(int position) {

    }

    public static DiscussionsPerForumFragment newInstance(String id, String name, String courseName) {
        Bundle args = new Bundle();
        args.putString(FORUM_ID, id);
        args.putString(FORUM_NAME, name);
        args.putString(COURSE_NAME, courseName);
        DiscussionsPerForumFragment fragment = new DiscussionsPerForumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        TextView textForumName = view.findViewById(R.id.text_forum_name);
        TextView textCourseName = view.findViewById(R.id.text_course_name);
        mCardDiscussions = view.findViewById(R.id.card_forum_discussions);
        RecyclerView rvDiscussions = view.findViewById(R.id.rv_forum_discussions);

        SimpleDividerItemDecoration dividerItemDecoration =
                new SimpleDividerItemDecoration(rvDiscussions.getContext());
        rvDiscussions.addItemDecoration(dividerItemDecoration);
        rvDiscussions.setLayoutManager(new LinearLayoutManager(getContext()));
        mDiscussionAdapter = new DiscussionAdapter(getContext(), mDiscussionsList, this);
        rvDiscussions.setAdapter(mDiscussionAdapter);

        textForumName.setText(mForumName);
        textCourseName.setText(mCourseName);
        mCardDiscussions.setVisibility(View.GONE);
    }
}
