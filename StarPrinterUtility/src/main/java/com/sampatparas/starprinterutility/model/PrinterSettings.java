package com.sampatparas.starprinterutility.model;

import androidx.annotation.NonNull;

public class PrinterSettings {
    private final int mModelIndex;
    private final String mPortName;
    private final String mPortSettings;
    private final String mMacAddress;
    private final String mModelName;
    private final boolean mCashDrawerOpenActiveHigh;
    private final int mPaperSize;

    public PrinterSettings(int modelIndex, @NonNull String portName, @NonNull String portSettings, @NonNull String macAddress, @NonNull String modelName, boolean cashDrawerOpenActiveHigh, int paperSize) {
        mModelIndex = modelIndex;
        mPortName = portName;
        mPortSettings = portSettings;
        mMacAddress = macAddress;
        mModelName = modelName;
        mCashDrawerOpenActiveHigh = cashDrawerOpenActiveHigh;
        mPaperSize = paperSize;
    }

    public int getModelIndex() {
        return mModelIndex;
    }

    public String getPortName() {
        return mPortName;
    }

    public String getPortSettings() {
        return mPortSettings;
    }

    public String getMacAddress() {
        return mMacAddress;
    }

    public String getModelName() {
        return mModelName;
    }

    public boolean getCashDrawerOpenActiveHigh() {
        return mCashDrawerOpenActiveHigh;
    }

    public int getPaperSize() {
        return mPaperSize;
    }
}
