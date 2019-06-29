package com.example.cris.studentsapp.screen.deadlines.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventEntity;

public class DeadlineViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextTitle, mTextCourse, mTextDeadline;
    private OnDeadlineClickListener mOnDeadlineClickListener;

    public DeadlineViewHolder(@NonNull View itemView, OnDeadlineClickListener onDeadlineClickListener) {
        super(itemView);

        mOnDeadlineClickListener = onDeadlineClickListener;

        mTextTitle = itemView.findViewById(R.id.text_title);
        mTextCourse = itemView.findViewById(R.id.text_course);
        mTextDeadline = itemView.findViewById(R.id.text_deadline);

        ConstraintLayout constraintDeadline = itemView.findViewById(R.id.constraint_deadline);
        constraintDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDeadlineClickListener.onDeadlineClick(getAdapterPosition());
            }
        });
    }

    void bindData(EventEntity eventEntity) {
        mTextTitle.setText(eventEntity.getEventName());
        mTextCourse.setText(eventEntity.getCourse().getFullname());
        // mTextDeadline.setText(eventEntity.getDateStart());
        //todo end date ???
    }

    interface OnDeadlineClickListener {
        void onDeadlineClick(int position);
    }
}
