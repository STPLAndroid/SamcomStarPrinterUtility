package com.stpl.starmicronicsprinterlibrary.searchPrinter;

import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_ALL;
import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_BLUETOOTH;
import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_ETHERNET;
import static com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingConstant.IF_TYPE_USB;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.stpl.starmicronicsprinterlibrary.Utils.Utils;
import com.stpl.starmicronicsprinterlibrary.interfaces.PrinterListCallBack;
import com.stpl.starmicronicsprinterlibrary.model.PrinterSettings;
import com.stpl.starmicronicsprinterlibrary.model.SearchResultInfo;
import com.starmicronics.stario.PortInfo;
import com.starmicronics.stario.StarIOPort;
import com.starmicronics.stario.StarIOPortException;
import com.stpl.starmicronicsprinterlibrary.printerUtils.PrinterSettingManager;

import java.util.ArrayList;
import java.util.List;

public class SearchPrinterUtils {
    int count = 0;
    Activity activity;
    private static final int ACCESS_REQUEST_CODE=100;
    private static final int REQUEST_ENABLE_CODE=1;
    PrinterListCallBack printerTypeCallBack;
    private List<SearchResultInfo> searchResultArray = new ArrayList<>();

    public SearchPrinterUtils(Activity activity, PrinterListCallBack printerTypeCallBack) {
        this.activity = activity;
        this.printerTypeCallBack = printerTypeCallBack;
    }

    public void startSearchToLan() {
        searchResultArray.clear();
        SearchTask searchTask = new SearchTask();
        searchTask.execute(IF_TYPE_ETHERNET);
    }


    public void startSearchToBluetooth() {
        setBluetooth(true);
        searchResultArray.clear();
        SearchTask searchTask = new SearchTask();
        searchTask.execute(IF_TYPE_BLUETOOTH);
    }

    private PrinterSettings getPrinterSetting(Activity activity) {
        PrinterSettingManager settingManager = new PrinterSettingManager(activity);
        PrinterSettings settings = settingManager.getPrinterSettings();
        return settings;
    }

    // search with USB Connections
    public void startSearchToUsb() {
        searchResultArray.clear();
        SearchTask searchTask = new SearchTask();
        searchTask.execute(IF_TYPE_USB);
    }


    public void startSearchAll() {
        setBluetooth(true);
        count = 0;
        searchResultArray.clear();
        String[] str = new String[]{IF_TYPE_ETHERNET, IF_TYPE_BLUETOOTH, IF_TYPE_USB};
        for (String s : str) {
            SearchTaskAll searchTask = new SearchTaskAll();
            searchTask.execute(s);
        }
    }


    public  boolean setBluetooth(boolean enable) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean isEnabled = bluetoothAdapter.isEnabled();
        if (enable && !isEnabled) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableIntent, REQUEST_ENABLE_CODE);
            }
            return bluetoothAdapter.enable();
        }
        else if(!enable && isEnabled) {
            return bluetoothAdapter.disable();
        }
        // No need to change bluetooth state
        return true;
    }
    /*public void enableBlueTooth() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.disable();
        }
    }*/


    private class SearchTask extends AsyncTask<String, Void, Void> {
        private List<PortInfo> mPortList;

        SearchTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.pDialog(activity);
        }

        @Override
        protected Void doInBackground(String... interfaceType) {
            try {
                mPortList = StarIOPort.searchPrinter(interfaceType[0], activity);
            } catch (StarIOPortException e) {
                mPortList = new ArrayList<>();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void doNotUse) {
            Utils.pDialogDismiss();
            for (PortInfo info : mPortList) {
                addItem(info);
            }
            if (searchResultArray.isEmpty()) {
                printerTypeCallBack.onFailSearchResult("no item found");
            } else {
                printerTypeCallBack.onSuccessSearchResult(searchResultArray);
            }
        }

    }

    private class SearchTaskAll extends AsyncTask<String, Void, Void> {
        private List<PortInfo> mPortList;

        SearchTaskAll() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.pDialog(activity);
        }

        @Override
        protected Void doInBackground(String... interfaceType) {
            try {
                mPortList = StarIOPort.searchPrinter(interfaceType[0], activity);
            } catch (StarIOPortException e) {
                mPortList = new ArrayList<>();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void doNotUse) {
            count++;
            Utils.pDialogDismiss();
            for (PortInfo info : mPortList) {
                addItem(info);
            }
            if (count == 3) {
                if (searchResultArray.isEmpty()) {
                    printerTypeCallBack.onFailSearchResult("no item found");
                } else {
                    printerTypeCallBack.onSuccessSearchResult(searchResultArray);
                }
            }
        }
    }

    private void addItem(PortInfo info) {
        try {
            String modelName;
            String portName;
            String macAddress;

            if (info.getPortName().startsWith(IF_TYPE_BLUETOOTH)) {
                modelName = info.getPortName().substring(IF_TYPE_BLUETOOTH.length());
                portName = IF_TYPE_BLUETOOTH + info.getMacAddress();
                macAddress = info.getMacAddress();
            } else {
                modelName = info.getModelName();
                portName = info.getPortName();
                macAddress = info.getMacAddress();
            }

            SearchResultInfo searchResultInfo = new SearchResultInfo();
            searchResultInfo.setModelName(modelName);
            searchResultInfo.setPortNumber(portName);
            searchResultInfo.setMacAddress(macAddress);
            searchResultArray.add(searchResultInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void searchPrinter(String type) {
        if (type.equalsIgnoreCase(IF_TYPE_ETHERNET)) {
            startSearchToLan();
        } else if (type.equalsIgnoreCase(IF_TYPE_BLUETOOTH)) {
            startSearchToBluetooth();
        } else if (type.equalsIgnoreCase(IF_TYPE_USB)) {
            startSearchToUsb();
        } else if (type.equalsIgnoreCase(IF_TYPE_ALL)) {
            startSearchAll();
        }
    }
}

