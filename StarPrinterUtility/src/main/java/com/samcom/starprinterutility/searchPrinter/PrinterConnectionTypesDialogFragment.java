package com.samcom.starprinterutility.searchPrinter;

import static com.samcom.starprinterutility.printerUtils.PrinterSettingConstant.IF_TYPE_ALL;
import static com.samcom.starprinterutility.printerUtils.PrinterSettingConstant.IF_TYPE_BLUETOOTH;
import static com.samcom.starprinterutility.printerUtils.PrinterSettingConstant.IF_TYPE_ETHERNET;
import static com.samcom.starprinterutility.printerUtils.PrinterSettingConstant.IF_TYPE_USB;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.samcom.starprinterutility.R;
import com.samcom.starprinterutility.interfaces.PrinterTypeCallBack;

public class PrinterConnectionTypesDialogFragment extends BaseDialogFragment {
    private RadioGroup radioGroup;
    private PrinterTypeCallBack printerTypeCallBack;

    public static PrinterConnectionTypesDialogFragment newInstance(PrinterTypeCallBack printerTypeCallBack) {
        PrinterConnectionTypesDialogFragment dialogFragment = new PrinterConnectionTypesDialogFragment();
        dialogFragment.setCallback(printerTypeCallBack);
        return dialogFragment;
    }

    private void setCallback(PrinterTypeCallBack printerTypeCallBack) {
        this.printerTypeCallBack = printerTypeCallBack;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStylePrinter);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connection_types_alert, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
            Log.e("values", "button" + radioButton.getText().toString());
            printerTypeCallBack.callback(setConnectionType(radioButton.getText().toString()));
            dismiss();
        });
    }

    private String setConnectionType(String str) {
        if (str.equalsIgnoreCase("LAN")) {
            return IF_TYPE_ETHERNET;
        } else if (str.equalsIgnoreCase("Bluetooth")) {
            return IF_TYPE_BLUETOOTH;
        } else if (str.equalsIgnoreCase("All")) {
            return IF_TYPE_ALL;
        } else if (str.equalsIgnoreCase("USB")) {
            return IF_TYPE_USB;
        }
        return "";
    }

    private void initView(View view) {
        radioGroup = view.findViewById(R.id.radioGroup);
    }
}
