package com.example.cris.studentsapp.screen.forumspercourse.view.adapter.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.courses.model.entity.CourseEntity;

import java.util.List;

public class CourseSpinnerAdapter extends ArrayAdapter<CourseEntity> {

    private List<CourseEntity> mCourseEntities;
    private LayoutInflater mLayoutInflater;

    public CourseSpinnerAdapter(@NonNull Context context, int resource, List<CourseEntity> list) {
        super(context, resource, list);
        mCourseEntities = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_course_spinner, parent, false);
            holder = new ViewHolder(convertView);
            //holder.mTextViewName = convertView.findViewById(R.id.text_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindData(mCourseEntities.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_course_dropdown,
                    parent, false);
        }
        TextView textView = convertView.findViewById(R.id.text_item);
        textView.setText(mCourseEntities.get(position).getFullname());
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextViewName;

        private ViewHolder(View view) {
            mTextViewName = view.findViewById(R.id.text_item);
        }

        private void bindData(CourseEntity entity) {
            mTextViewName.setText(entity.getFullname());
        }
    }
}
