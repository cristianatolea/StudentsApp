package com.example.cris.studentsapp.screen.dayschedule.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;

import java.util.List;

public class DayElementAdapter extends RecyclerView.Adapter<DayElementViewHolder> {

    private Context mContext;
    private List<DayElementEntity> mDayElements;

    public DayElementAdapter(Context context, List<DayElementEntity> dayElements) {
        mContext = context;
        mDayElements = dayElements;
    }

    @NonNull
    @Override
    public DayElementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_day_element, viewGroup, false);
        return new DayElementViewHolder(view, mContext);
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
}
