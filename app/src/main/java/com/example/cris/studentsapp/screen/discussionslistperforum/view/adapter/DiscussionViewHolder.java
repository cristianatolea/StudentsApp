package com.example.cris.studentsapp.screen.discussionslistperforum.view.adapter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cris.studentsapp.R;
import com.example.cris.studentsapp.screen.discussionslistperforum.model.entity.DiscussionEntity;

public class DiscussionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTextDiscussionName;
    private TextView mTextDiscussionUserName;
    private OnDetailsClickListener mOnDetailsClickListener;

    public DiscussionViewHolder(@NonNull View itemView, OnDetailsClickListener onDetailsClickListener) {
        super(itemView);

        mOnDetailsClickListener = onDetailsClickListener;

        LinearLayout linearDetails = itemView.findViewById(R.id.linear_details);
        ConstraintLayout constraintLayout = itemView.findViewById(R.id.constraint_discussion);
        mTextDiscussionName = itemView.findViewById(R.id.text_discussion_title);
        mTextDiscussionUserName = itemView.findViewById(R.id.text_discussion_username);

        linearDetails.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);
    }

    void bindData(DiscussionEntity entity) {
        mTextDiscussionName.setText(entity.getName());
        mTextDiscussionUserName.setText(entity.getUserFullname());

//        if (Build.VERSION.SDK_INT >= 24) {
//            mTextWeek.setText(Html.fromHtml("<p>Salutare,</p><p>Vă mulțumesc pentru particparea la cursul de Proiectarea Rețelelor 2018-2019.</p><p>Notele vor fi trecute în catalog luni și vor fi actualizate pe studenti.pub.ro în cursul săptămânii următoare. Pentru orice neclarități, vă rog să mă contactați pe email: razvan.rughinis@cs.pub.ro.</p><p>În urma centralizării opiniilor întregii echipe didactice, au fost acordate cele 14 distincții (pinuri) pentru PR2019, afișate pe siteul systems.cs.pub.ro [<a href=\"https://systems.cs.pub.ro/bachelor/pr_hitlist\">1</a>].</p><p>[1] <a href=\"https://systems.cs.pub.ro/bachelor/pr_hitlist\">https://systems.cs.pub.ro/bachelor/pr_hitlist</a></p><p>Baftă,</p><p>Răzvan Rughiniș</p>", Html.FROM_HTML_MODE_LEGACY));
//        } else {
//            mTextWeek.setText(Html.fromHtml("<p>Salutare,</p><p>Vă mulțumesc pentru particparea la cursul de Proiectarea Rețelelor 2018-2019.</p><p>Notele vor fi trecute în catalog luni și vor fi actualizate pe studenti.pub.ro în cursul săptămânii următoare. Pentru orice neclarități, vă rog să mă contactați pe email: razvan.rughinis@cs.pub.ro.</p><p>În urma centralizării opiniilor întregii echipe didactice, au fost acordate cele 14 distincții (pinuri) pentru PR2019, afișate pe siteul systems.cs.pub.ro [<a href=\"https://systems.cs.pub.ro/bachelor/pr_hitlist\">1</a>].</p><p>[1] <a href=\"https://systems.cs.pub.ro/bachelor/pr_hitlist\">https://systems.cs.pub.ro/bachelor/pr_hitlist</a></p><p>Baftă,</p><p>Răzvan Rughiniș</p>")); // or for older api
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraint_discussion:
            case R.id.linear_details:
                mOnDetailsClickListener.onDetailsClick(getAdapterPosition());
                break;
        }
    }

    interface OnDetailsClickListener {
        void onDetailsClick(int position);
    }
}
