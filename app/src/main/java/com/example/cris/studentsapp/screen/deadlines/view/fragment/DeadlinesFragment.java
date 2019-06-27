package com.example.cris.studentsapp.screen.deadlines.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventEntity;
import com.example.cris.studentsapp.screen.deadlines.presenter.IDeadlinesPresenter;
import com.example.cris.studentsapp.screen.deadlines.view.adapter.DeadlinesAdapter;
import com.example.cris.studentsapp.screen.deadlines.view.delegate.IDeadlinesViewDelegate;
import com.example.cris.studentsapp.screen.main.view.activity.MainActivity;
import com.example.cris.studentsapp.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DeadlinesFragment extends BaseFragment implements IDeadlinesViewDelegate {

    private ProgressBar mProgressBar;
    private TextView mTextNoResults;
    private RecyclerView mRvDeadlines;

    private List<EventEntity> mEventsList;
    private DeadlinesAdapter mAdapter;

    @Inject
    IDeadlinesPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deadlines, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEventsList = new ArrayList<>();

        initView(view);

        mPresenter.getDeadlines();

        ((MainActivity) getActivity()).setToolbarTitle(R.string.deadlines);
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
    public void onGetEventsSuccess(List<EventEntity> list) {
        mTextNoResults.setVisibility(View.GONE);
        mEventsList.clear();
        mEventsList.addAll(list);
        mAdapter.notifyDataSetChanged();
        if (mEventsList.isEmpty() || mEventsList == null) {
            mTextNoResults.setVisibility(View.VISIBLE);
            mRvDeadlines.setVisibility(View.GONE);
        } else {
            mTextNoResults.setVisibility(View.GONE);
            mRvDeadlines.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGetNoEvents() {
        mTextNoResults.setVisibility(View.VISIBLE);
    }

    private void initView(View view) {
        LinearLayout lineaAddDiscussion = getActivity().findViewById(R.id.linear_add);
        lineaAddDiscussion.setVisibility(View.GONE);
        mRvDeadlines = view.findViewById(R.id.rv_deadlines);

        mProgressBar = getActivity().findViewById(R.id.progress_bar);
        mTextNoResults = view.findViewById(R.id.text_no_result);

        mTextNoResults.setVisibility(View.GONE);

        mRvDeadlines.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new DeadlinesAdapter(mEventsList, getContext());
        mRvDeadlines.setAdapter(mAdapter);
    }
}
