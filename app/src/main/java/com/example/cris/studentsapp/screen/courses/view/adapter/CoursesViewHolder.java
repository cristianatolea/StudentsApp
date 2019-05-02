package com.example.cris.studentsapp.screen.courses.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;

public class CoursesViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private ConstraintLayout mConstraintLayout;
    private OnCourseClickListener mOnCourseClickListener;

    public CoursesViewHolder(@NonNull View itemView, OnCourseClickListener onCourseClickListener) {
        super(itemView);

        mOnCourseClickListener = onCourseClickListener;

        mConstraintLayout = itemView.findViewById(R.id.constraint_content);

        mConstraintLayout.setOnClickListener(this);
    }

    void bindData(CourseEntity entity) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraint_content:
                mOnCourseClickListener.onCourseClick(getAdapterPosition());
                break;
        }
    }

    public interface OnCourseClickListener {
        void onCourseClick(int position);
    }
}
