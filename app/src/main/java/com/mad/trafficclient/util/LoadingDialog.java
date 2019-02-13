package com.mad.trafficclient.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.trafficclient.BaseApplication;
import com.mad.trafficclient.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadingDialog {

    public static Dialog dialog;
    public static Date data;
    public static SimpleDateFormat format;
    private static Toast toast;

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(BaseApplication.getInstance(), msg, Toast.LENGTH_LONG);
        toast.show();

    }

    public static void showDialog(Context context) {
        dialog = new Dialog(context, R.style.dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void showDialog(Context context, String s) {
        dialog = new Dialog(context, R.style.dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.setCanceledOnTouchOutside(false);
        TextView textView = (TextView) dialog.findViewById(R.id.textView1);
        textView.setText(s);
        dialog.show();

    }

    public static Dialog createDialog(Context context, String msg) {
        Dialog dialog = new Dialog(context, R.style.dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.setCanceledOnTouchOutside(false);

        TextView textView = (TextView) dialog.findViewById(R.id.textView1);
        textView.setText(msg);
        return dialog;
    }

    public static void disDialog() {

        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    public static String getTime() {
        data = new Date(System.currentTimeMillis());
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = format.format(data);
        return string;
    }
}
