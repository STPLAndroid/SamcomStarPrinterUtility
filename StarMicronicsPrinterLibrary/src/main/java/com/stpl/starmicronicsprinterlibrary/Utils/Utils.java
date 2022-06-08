package com.stpl.starmicronicsprinterlibrary.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.stpl.starmicronicsprinterlibrary.R;

public class Utils {


    public static Dialog dialog;

    public static void pDialog(Context context) {
        try {

            if (dialog != null)
                if (dialog.isShowing())
                    dialog.dismiss();
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setDimAmount((float) 0.8);
            dialog.setContentView(R.layout.dialog_custome_progressbar);
            dialog.setCancelable(false);
            dialog.show();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pDialogDismiss() {
        try {
            if (dialog.isShowing())
                dialog.dismiss();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
