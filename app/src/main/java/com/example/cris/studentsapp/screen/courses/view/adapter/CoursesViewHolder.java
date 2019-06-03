package com.example.cris.studentsapp.screen.courses.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;

public class CoursesViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private ConstraintLayout mConstraintLayout;
    private LinearLayout mLinearDetails;
    private TextView mTextTile;
    private OnCourseClickListener mOnCourseClickListener;

    public CoursesViewHolder(@NonNull View itemView, OnCourseClickListener onCourseClickListener) {
        super(itemView);

        mOnCourseClickListener = onCourseClickListener;

        mConstraintLayout = itemView.findViewById(R.id.constraint_course);
        mLinearDetails = itemView.findViewById(R.id.linear_details);
        mTextTile = itemView.findViewById(R.id.text_course_title);

        mConstraintLayout.setOnClickListener(this);
        mLinearDetails.setOnClickListener(this);
    }

    void bindData(CourseEntity entity) {
        mTextTile.setText(entity.getFullname());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_details:
            case R.id.constraint_course:
                mOnCourseClickListener.onCourseClick(getAdapterPosition());
                break;
        }
    }

    public interface OnCourseClickListener {
        void onCourseClick(int position);
    }
}
