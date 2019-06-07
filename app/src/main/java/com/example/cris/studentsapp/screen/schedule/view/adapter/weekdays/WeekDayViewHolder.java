package com.example.cris.studentsapp.screen.schedule.view.adapter.weekdays;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;

public class WeekDayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context mContext;
    private TextView mTextName;
    private TextView mTextNumberOfElements;
    private OnDetailsClickListener mOnDetailsClickListener;

    WeekDayViewHolder(@NonNull View itemView,
                      Context context,
                      OnDetailsClickListener onDetailsClickListener) {
        super(itemView);

        mContext = context;
        mOnDetailsClickListener = onDetailsClickListener;

        mTextName = itemView.findViewById(R.id.text_weekday_name);
        mTextNumberOfElements = itemView.findViewById(R.id.text_number_elements);
        LinearLayout linearDetails = itemView.findViewById(R.id.linear_details);
        ConstraintLayout constraintDay = itemView.findViewById(R.id.constraint_day);

        linearDetails.setOnClickListener(this);
        constraintDay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraint_day:
            case R.id.linear_details:
                mOnDetailsClickListener.onDetailsClick(getAdapterPosition());
                break;
        }
    }

    void bindData(WeekDayEntity entity) {
        mTextName.setText(entity.getWeekDayName());
        mTextNumberOfElements.setText(entity.getNumberOfEvents());
    }

    public interface OnDetailsClickListener {
        void onDetailsClick(int position);
    }
}
