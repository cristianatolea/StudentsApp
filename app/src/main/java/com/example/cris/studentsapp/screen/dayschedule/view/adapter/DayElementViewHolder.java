package com.example.cris.studentsapp.screen.dayschedule.view.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;
import com.example.cris.studentsapp.screen.dayschedule.view.adapter.spinner.ScheduleSpinnerAdapter;
import com.example.cris.studentsapp.utils.AlertUtils;
import com.example.cris.studentsapp.utils.LocalSaving;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DayElementViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    private EditText mEditName, mEditRoom, mEditTime;
    private Spinner mSpinnerType, mSpinnerRecurrence;
    private Button mButtonSave;
    private Button mButtonCancel;
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
        mButtonSave = itemView.findViewById(R.id.button_save_element);
        mButtonCancel = itemView.findViewById(R.id.button_cancel);


        mTypeAdapter = new ScheduleSpinnerAdapter(mContext,
                R.layout.item_schedule_spinner,
                LocalSaving.getTypeList(mContext));
        mRecurrenceAdapter = new ScheduleSpinnerAdapter(mContext,
                R.layout.item_schedule_spinner,
                LocalSaving.getRecurrenceList(mContext));

        mSpinnerRecurrence.setAdapter(mRecurrenceAdapter);
        mSpinnerType.setAdapter(mTypeAdapter);

        mButtonSave.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);
        mEditTime.setOnClickListener(this);

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
                            LocalSaving.getTypeList(mContext).get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerRecurrence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mOnDayElementUpdated.onRecurrenceSelected(getAdapterPosition(),
                        LocalSaving.getRecurrenceList(mContext).get(position));
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
                if (!checkEmptyFields()) {
                    mEditName.setFocusable(false);
                    mEditRoom.setFocusable(false);
                    mEditTime.setFocusable(false);
                    mSpinnerRecurrence.setFocusable(false);
                    mSpinnerType.setFocusable(false);
                    mButtonCancel.setVisibility(View.GONE);
                    mButtonSave.setVisibility(View.GONE);
                    mOnDayElementUpdated.onSaveClicked(getAdapterPosition());
                } else {
                    AlertUtils.alert(mContext, R.string.alert_title, "empty fields");
                }
                break;
            case R.id.button_cancel:
                break;
            case R.id.edit_text_time:
                getTimePicker();
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

        if (!"".equals(elementEntity.getElementRoom())) {
            mEditRoom.setText(elementEntity.getElementRoom());
            mEditRoom.setFocusable(false);
        } else {
            mEditRoom.setFocusable(true);
        }

        if (!"".equals(elementEntity.getTime())) {
            mEditTime.setText(elementEntity.getTime());
            mEditTime.setFocusable(false);
        } else {
            mEditTime.setFocusable(true);
        }

        if (getTypePosition(elementEntity.getElementType()) != -1) {
            mSpinnerType.setSelection(getTypePosition(elementEntity.getElementType()));
            mSpinnerType.setEnabled(false);
        } else {
            mSpinnerType.setSelection(0);
            mSpinnerType.setEnabled(true);
        }

        if (getRecurrencePosition(elementEntity.getRecurrence()) != -1) {
            mSpinnerRecurrence.setSelection(getRecurrencePosition(elementEntity.getRecurrence()));
            mSpinnerRecurrence.setEnabled(false);
        } else {
            mSpinnerRecurrence.setSelection(0);
            mSpinnerRecurrence.setEnabled(true);
        }

        if (elementEntity.checkIfEmptyFields()){
            mButtonSave.setVisibility(View.VISIBLE);
            mButtonCancel.setVisibility(View.VISIBLE);

        } else {
            mButtonSave.setVisibility(View.GONE);
            mButtonCancel.setVisibility(View.GONE);
        }

        mEditName.addTextChangedListener(mNameWatcher);
        mEditRoom.addTextChangedListener(mRoomWatcher);
    }

    private int getTypePosition(String type) {
        for (int i = 0; i < LocalSaving.getTypeList(mContext).size(); i++) {
            if (type.equals(LocalSaving.getTypeList(mContext).get(i)))
                return i;
        }
        return -1;
    }

    private int getRecurrencePosition(String type) {
        for (int i = 0; i < LocalSaving.getRecurrenceList(mContext).size(); i++) {
            if (type.equals(LocalSaving.getRecurrenceList(mContext).get(i)))
                return i;
        }
        return -1;
    }

    private void getTimePicker() {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                updateLabel(hourOfDay, minute);
                String time = String.valueOf(hourOfDay).concat(" : ").concat(String.valueOf(minute));
                mOnDayElementUpdated.onUpdateTime(getAdapterPosition(), time);
            }
        };
        new TimePickerDialog(mContext, time, hour, minutes, true).show();
    }

    private void updateLabel(int hour, int minute) {
        mEditTime.setText(String.valueOf(hour).concat(" : ").concat(String.valueOf(minute)));
    }

    private boolean checkEmptyFields() {
        if ("".equals(mEditRoom.getText()) || "".equals(mEditName.getText()) || "".equals(mEditTime.getText())
                || "Select type of event".equals(mSpinnerType.getSelectedItem().toString())
                || "Set recurrence".equals(mSpinnerRecurrence.getSelectedItem().toString())) {
            return true;
        }
        return false;
    }

    interface OnDayElementUpdated {
        void onNameAdded(int position, String name);

        void onRoomAdded(int position, String room);

        void onUpdateTime(int position, String time);

        void onTypeSelected(int position, String type);

        void onRecurrenceSelected(int position, String recurrence);

        void onSaveClicked(int position);
    }
}
