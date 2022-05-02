package com.stpl.starmicronicsprinterlibrary.printerUtils;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.stpl.starmicronicsprinterlibrary.R;
import com.stpl.starmicronicsprinterlibrary.model.PrinterSettings;
import com.stpl.starmicronicsprinterlibrary.model.SearchResultInfo;
import com.stpl.starmicronicsprinterlibrary.searchPrinter.BaseDialogFragment;

public class ModelConfirmDialogFragmentSample extends BaseDialogFragment {
    private static final String TAG = "ModelConfirmDialogFragm";
    private static final String BUNDLE_KEY_MODEL_INDEX = "BUNDLE_KEY_MODEL_INDEX";
    private RelativeLayout rlTop;
    private ImageView imgBack;
    private TextView txtSetting;
    private View view;
    private int mPrinterSettingIndex = 0;
    private TextView tvTitle;
    private BroadcastReceiver listener;
    private Button btnPositive;
    private boolean mDrawerOpenStatus;
    private Button btnNegative;
    private int mModelIndex;
    private String mPortSettings;
    private int mPaperSize;

   /* String mPortName = "";
    String mMacAddress = "";
    String mModelName = "";*/

    private SearchResultInfo mSearchResultInfo;

    public static ModelConfirmDialogFragmentSample newInstance(String tag, int model, SearchResultInfo searchResultInfo) {
        ModelConfirmDialogFragmentSample dialogFragment = new ModelConfirmDialogFragmentSample();
        Bundle args = new Bundle();
        args.putString(DIALOG_TAG, tag);
        args.putBoolean(CANCEL, false);
        args.putBoolean(CALLBACK, true);
        args.putString(LABEL_POSITIVE, "Yes");
    /*    args.putString(PORT_NUMBER, portNumber);
        args.putString(MAC_ADDRESS, macAddress);
        args.putString(MODEL_NAME, modelName);*/
        args.putInt(BUNDLE_KEY_MODEL_INDEX, model);
        args.putSerializable(SEARCH_RESULT_INFO, searchResultInfo);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStylePrinter);
        /*dialogCallBackBrodcast();*/
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
        View view = inflater.inflate(R.layout.fragment_confirmation_alert, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        final Bundle args = getArguments();
        mModelIndex = getArguments().getInt(BUNDLE_KEY_MODEL_INDEX);
        mSearchResultInfo = (SearchResultInfo) getArguments().getSerializable(SEARCH_RESULT_INFO);
    /*  mPortName = getArguments().getString(PORT_NUMBER);
        mModelName = getArguments().getString(MODEL_NAME);
        mMacAddress = getArguments().getString(MAC_ADDRESS);*/
        String modelTitle = ModelCapability.getModelTitle(getArguments().getInt(BUNDLE_KEY_MODEL_INDEX));
        txtSetting.setText("Confirm");
        tvTitle.setText("Is your printer " + modelTitle + " ?");
        btnPositive.setText(args.getString(LABEL_POSITIVE));
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mModelIndex = data.getIntExtra(BUNDLE_KEY_MODEL_INDEX, ModelCapability.NONE);
                mPortSettings = ModelCapability.getPortSettings(mModelIndex);
                selectPaperSizeAndCashDrawerStatusDialog();
                /*Intent intent = new Intent(DIALOG_CALLBACK);
                intent.putExtra("tag",args.getString(DIALOG_TAG));
                intent.putExtra(LABEL_POSITIVE, LABEL_POSITIVE);
                intent.putExtra(BUNDLE_KEY_MODEL_INDEX, args.getInt(BUNDLE_KEY_MODEL_INDEX));
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                dismiss();*/
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(DIALOG_CALLBACK);
                intent.putExtra(LABEL_NEGATIVE, LABEL_NEGATIVE);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);*/
                dismiss();
            }
        });
    }


   /* public void dialogCallBackBrodcast() {
        listener = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent data) {
                Log.d("msg", "brodcast");
                String tag = "";
                if (data.getExtras() != null) {
                    tag = data.getExtras().getString("tag", "");
                }
                switch (tag) {
                    case "MODEL_CONFIRM_DIALOG":{
                        boolean isPressedYes = data.hasExtra(LABEL_POSITIVE);
                        if (isPressedYes) {
                            mModelIndex = data.getIntExtra(BUNDLE_KEY_MODEL_INDEX, ModelCapability.NONE);
                            mPortSettings = ModelCapability.getPortSettings(mModelIndex);
                            selectPaperSizeAndCashDrawerStatusDialog();
                        }
                        break;
                    }
                }
            }
        };
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(listener, new IntentFilter(Const.DIALOG_CALLBACK));
    }*/

    private void selectPaperSizeAndCashDrawerStatusDialog() {
        try {
            // In the SDK sample, when setting up a backup device, the paper size (dot) is the same as the destination device.
            mPaperSize = PrinterSettingConstant.PAPER_SIZE_THREE_INCH;
            if (mPrinterSettingIndex == 0) {    // Destination device
                if (ModelCapability.canSetDrawerOpenStatus(mModelIndex)) {
                    mDrawerOpenStatus = true;
                    registerPrinter();
                    try {
                        int language = 0;
//                        ((SettingActivity) getActivity()).addFragment(StarPrinterFragment.newInstance(language));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    mDrawerOpenStatus = true;
                    registerPrinter();
                    getActivity().finish();
                }
            }

        } catch (Exception e) {
        }
    }

    private void registerPrinter() {
        PrinterSettingManager settingManager = new PrinterSettingManager(getActivity());
        settingManager.storePrinterSettings(mPrinterSettingIndex, new PrinterSettings(mModelIndex, mSearchResultInfo.getPortNumber(), mPortSettings, mSearchResultInfo.getMacAddress(), mSearchResultInfo.getModelName(), mDrawerOpenStatus, mPaperSize));
        /* SharedPrefsUtils.getSPinstance().setEloPrinterSelected(getActivity(), false);*/
        Log.e(TAG, "mMacAddress : " + mSearchResultInfo.getMacAddress());
        Log.e(TAG, "mModelName : " + mSearchResultInfo.getModelName());
        Log.e(TAG, "mDrawerOpenStatus : " + mDrawerOpenStatus);
        Log.e(TAG, "mPaperSize : " + mPaperSize);
        Log.e(TAG, "mPrinterSettingIndex : " + mPrinterSettingIndex);
        Log.e(TAG, "mPortName : " + mSearchResultInfo.getPortNumber());
        Log.e(TAG, "mPortSettings : " + mPortSettings);
        Log.e(TAG, "mModelIndex : " + mModelIndex);
        dismiss();
    }


    private void initView(View view) {
        rlTop = (RelativeLayout) view.findViewById(R.id.rlTop);
        imgBack = (ImageView) view.findViewById(R.id.imgBack);
        txtSetting = (TextView) view.findViewById(R.id.txtTitle);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        btnPositive = (Button) view.findViewById(R.id.btnPositive);
        btnNegative = (Button) view.findViewById(R.id.btnNegative);
    }
}
