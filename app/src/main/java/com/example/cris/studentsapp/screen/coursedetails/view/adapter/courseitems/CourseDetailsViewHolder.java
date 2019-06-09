package com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitems;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitemfiles.CourseItemFileAdapter;

public class CourseDetailsViewHolder extends RecyclerView.ViewHolder implements
        CourseItemFileAdapter.OnItemFileClickListener {

    private TextView mTextWeek;
    private TextView mTextResults;
    private RecyclerView mRvFiles;
    private CourseItemFileAdapter mFilesAdapter;
    private OnFileClickListener mOnFileClickListener;

    private Context mContext;

    public CourseDetailsViewHolder(@NonNull View itemView, Context context, OnFileClickListener onFileClickListener) {
        super(itemView);

        mContext = context;
        mOnFileClickListener = onFileClickListener;

        mTextWeek = itemView.findViewById(R.id.text_week_course);
        mTextResults = itemView.findViewById(R.id.text_no_results);
        mRvFiles = itemView.findViewById(R.id.rv_weeks_elements);

        mRvFiles.setLayoutManager(new LinearLayoutManager(mContext));

        mTextResults.setVisibility(View.GONE);
    }

    public void bindData(CourseDetailsItem item) {
        mTextWeek.setText(item.getName());

        if (item.getModules() != null && !item.getModules().isEmpty()) {
            mTextResults.setVisibility(View.GONE);
            mFilesAdapter = new CourseItemFileAdapter(mContext, item.getModules(), this);
            mRvFiles.setAdapter(mFilesAdapter);
        } else {
            mTextResults.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onItemFileClick(int position) {
        mOnFileClickListener.onFileClick(getAdapterPosition(), position);
    }

    public interface OnFileClickListener {
        void onFileClick(int coursePosition, int position);
    }
}
