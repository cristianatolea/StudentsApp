package com.example.cris.studentsapp.screen.dayschedule.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;

import java.sql.Time;
import java.util.List;

public class DayElementAdapter extends RecyclerView.Adapter<DayElementViewHolder> implements
        DayElementViewHolder.OnDayElementUpdated {

    private Context mContext;
    private List<DayElementEntity> mDayElements;
    private OnItemDayElementUpdated mOnItemDayElementUpdated;

    public DayElementAdapter(Context context,
                             List<DayElementEntity> dayElements,
                             OnItemDayElementUpdated onItemDayElementUpdated) {
        mContext = context;
        mDayElements = dayElements;
        mOnItemDayElementUpdated = onItemDayElementUpdated;
    }

    @NonNull
    @Override
    public DayElementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_day_element, viewGroup, false);
        return new DayElementViewHolder(view, mContext, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DayElementViewHolder dayElementViewHolder, int i) {
        DayElementEntity elementEntity = mDayElements.get(i);
        dayElementViewHolder.bindData(elementEntity);
    }

    @Override
    public int getItemCount() {
        return mDayElements.size();
    }

    @Override
    public void onNameAdded(int position, String name) {
        mOnItemDayElementUpdated.onNameAdded(position, name);
    }

    @Override
    public void onRoomAdded(int position, String room) {
        mOnItemDayElementUpdated.onRoomAdded(position, room);
    }

    @Override
    public void onUpdateTime(int position, Time time) {
        mOnItemDayElementUpdated.onUpdateTime(position, time);
    }

    @Override
    public void onTypeSelected(int position, String type) {
        mOnItemDayElementUpdated.onTypeSelected(position, type);
    }

    @Override
    public void onRecurrenceSelected(int position, String recurrence) {
        mOnItemDayElementUpdated.onRecurrenceSelected(position, recurrence);
    }

    @Override
    public void onSaveClicked(int position) {
        mOnItemDayElementUpdated.onSaveClicked(position);
    }

    public interface OnItemDayElementUpdated {
        void onNameAdded(int position, String name);

        void onRoomAdded(int position, String room);

        void onUpdateTime(int position, Time time);

        void onTypeSelected(int position, String type);

        void onRecurrenceSelected(int position, String recurrence);

        void onSaveClicked(int position);
    }
}
