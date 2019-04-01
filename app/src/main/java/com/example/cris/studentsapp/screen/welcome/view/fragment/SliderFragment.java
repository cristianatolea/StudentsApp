package com.example.cris.studentsapp.screen.welcome.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cris.studentsapp.R;

import static com.example.cris.studentsapp.utils.Constants.URL_MICROSOFT;
import static com.example.cris.studentsapp.utils.Constants.URL_OUTLOOK;
import static com.example.cris.studentsapp.utils.Constants.URL_STUDENTI_PUB;


public class SliderFragment extends Fragment implements View.OnClickListener {

    private static final String PAGE_NUMBER = "3";
    private int mPageNumber;

    public static SliderFragment newInstance(int pageNumber) {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_NUMBER, pageNumber);
        SliderFragment fragment = new SliderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPageNumber = getArguments().getInt(PAGE_NUMBER);

        TextView firstTextView = view.findViewById(R.id.slider_text_title);
        TextView secondTextView = view.findViewById(R.id.slider_text_content);
        switch (mPageNumber) {
            case 0:
                firstTextView.setText(getString(R.string.conturi_acces));
                String string1 = getString(R.string.welcome_conturi_access_1);
                String string2 = getString(R.string.welcome_conturi_access_2);
                String string3 = getString(R.string.welcome_conturi_access_3);
                String textAll1 = string1.concat(" ").concat(string2).concat(string3);

                Spannable spannable = new SpannableString(textAll1);
                spannable.setSpan(
                        new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                openStudentiSite();
                            }

                            @Override
                            public void updateDrawState(@NonNull TextPaint ds) {
                                ds.setColor(getResources().getColor(R.color.blue_dark));
                            }
                        },
                        string1.length(),
                        string1.length() + string2.length() + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                secondTextView.setText(spannable, TextView.BufferType.SPANNABLE);
                secondTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            case 1:
                firstTextView.setText(getString(R.string.acces_email));
                String accessEmail1 = getString(R.string.welcome_access_email_1);
                String accessEmail2 = getString(R.string.welcome_access_email_2);
                String accessEmail3 = getString(R.string.welcome_access_email_3);
                String textAll2 = accessEmail1.concat(" ").concat(accessEmail2).concat(accessEmail3);

                Spannable spannable1 = new SpannableString(textAll2);
                spannable1.setSpan(
                        new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                openOutlookSite();
                            }

                            @Override
                            public void updateDrawState(@NonNull TextPaint ds) {
                                ds.setColor(getResources().getColor(R.color.blue_dark));
                            }
                        },
                        accessEmail1.length(),
                        accessEmail1.length() + accessEmail2.length() + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                secondTextView.setText(spannable1, TextView.BufferType.SPANNABLE);
                secondTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            case 2:
                firstTextView.setText(getString(R.string.software_gratuit));
                String softwareGr1 = getString(R.string.welcome_software_gratuit_1);
                String softwareGr2 = getString(R.string.welcome_software_gratuit_2);
                String softwareGr3 = getString(R.string.welcome_software_gratuit_3);
                String textAll3 = softwareGr1.concat(" ").concat(softwareGr2)
                        .concat(" ").concat(softwareGr3);

                Spannable spannable2 = new SpannableString(textAll3);
                spannable2.setSpan(
                        new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                openMicrosoftSite();
                            }

                            @Override
                            public void updateDrawState(@NonNull TextPaint ds) {
                                ds.setColor(getResources().getColor(R.color.blue_dark));
                            }
                        },
                        softwareGr1.length(),
                        softwareGr1.length() + softwareGr2.length() + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                secondTextView.setText(spannable2, TextView.BufferType.SPANNABLE);
                secondTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (mPageNumber) {
            case 0:
                openStudentiSite();
                break;
            case 1:
                openOutlookSite();
                break;
            case 2:
                openMicrosoftSite();
                break;
        }
    }

    private void openStudentiSite() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(URL_STUDENTI_PUB));
        startActivity(i);
    }

    private void openOutlookSite(){
        Intent outlook = new Intent(Intent.ACTION_VIEW);
        outlook.setData(Uri.parse(URL_OUTLOOK));
        startActivity(outlook);
    }

    private void openMicrosoftSite() {
        Intent microsoft = new Intent(Intent.ACTION_VIEW);
        microsoft.setData(Uri.parse(URL_MICROSOFT));
        startActivity(microsoft);
    }
}

