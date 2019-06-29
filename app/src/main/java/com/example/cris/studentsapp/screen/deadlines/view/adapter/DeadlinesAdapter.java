package com.example.cris.studentsapp.screen.deadlines.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventEntity;

import java.util.List;

public class DeadlinesAdapter extends RecyclerView.Adapter<DeadlineViewHolder> implements
        DeadlineViewHolder.OnDeadlineClickListener {

    private List<EventEntity> mEvents;
    private Context mContext;
    private OnDeadlineItemClickListener mOnDeadlineItemClickListener;

    public DeadlinesAdapter(List<EventEntity> events,
                            Context context,
                            OnDeadlineItemClickListener onDeadlineItemClickListener) {
        mEvents = events;
        mContext = context;
        mOnDeadlineItemClickListener = onDeadlineItemClickListener;
    }

    @NonNull
    @Override
    public DeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_deadline, viewGroup, false);
        return new DeadlineViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DeadlineViewHolder deadlineViewHolder, int i) {
        EventEntity eventEntity = mEvents.get(i);
        deadlineViewHolder.bindData(eventEntity);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    @Override
    public void onDeadlineClick(int position) {
        mOnDeadlineItemClickListener.onDeadlineItemClick(position);
    }

    public interface OnDeadlineItemClickListener{
        void onDeadlineItemClick(int position);
    }
}
