package com.example.cris.studentsapp.screen.dashboard.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dashboard.model.entity.DashboardItem;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardViewHolder> implements
        DashboardViewHolder.OnDashboardClickListener {

    private Context mContext;
    private List<DashboardItem> mDashboardItems;
    private OnDashboardItemClickListener mOnDashboardItemClickListener;

    public DashboardAdapter(Context context,
                            List<DashboardItem> dashboardItems,
                            OnDashboardItemClickListener onDashboardItemClickListener) {
        mContext = context;
        mDashboardItems = dashboardItems;
        mOnDashboardItemClickListener = onDashboardItemClickListener;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dashboard,
                viewGroup, false);
        return new DashboardViewHolder(view, mContext, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder dashboardViewHolder, int i) {
        DashboardItem item = mDashboardItems.get(i);
        dashboardViewHolder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return mDashboardItems.size();
    }

    @Override
    public void onDashboardClick(int position) {
        mOnDashboardItemClickListener.onDashboardItemClick(position);
    }

    public List<DashboardItem> getAdapterList() {
        return mDashboardItems;
    }

    public interface OnDashboardItemClickListener {
        void onDashboardItemClick(int position);
    }
}
