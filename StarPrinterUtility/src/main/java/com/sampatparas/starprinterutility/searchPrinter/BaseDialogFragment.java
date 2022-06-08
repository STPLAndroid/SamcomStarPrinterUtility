package com.sampatparas.starprinterutility.searchPrinter;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import com.sampatparas.starprinterutility.R;

import java.util.ArrayList;
import java.util.List;

public class BaseDialogFragment extends DialogFragment {

    public String CompanyName = "";
    ProgressDialog m_pd = null;
    boolean checkDialog = true;
    private List<String> newListPermissionsNeeded = new ArrayList<>();
    private int PERMISSIONS_REQUEST = 123;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() != null)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            m_pd = new ProgressDialog(getActivity());
            m_pd.setCancelable(false);
            m_pd.setMessage(getResources().getString(R.string.please_wait));
            m_pd.setIndeterminate(true);
        } else {
            m_pd = new ProgressDialog(getActivity());
            m_pd.setCancelable(false);
            m_pd.setMessage(getResources().getString(R.string.please_wait));
            m_pd.setIndeterminate(true);
        }
    }



    protected void showProgress(String msg) {
        if (m_pd != null) {
            // m_pd.setCancelable(false);
            m_pd.setMessage(msg);
            checkDialog = false;
            m_pd.show();
        }

    }







    // code from CommonAlertDialogFragment
    public static final String DIALOG_TAG = "bundle_dialog_tag";
    public static final String LABEL_POSITIVE = "bundle_label_positive";
    public static final String LABEL_NEGATIVE = "bundle_label_negative";
    public static final String CANCEL = "bundle_cancel";
    protected static final String TITLE = "bundle_title";
    protected static final String MESSAGE_STRING = "bundle_message_string";
    public static final String CALLBACK = "bundle_callback";
    public static final String PORT_NUMBER = "PORT_NUMBER";
    public static final String MAC_ADDRESS = "MAC_ADDRESS";
    public static final String MODEL_NAME = "MODEL_NAME";
    public static final String SEARCH_RESULT_INFO = "SEARCH_RESULT_INFO";

    protected void setupPositiveButton(AlertDialog.Builder builder) {
        final Bundle args = getArguments();
        final boolean isCallback = args.getBoolean(CALLBACK, false);

        if (args.containsKey(LABEL_POSITIVE)) {
            builder.setPositiveButton(args.getString(LABEL_POSITIVE), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isCallback) {
                        Intent intentForPassingData = new Intent();
                        intentForPassingData.putExtra(LABEL_POSITIVE, LABEL_POSITIVE);
//                        callbackToTarget(args.getString(DIALOG_TAG), intentForPassingData);
                    }
                    dialog.dismiss();
                }
            });
        }
    }

    protected void setupNegativeButton(AlertDialog.Builder builder) {
        final Bundle args = getArguments();
        final boolean isCallback = args.getBoolean(CALLBACK, false);

        if (args.containsKey(LABEL_NEGATIVE)) {
            builder.setNegativeButton(args.getString(LABEL_NEGATIVE), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (isCallback) {
                        Intent intentForPassingData = new Intent();
                        intentForPassingData.putExtra(LABEL_NEGATIVE, LABEL_NEGATIVE);
                        //   callbackToTarget(args.getString(DIALOG_TAG), intentForPassingData);
                    }

                    dialog.dismiss();
                }
            });
        }
    }
    public interface Callback {
        void onDialogResult(String tag, Intent data);
    }
}