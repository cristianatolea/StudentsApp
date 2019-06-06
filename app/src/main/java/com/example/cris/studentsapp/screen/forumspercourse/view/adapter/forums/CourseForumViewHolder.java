package com.example.cris.studentsapp.screen.forumspercourse.view.adapter.forums;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.forumspercourse.model.entity.ForumEntity;

public class CourseForumViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private TextView mTextForumName;
    private OnDetailsClickListener mOnDetailsClickListener;

    CourseForumViewHolder(@NonNull View itemView, OnDetailsClickListener onDetailsClickListener) {
        super(itemView);

        mOnDetailsClickListener = onDetailsClickListener;

        LinearLayout linearDetails = itemView.findViewById(R.id.linear_details);
        ConstraintLayout constraintLayout = itemView.findViewById(R.id.constraint_forum);
        mTextForumName = itemView.findViewById(R.id.text_forum_title);

        linearDetails.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraint_forum:
            case R.id.linear_details:
                mOnDetailsClickListener.onDetailsClick(getAdapterPosition());
                break;
        }
    }

    void bindData(ForumEntity entity) {
        mTextForumName.setText(entity.getForumName());
    }

    interface OnDetailsClickListener {
        void onDetailsClick(int position);
    }

}
