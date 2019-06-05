package com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitemfiles;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailModule;

public class CourseItemFileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTextFileName;
    private OnFileClickListener mOnFileClickListener;

    public CourseItemFileViewHolder(@NonNull View itemView, OnFileClickListener onFileClickListener) {
        super(itemView);

        mOnFileClickListener = onFileClickListener;

        mTextFileName = itemView.findViewById(R.id.course_week_filename);

        mTextFileName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_week_filename:
                mOnFileClickListener.onFileClick(getAdapterPosition());
                break;
        }
    }

    public void bindData(CourseDetailModule module) {
        if (module != null) {
            mTextFileName.setText(module.getModuleName());
        }

    }

    public interface OnFileClickListener {
        void onFileClick(int position);
    }
}
