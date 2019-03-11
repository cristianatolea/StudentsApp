package com.example.cris.studentsapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.cris.studentsapp.R;


public class AlertUtils {

    /**
     * default standard alert for application with one button
     *
     * @param context used to create dialog alert
     * @param title   string to display as title
     * @param content string to display as content
     */
    public static void alert(Context context, String title, String content) {
        if (context != null) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.alert_dialog_standard);

            TextView txtTitle = dialog.findViewById(R.id.text_alert_title);
            TextView txtContent = dialog.findViewById(R.id.text_alert_content);
            Button btnOk = dialog.findViewById(R.id.button_ok);

            txtTitle.setText(title);
            txtContent.setText(content);

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * default standard alert for application with one button
     *
     * @param context       used to create dialog alert
     * @param titleStringId string id to display as title
     * @param content       string to display as content
     */
    public static void alert(Context context, int titleStringId, String content) {
        alert(context, context.getString(titleStringId), content);
    }

    /**
     * default standard alert for application with one button
     *
     * @param context         used to create dialog alert
     * @param titleStringId   string id to display as title
     * @param contentStringId string id to display as content
     */
    public static void alert(Context context, int titleStringId, int contentStringId) {
        alert(context, context.getString(titleStringId), context.getString(contentStringId));
    }
}
