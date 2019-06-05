package com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.coursedetails.model.entity.CourseDetailsItem;
import com.example.cris.studentsapp.screen.coursedetails.view.adapter.courseitemfiles.CourseItemFileAdapter;

public class CourseDetailsViewHolder extends RecyclerView.ViewHolder implements
        CourseItemFileAdapter.OnItemFileClickListener {

    private TextView mTextWeek;
    private RecyclerView mRvFiles;
    private CourseItemFileAdapter mFilesAdapter;

    private Context mContext;

    public CourseDetailsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        mContext = context;

        mTextWeek = itemView.findViewById(R.id.text_week_course);
        mRvFiles = itemView.findViewById(R.id.rv_weeks_elements);

        mRvFiles.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public void bindData(CourseDetailsItem item) {
        mTextWeek.setText(item.getName());

        mFilesAdapter = new CourseItemFileAdapter(mContext, item.getModules(), this);
        mRvFiles.setAdapter(mFilesAdapter);

    }

    @Override
    public void onItemFileClick(int position) {

    }
}
