package com.example.cris.studentsapp.screen.dashboard.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.base.BaseFragment;
import com.example.cris.studentsapp.screen.dashboard.model.entity.DashboardItem;
import com.example.cris.studentsapp.screen.dashboard.presenter.IDashboardPresenter;
import com.example.cris.studentsapp.screen.dashboard.view.adapter.DashboardAdapter;
import com.example.cris.studentsapp.screen.dashboard.view.delegate.IDashboardViewDelegate;

import java.util.List;

import javax.inject.Inject;

public class DashboardFragment extends BaseFragment implements
        IDashboardViewDelegate,
        DashboardAdapter.OnDashboardItemClickListener {

    private RecyclerView mRvDashboard;
    private DashboardAdapter mAdapter;

    @Inject
    IDashboardPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mPresenter.populateDashboard(view);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void updateDashboardScreen(List<DashboardItem> list) {
        mAdapter = new DashboardAdapter(getContext(), list, this);
        mRvDashboard.setAdapter(mAdapter);
    }

    private void initView(View view) {
        mRvDashboard = view.findViewById(R.id.rv_dashboard);
        mRvDashboard.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onDashboardItemClick(int position) {
        switch (position) {
            case 0:
                Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
