package com.example.cris.studentsapp.screen.dayschedule.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.dayschedule.model.DayElementEntity;

import java.util.ArrayList;
import java.util.List;

public class DayElementViewHolder extends RecyclerView.ViewHolder {

    private EditText mEditName, mEditRoom, mEditTime;
    private Spinner mSpinnerType, mSpinnerRecurrence;
    private Context mContext;
    private TextWatcher mNameWatcher, mRoomWatcher;

    private ArrayAdapter<String> mTypeAdapter, mRecurrenceAdapter;

    public DayElementViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        mContext = context;

        mEditName = itemView.findViewById(R.id.edit_text_element_name);
        mEditRoom = itemView.findViewById(R.id.edit_text_room);
        mEditTime = itemView.findViewById(R.id.edit_text_time);
        mSpinnerType = itemView.findViewById(R.id.spinner_element_type);
        mSpinnerRecurrence = itemView.findViewById(R.id.spinner_recurrence);

        List<String> mTypes = new ArrayList<>();
        mTypes.add("None");
        mTypes.add("Course");
        mTypes.add("Lab");
        mTypes.add("Seminary");
        List<String> mRecurrence = new ArrayList<>();
        mRecurrence.add("Weekly");
        mRecurrence.add("Odd");
        mRecurrence.add("Even");

        mTypeAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, mTypes);
        mRecurrenceAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, mRecurrence);

        mTypeAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        mRecurrenceAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        mSpinnerRecurrence.setAdapter(mRecurrenceAdapter);
        mSpinnerType.setAdapter(mTypeAdapter);

        mNameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mEditName.addTextChangedListener(mNameWatcher);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

    }

    void bindData(DayElementEntity elementEntity) {
        if (!"".equals(elementEntity.getElementName())) {
            mEditName.setText(elementEntity.getElementName());
            mEditName.setFocusable(false);
        } else {
            mEditName.setFocusable(true);
        }
    }
}
