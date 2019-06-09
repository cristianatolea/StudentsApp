package com.example.cris.studentsapp.screen.dayschedule.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;
import com.example.cris.studentsapp.screen.dayschedule.view.adapter.spinner.ScheduleSpinnerAdapter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DayElementViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private EditText mEditName, mEditRoom, mEditTime;
    private Spinner mSpinnerType, mSpinnerRecurrence;
    private Context mContext;
    private TextWatcher mNameWatcher, mRoomWatcher;

    private ScheduleSpinnerAdapter mTypeAdapter, mRecurrenceAdapter;
    private OnDayElementUpdated mOnDayElementUpdated;

    public DayElementViewHolder(@NonNull View itemView,
                                Context context,
                                OnDayElementUpdated onDayElementUpdated) {
        super(itemView);

        mContext = context;
        mOnDayElementUpdated = onDayElementUpdated;

        mEditName = itemView.findViewById(R.id.edit_text_element_name);
        mEditRoom = itemView.findViewById(R.id.edit_text_room);
        mEditTime = itemView.findViewById(R.id.edit_text_time);
        mSpinnerType = itemView.findViewById(R.id.spinner_element_type);
        mSpinnerRecurrence = itemView.findViewById(R.id.spinner_recurrence);
        Button buttonSave = itemView.findViewById(R.id.button_save_element);
        Button buttonCancel = itemView.findViewById(R.id.button_cancel);

        final List<String> typeList = new ArrayList<>();
        typeList.add("Select type of event");
        typeList.add("Course");
        typeList.add("Lab");
        typeList.add("Seminary");
        final List<String> recurrenceList = new ArrayList<>();
        recurrenceList.add("Weekly");
        recurrenceList.add("Odd");
        recurrenceList.add("Even");

        mTypeAdapter = new ScheduleSpinnerAdapter(mContext, R.layout.item_schedule_spinner, typeList);
        mRecurrenceAdapter = new ScheduleSpinnerAdapter(mContext, R.layout.item_schedule_spinner, recurrenceList);

        mSpinnerRecurrence.setAdapter(mRecurrenceAdapter);
        mSpinnerType.setAdapter(mTypeAdapter);

        buttonSave.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        mNameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOnDayElementUpdated.onNameAdded(getAdapterPosition(),
                        mEditName.getText().toString());
            }
        };

        mRoomWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOnDayElementUpdated.onRoomAdded(getAdapterPosition(),
                        mEditRoom.getText().toString());
            }
        };

        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    mOnDayElementUpdated.onTypeSelected(getAdapterPosition(),
                            typeList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerRecurrence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mOnDayElementUpdated.onRecurrenceSelected(getAdapterPosition(),
                        recurrenceList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save_element:
                mOnDayElementUpdated.onSaveClicked(getAdapterPosition());
                break;
            case R.id.button_cancel:
                break;
        }
    }

    void bindData(DayElementEntity elementEntity) {
        if (!"".equals(elementEntity.getElementName())) {
            mEditName.setText(elementEntity.getElementName());
            mEditName.setFocusable(false);
        } else {
            mEditName.setFocusable(true);
        }
    }

    interface OnDayElementUpdated {
        void onNameAdded(int position, String name);

        void onRoomAdded(int position, String room);

        void onUpdateTime(int position, Time time);

        void onTypeSelected(int position, String type);

        void onRecurrenceSelected(int position, String recurrence);

        void onSaveClicked(int position);
    }
}
