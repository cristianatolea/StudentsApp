package com.example.cris.studentsapp.screen.deadlineassignment.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.deadlineassignment.model.entity.EnrolledUserEntity;

public class DeadlineEnrolledViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTextName;
    private OnStudentClickListener mOnStudentClickListener;

    public DeadlineEnrolledViewHolder(@NonNull View itemView,
                                      OnStudentClickListener onStudentClickListener) {
        super(itemView);

        mOnStudentClickListener = onStudentClickListener;

        mTextName = itemView.findViewById(R.id.text_enrolled_name);
        LinearLayout linearDetails = itemView.findViewById(R.id.linear_details);
        ConstraintLayout constraintUser = itemView.findViewById(R.id.constraint_enrolled);

        linearDetails.setOnClickListener(this);
        constraintUser.setOnClickListener(this);
    }

    void bindData(EnrolledUserEntity entity) {
        mTextName.setText(entity.getFullname());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_details:
            case R.id.constraint_enrolled:
                mOnStudentClickListener.onStudentClick(getAdapterPosition());
                break;
        }
    }

    interface OnStudentClickListener {
        void onStudentClick(int position);
    }
}
