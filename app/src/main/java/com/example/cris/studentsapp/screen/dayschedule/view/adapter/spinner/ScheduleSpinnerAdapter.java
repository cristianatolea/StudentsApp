package com.example.cris.studentsapp.screen.dayschedule.view.adapter.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cris.studentsapp.R;

import java.util.List;

public class ScheduleSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> mStringList;
    private LayoutInflater mLayoutInflater;

    public ScheduleSpinnerAdapter(@NonNull Context context, int resource, List<String> list) {
        super(context, resource, list);
        mStringList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_schedule_spinner, parent, false);
            holder = new ViewHolder(convertView);
            //holder.mTextViewName = convertView.findViewById(R.id.text_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindData(mStringList.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_schedule_dropdown,
                    parent, false);
        }
        TextView textView = convertView.findViewById(R.id.text_item);
        textView.setText(mStringList.get(position));
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextViewName;

        private ViewHolder(View view) {
            mTextViewName = view.findViewById(R.id.text_item);
        }

        private void bindData(String entity) {
            mTextViewName.setText(entity);
        }
    }
}
