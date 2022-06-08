package com.samcom.starprinterutility.model;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.samcom.starprinterutility.Utils.Const;
import com.samcom.starprinterutility.printerUtils.ModelCapability;
import com.samcom.starprinterutility.printerUtils.ModelConfirmDialogFragmentSample;
import com.samcom.starprinterutility.printerUtils.ModelSelectDialogFragment;
import com.samcom.starprinterutility.searchPrinter.BaseDialogFragment;

import java.io.Serializable;

/**
 * Created by kajol on 09-Oct-18.
 */

public class SearchResultInfo implements Serializable {

    private String modelName;
    private String portNumber;
    private String macAddress;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void confirmPrinter(AppCompatActivity activity) {
        SearchResultInfo info = this;

        int model = ModelCapability.getModel(modelName);
        if (model == ModelCapability.NONE) {
            ModelSelectDialogFragment dialog = ModelSelectDialogFragment.newInstance(Const.MODEL_SELECT_DIALOG_0);

            dialog.setmCallbackTarget(new BaseDialogFragment.Callback() {
                @Override
                public void onDialogResult(String tag, Intent data) {
                    int m1 = data.getIntExtra(Const.BUNDLE_KEY_MODEL_INDEX, 0);
                    ModelConfirmDialogFragmentSample confirmDialogFragmentSample =
                            ModelConfirmDialogFragmentSample.newInstance(Const.MODEL_CONFIRM_DIALOG, m1, info);
                    confirmDialogFragmentSample.show(activity.getSupportFragmentManager(),  ModelConfirmDialogFragmentSample.class.getSimpleName());

                }
            });


            dialog.show(activity.getSupportFragmentManager(),  ModelSelectDialogFragment.class.getSimpleName());
        } else {
            ModelConfirmDialogFragmentSample dialog =
                    ModelConfirmDialogFragmentSample.newInstance(
                            Const.MODEL_CONFIRM_DIALOG,
                            model,info);
            dialog.show(
                    activity.getSupportFragmentManager(), ModelConfirmDialogFragmentSample.class.getSimpleName()
            );
        }
    }
}