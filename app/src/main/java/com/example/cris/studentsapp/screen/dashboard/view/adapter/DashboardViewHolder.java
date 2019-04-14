package com.example.cris.studentsapp.screen.dashboard.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dashboard.model.entity.DashboardItem;

public class DashboardViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private ConstraintLayout mItemLayout;
    private TextView mItemTitle;
    private ImageView mItemImage;
    private ImageView mItemArrow;
    private Context mContext;

    private OnDashboardClickListener mOnDashboardClickListener;

    public DashboardViewHolder(@NonNull View itemView,
                               Context context,
                               OnDashboardClickListener onDashboardClickListener) {
        super(itemView);
        mContext = context;
        mOnDashboardClickListener = onDashboardClickListener;

        mItemLayout = itemView.findViewById(R.id.item_dashboard_layout);
        mItemTitle = itemView.findViewById(R.id.text_dashboard_title);
        mItemImage = itemView.findViewById(R.id.image_dashboard_icon);
        mItemArrow = itemView.findViewById(R.id.image_dashboard_arrow);

        mItemArrow.setOnClickListener(this);
        mItemLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_dashboard_layout:
            case R.id.image_dashboard_arrow:
                mOnDashboardClickListener.onDashboardClick(getAdapterPosition());
                break;
        }
    }

    void bindData(DashboardItem item) {
        mItemTitle.setText(item.getTitle());
        mItemImage.setImageResource(item.getIconResource());
    }

    interface OnDashboardClickListener {
        void onDashboardClick(int position);
    }
}
