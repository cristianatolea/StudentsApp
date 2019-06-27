package com.example.cris.studentsapp.screen.deadlines.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlines.model.entity.EventEntity;

public class DeadlineViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextTitle, mTextCourse, mTextDeadline;

    public DeadlineViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextTitle = itemView.findViewById(R.id.text_title);
        mTextCourse = itemView.findViewById(R.id.text_course);
        mTextDeadline = itemView.findViewById(R.id.text_deadline);
    }

    void bindData(EventEntity eventEntity) {
        mTextTitle.setText(eventEntity.getEventName());
        mTextCourse.setText(eventEntity.getCourse().getFullname());
       // mTextDeadline.setText(eventEntity.getDateStart());
        //todo end date ???
    }
}
