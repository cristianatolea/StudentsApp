package com.example.cris.studentsapp.screen.schedule.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.schedule.model.entity.WeekDayEntity;

import java.util.List;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayViewHolder> implements
        WeekDayViewHolder.OnDetailsClickListener {

    private Context mContext;
    private List<WeekDayEntity> mWeekDays;
    private OnItemDetailsClickListener mOnItemDetailsClickListener;

    public WeekDayAdapter(Context context,
                          List<WeekDayEntity> weekDays,
                          OnItemDetailsClickListener onItemDetailsClickListener) {
        mContext = context;
        mWeekDays = weekDays;
        mOnItemDetailsClickListener = onItemDetailsClickListener;
    }

    @NonNull
    @Override
    public WeekDayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_week_day, viewGroup, false);
        return new WeekDayViewHolder(view, mContext, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekDayViewHolder weekDayViewHolder, int i) {
        WeekDayEntity weekDayEntity = mWeekDays.get(i);
        weekDayViewHolder.bindData(weekDayEntity);
    }

    @Override
    public int getItemCount() {
        return mWeekDays.size();
    }

    @Override
    public void onDetailsClick(int position) {
        mOnItemDetailsClickListener.onItemClick(position);
    }

    public interface OnItemDetailsClickListener {
        void onItemClick(int position);
    }
}
